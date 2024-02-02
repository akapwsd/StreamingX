package com.code.aws;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.code.okhttp.WSManager;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.Constants;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class S3AwsHelper {
    private final String TAG = "S3AwsHelper";
    private static S3AwsHelper single;
    public static final String IMAGE_SPLIT = "@@";
    private ConcurrentHashMap<String, Long> downloadList;

    private S3AwsHelper() {
        if (downloadList == null) {
            downloadList = new ConcurrentHashMap<>();
        }
    }

    public synchronized static S3AwsHelper getInstance() {
        synchronized (S3AwsHelper.class) {
            if (single == null) {
                single = new S3AwsHelper();
            }
        }
        return single;
    }

    public void uploadWithTransferUtility(final String msgFp, final String filepath, final String ext, final int status, long account, IAWSFileRequest request) {
        TransferUtility transferUtility = WSManager.getInstance().getTransferUtility();
        if (transferUtility == null) {
            LogUtil.e(TAG, "uploadWithTransferUtility::get transfer utility fail");
            return;
        }
        String paasImPrefix = RtcSpUtils.getInstance().getPaasImPrefix();
        String contentType;
        if (account != 0L) {
            paasImPrefix = paasImPrefix + ":" + account;
        }
        if (status == Constants.MSG_SEND_VOICE) {
            contentType = paasImPrefix + "/voice/";
        } else if (status == Constants.MSG_SEND_IMAGE) {
            contentType = paasImPrefix + "/image/";
        } else {
            LogUtil.e(TAG, "uploadWithTransferUtility file type is unknown");
            return;
        }
        LogUtil.d(TAG, "uploadWithTransferUtility::key is: " + Constants.AWS_KEY + " paasImPrefix:" + paasImPrefix + " account:" + account + " contentType:" + contentType);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType + ext);
        final TransferObserver uploadObserver = transferUtility.upload(Constants.AWS_KEY, new File(filepath), objectMetadata);
        uploadObserver.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    if (TransferState.COMPLETED == uploadObserver.getState()) {
                        String absoluteFilePath = uploadObserver.getBucket();
                        LogUtil.d(TAG, "update complete and absoluteFilePath is: " + absoluteFilePath);
                        LogUtil.d(TAG, "Bytes state: " + uploadObserver.getState());
                        LogUtil.d(TAG, "Bytes Transferred: " + uploadObserver.getBytesTransferred());
                        LogUtil.d(TAG, "Bytes Total: " + uploadObserver.getBytesTotal());
                        request.aws_success(Constants.AWS_UPLOAD, msgFp, "");
                        uploadObserver.cleanTransferListener();
                    }
                } else {
                    LogUtil.d(TAG, "update other state and state is: " + state);
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDoneF = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int) percentDoneF;
                LogUtil.d(TAG, "ID:" + id + " bytesCurrent: " + bytesCurrent + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
                request.aws_progress(Constants.AWS_UPLOAD, msgFp, percentDone);
            }

            @Override
            public void onError(int id, Exception ex) {
                LogUtil.e(TAG, "uploadWithTransferUtility::" + ex.getLocalizedMessage());
                uploadObserver.cleanTransferListener();
                request.aws_error(Constants.AWS_UPLOAD, msgFp, id, ex.getMessage());
            }
        });
    }

    public void downloadWithTransferUtility(String awsKey, String msgFp, int mediaType, long account, String downloadPath, IAWSFileRequest request) {
        TransferUtility transferUtility = WSManager.getInstance().getTransferUtility();
        if (transferUtility == null) {
            LogUtil.e(TAG, "downloadWithTransferUtility::get transfer utility fail");
            downloadList.remove(awsKey);
            return;
        }
        downloadList.put(awsKey, System.currentTimeMillis());
        String key = awsKey.replace(Constants.AWS_HTTPS_HEAD, "");
        LogUtil.d(TAG, "downloadWithTransferUtility::key is: " + key);
        File folder = new File(downloadPath);
        if (!folder.exists()) {
            boolean mkdir = folder.mkdir();
            LogUtil.d(TAG, "downloadWithTransferUtility::mkdir is: " + mkdir);
        }
        final File localMediaFileByUrl = findLocalMediaFileByUrl(awsKey, mediaType, account, downloadPath);
        if (localMediaFileByUrl.exists()) {
            request.aws_success(Constants.AWS_DOWNLOAD, msgFp, localMediaFileByUrl.getPath());
            downloadList.remove(awsKey);
            return;
        }
        final TransferObserver downloadObserver = transferUtility.download(key, new File(localMediaFileByUrl.getPath()));
        downloadObserver.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    if (TransferState.COMPLETED == downloadObserver.getState()) {
                        String absoluteFilePath = downloadObserver.getBucket();
                        LogUtil.d(TAG, "download complete and absoluteFilePath is: " + absoluteFilePath);
                        LogUtil.d(TAG, "Bytes state: " + downloadObserver.getState());
                        LogUtil.d(TAG, "Bytes Transferred: " + downloadObserver.getBytesTransferred());
                        LogUtil.d(TAG, "Bytes Total: " + downloadObserver.getBytesTotal());
                        request.aws_success(Constants.AWS_DOWNLOAD, msgFp, localMediaFileByUrl.getPath());
                        downloadObserver.cleanTransferListener();
                        downloadList.remove(awsKey);
                    }
                } else {
                    LogUtil.d(TAG, "1----update other state and state is: " + state);
                    if (state == TransferState.FAILED) {
                        request.aws_error(Constants.AWS_DOWNLOAD, msgFp, Constants.AWS_REQUEST_TIMEOUT, "download timeout");
                        downloadObserver.cleanTransferListener();
                        downloadList.remove(awsKey);
                        if (localMediaFileByUrl.exists()) {
                            boolean delete = localMediaFileByUrl.delete();
                            LogUtil.d(TAG, "downloadWithTransferUtility onError delete:" + delete);
                        }
                    }
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDoneF = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int) percentDoneF;
                LogUtil.d(TAG, "onProgressChanged::ID is: " + id + "   bytesCurrent is: " + bytesCurrent + "  bytesTotal is: " + bytesTotal + " " + percentDone + "%");
                request.aws_progress(Constants.AWS_DOWNLOAD, msgFp, percentDone);
            }

            @Override
            public void onError(int id, Exception ex) {
                ex.printStackTrace();
                LogUtil.e(TAG, "downloadWithTransferUtility::" + ex.getLocalizedMessage());
                downloadObserver.cleanTransferListener();
                request.aws_error(Constants.AWS_DOWNLOAD, msgFp, id, ex.getMessage());
                downloadList.remove(awsKey);
                if (localMediaFileByUrl.exists()) {
                    boolean delete = localMediaFileByUrl.delete();
                    LogUtil.d(TAG, "downloadWithTransferUtility onError delete:" + delete);
                }
            }
        });
    }

    private File findLocalMediaFileByUrl(String fileUrl, int type, long account, String downloadPath) {
        try {
            int i = fileUrl.indexOf("?");
            if (i > 0) {
                fileUrl = fileUrl.substring(0, i);
            }
            String paasImPrefix = RtcSpUtils.getInstance().getPaasImPrefix();
            if (account != 0L) {
                paasImPrefix = paasImPrefix + ":" + account;
            }
            LogUtil.d(TAG, "findLocalMediaFileByUrl::filepath is: " + fileUrl + " paasImPrefix:" + paasImPrefix + " account:" + account);
            String fileExt = "";
            int filePublicIndex;
            if (type == Constants.MSG_SEND_VOICE) {
                filePublicIndex = fileUrl.indexOf(paasImPrefix + "/voice/");
            } else if (type == Constants.MSG_SEND_IMAGE) {
                filePublicIndex = fileUrl.indexOf(paasImPrefix + "/image/");
            } else {
                LogUtil.e(TAG, "findLocalMediaFileByUrl file type is unknown");
                return null;
            }
            if (filePublicIndex < 0) {
                filePublicIndex = 0;
            }
            String realKey = fileUrl.substring(filePublicIndex);
            int fileExtIndex = realKey.lastIndexOf(".");
            LogUtil.d(TAG, "findLocalMediaFileByUrl::fileExtIndex is: " + fileExtIndex + " and filePublicIndex is: " + filePublicIndex + " and realKey is: " + realKey);
            if (fileExtIndex > 0) {
                fileExt = realKey.substring(fileExtIndex);
            } else {
                fileExtIndex = realKey.length();
            }
            LogUtil.d(TAG, "findLocalMediaFileByUrl::fileExt is: " + fileExt);
            String name = realKey.substring(realKey.lastIndexOf("/"), fileExtIndex).replace("/", "");
            LogUtil.d(TAG, "findLocalMediaFileByUrl::name is: " + name);
            File folder = new File(downloadPath);
            if (!folder.exists()) {
                boolean mkdir = folder.mkdir();
                LogUtil.d(TAG, "findLocalMediaFileByUrl::mkdir is: " + mkdir);
            }
            final File file = new File(downloadPath + name + fileExt);
            LogUtil.d(TAG, "findLocalMediaFileByUrl::file is: " + file.getPath());
            return file;
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
            return new File("");
        }
    }

    public interface IAWSFileRequest {
        void aws_success(int requestType, String msgFp, String key);

        void aws_progress(int requestType, String msgFp, int progress);

        void aws_error(int requestType, String msgFp, int error_code, String error_msg);
    }
}
