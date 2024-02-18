package com.code.okhttp;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.code.aws.S3AwsHelper;
import com.code.bean.ChannelMsgBean;
import com.code.data.GreenDaoHelper;
import com.code.data.sqlbean.ChatListBean;
import com.code.data.sqlbean.MsgBean;
import com.code.data.sqlhelper.ChatListHelper;
import com.code.data.sqlhelper.MessageHelper;
import com.code.listener.ChannelMsgListener;
import com.code.listener.ChatMsgListener;
import com.code.listener.DownloadListener;
import com.code.listener.IRtcEngineEventCallBackHandler;
import com.code.msg.NettyMsg;
import com.code.utils.DataUtils;
import com.code.utils.HttpRequestUtils;
import com.code.utils.LogUtil;
import com.code.utils.MessageLoopThread;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.Constants;
import com.code.youyu.api.StreamingXRtcManager;
import com.google.protobuf.InvalidProtocolBufferException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import proto.ErrorOuterClass;
import uyujoy.api.channelim.frontend.ChannelIm;
import uyujoy.api.paasim.frontend.MediaBase;
import uyujoy.api.paasim.frontend.MsgBase;
import uyujoy.api.paasim.frontend.MsgReceiving;
import uyujoy.api.paasim.frontend.PaasIm;
import uyujoy.api.paasim.frontend.UpdatesBase;
import uyujoy.api.paasim.frontend.UserBase;
import uyujoy.com.api.channel.frontend.ChannelImform;
import uyujoy.com.api.gateway.frontend.Api;
import uyujoy.com.api.gateway.frontend.Base;

public class WSManager implements GreenDaoHelper.GreenDaoInitResultListener {
    private final String TAG = "WSManager";
    private boolean isBroadcaster = false;
    private boolean isNeedGetState = false;
    private Context mContext;
    private TransferUtility transferUtility;
    private final static int MAX_RECONNECT_NUM = 5;
    private final static int RECONNECT_MILLS = 1000;
    private final static int GLOBAL_HEART_BEAT_RATE = 5000;
    private final static int MAX_DIS_RECEIVE_NUM = 1;
    private int disReceivePongIndex = 0;
    private final static String BASE_URL = "wss://api.streamingxapp.com/v1/ws";
    private final static String BASE_TEST_URL = "wss://api.hitradegate.com/v1/ws";
    private final static String MODEL_BASE_URL = "wss://broadcaster.streamingxapp.com/v1/ws";
    private final static String MODEL_BASE_TEST_URL = "wss://broadcaster.hitradegate.com/v1/ws";
    private static HashMap<Integer, WeakReference<WebSocketResultListener>> sWeakRefListeners;
    private final HashMap<String, IRtcEngineEventCallBackHandler> callBackHandlerHashMap = new HashMap<>();
    private WebSocket mWebSocket;
    private OkHttpClient mClient;
    private String mWbSocketUrl;
    private boolean isReceivePong = true;
    private boolean isConnect = false;
    private boolean isCallIng = false;
    public String mChannelId = "";
    public int mClientRole;
    public String mUid = "";
    public int mCallType = 1;
    public String mToken = "";
    private String access_key_id;
    private String access_key_secret;
    private String session_token;
    private String token;
    private int reconnectNum = 0;
    private Handler heartHandler;
    private ChannelMsgListener channelMsgListener;
    private ChatMsgListener chatMsgListener;
    private Request request;
    private final Map<String, Function<byte[], Boolean>> actionMappings = new HashMap<>();
    private final HashMap<String, String> awsTranslateMap = new HashMap<>();
    private long serverNewPts = 0;
    private int initAwsCount = 0;

    private void initDataProcess() {
        Function<byte[], Boolean> pongFunction = bytes -> pongHandle();
        Function<byte[], Boolean> banUserRoomFunction = this::banUserRoomHandle;
        Function<byte[], Boolean> closeRoomFunction = this::closeRoomHandle;
        Function<byte[], Boolean> msgRecordFunction = this::msgRecordHandle;
        Function<byte[], Boolean> channelMsgRecordFunction = this::channelMsgRecordHandle;
        Function<byte[], Boolean> channelMatchFunction = this::channelMatchHandle;
        Function<byte[], Boolean> channelSkipFunction = this::channelSkipHandle;
        Function<byte[], Boolean> deviceUpdatedFunction = this::deviceUpdatedHandle;
        Function<byte[], Boolean> diffChatMsgFunction = this::diffMsgHandle;
        Function<byte[], Boolean> connectErrorFunction = this::connectErrorHandle;
        Function<byte[], Boolean> statesFunction = this::statesHandle;
        Function<byte[], Boolean> sendChatResultFunction = this::sendChatResultHandle;
        Function<byte[], Boolean> shortMessageFunction = this::shortMessageHandle;
        Function<byte[], Boolean> updateNewMessageFunction = this::updateNewMessageHandle;

        actionMappings.put(Constants.PONG, pongFunction);
        actionMappings.put(Constants.BAN_USER_ROOM, banUserRoomFunction);
        actionMappings.put(Constants.CLOSE_ROOM, closeRoomFunction);
        actionMappings.put(Constants.MSG_RECORD_ACK, msgRecordFunction);
        actionMappings.put(Constants.CHANNEL_MSG_RECORD, channelMsgRecordFunction);
        actionMappings.put(Constants.CHANNEL_MATCH, channelMatchFunction);
        actionMappings.put(Constants.CHANNEL_SKIP, channelSkipFunction);
        actionMappings.put(Constants.DEVICE_UPDATED, deviceUpdatedFunction);
        actionMappings.put(Constants.CHAT_DIFF_MSG_ACK, diffChatMsgFunction);
        actionMappings.put(Constants.STATES_ACK, statesFunction);
        actionMappings.put(Constants.CONNECT_ERROR, connectErrorFunction);
        actionMappings.put(Constants.MSG_SENT_ACK, sendChatResultFunction);
        actionMappings.put(Constants.SHORT_MESSAGE, shortMessageFunction);
        actionMappings.put(Constants.UPDATE_NEW_MESSAGES, updateNewMessageFunction);
    }

    private boolean pongHandle() {
        isReceivePong = true;
        return true;
    }

    private boolean banUserRoomHandle(byte[] data) {
        try {
            ChannelImform.channelUserStateChange channelUserStateChange = ChannelImform.channelUserStateChange.parseFrom(data);
            String uid = channelUserStateChange.getChu().getUid();
            if (uid.equals(mUid)) {
                StreamingXRtcManager.getInstance().closeVideoChat();
            }
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.banRoom(uid, channelUserStateChange.getReason());
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "userRoomHandle throw error:" + e);
            return false;
        }
    }

    private boolean closeRoomHandle(byte[] data) {
        try {
            ChannelImform.channelStateChange channelStateChange = ChannelImform.channelStateChange.parseFrom(data);
            StreamingXRtcManager.getInstance().closeVideoChat();
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.closeRoom(channelStateChange.getReason());
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "closeRoomHandle throw error:" + e);
            return false;
        }
    }

    private boolean msgRecordHandle(byte[] data) {
        try {
            ChannelIm.channelMsgRecordAck channelMsgRecordAck = ChannelIm.channelMsgRecordAck.parseFrom(data);
            if (channelMsgListener != null) {
                channelMsgListener.sendSuccess(channelMsgRecordAck.getFp());
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "msgRecordHandle throw error:" + e);
            return false;
        }
    }

    private boolean channelMsgRecordHandle(byte[] data) {
        try {
            ChannelIm.rcvChannelMsgRecord rcvChannelMsgRecord = ChannelIm.rcvChannelMsgRecord.parseFrom(data);
            LogUtil.d(TAG, "CHANNEL_MSG_RECORD rcvChannelMsgRecord:" + rcvChannelMsgRecord);
            ChannelMsgBean currentReceiveMsg = new ChannelMsgBean();
            currentReceiveMsg.setMsg(rcvChannelMsgRecord.getMsg().getMsg());
            currentReceiveMsg.setSendTime(rcvChannelMsgRecord.getMsg().getSendTime());
            currentReceiveMsg.setFp(rcvChannelMsgRecord.getMsg().getMsgFp());
            currentReceiveMsg.setChannelId(rcvChannelMsgRecord.getMsg().getChannelId());
            currentReceiveMsg.setFrom(rcvChannelMsgRecord.getMsg().getFrom());
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.receiveMsg(currentReceiveMsg);
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "channelMsgRecordHandle throw error:" + e);
            return false;
        }
    }

    private boolean channelMatchHandle(byte[] data) {
        try {
            ChannelImform.channelMatched channelMatched = ChannelImform.channelMatched.parseFrom(data);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.receiveMatch(channelMatched);
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "channelMatchHandle throw error:" + e);
            return false;
        }
    }

    private boolean channelSkipHandle(byte[] data) {
        try {
            ChannelImform.channelSkipped channelSkipped = ChannelImform.channelSkipped.parseFrom(data);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.receiveSkip(channelSkipped.getChannelId());
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "channelSkipHandle throw error:" + e);
            return false;
        }
    }

    private boolean deviceUpdatedHandle(byte[] data) {
        try {
            ChannelImform.deviceUpdated deviceUpdated = ChannelImform.deviceUpdated.parseFrom(data);
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.deviceUpdated(deviceUpdated.getIp());
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "deviceUpdatedHandle throw error:" + e);
            return false;
        }
    }

    private boolean statesHandle(byte[] data) {
        try {
            PaasIm.states states = PaasIm.states.parseFrom(data);
            serverNewPts = states.getPts();
            long lastPts = MessageHelper.getSingleton().getLastPts();
            LogUtil.d(TAG, "statesHandle serverNewPts:" + serverNewPts + " lastPts:" + lastPts);
            if (serverNewPts > lastPts) {
                getChatDiffMsg();
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "statesHandle throw error:" + e);
            return false;
        }
    }

    private boolean sendChatResultHandle(byte[] data) {
        try {
            LogUtil.d(TAG, "sendChatResultHandle is start");
            PaasIm.paasImMsgSent paasImMsgSent = PaasIm.paasImMsgSent.parseFrom(data);
            MessageLoopThread.getInstance().removeReceivedMsg(paasImMsgSent.getFp(), Constants.SEND_SUCCESS);
            chatMsgListener.sendResult(paasImMsgSent.getFp(), Constants.SEND_SUCCESS);
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "statesHandle throw error:" + e);
            return false;
        }
    }

    private boolean shortMessageHandle(byte[] data) {
        try {
            MsgReceiving.shortMessage shortMessage = MsgReceiving.shortMessage.parseFrom(data);
            LogUtil.d(TAG, "shortMessageHandle shortMessage:" + shortMessage + " uid:" + RtcSpUtils.getInstance().getUserUid());
            MsgBase.paasMsgRecord msg = shortMessage.getMsg();
            MsgBean oneMessage = MessageHelper.getSingleton().getOneMessage(msg.getMsgFp());
            if (oneMessage != null) {
                LogUtil.d(TAG, "shortMessageHandle have data");
                oneMessage.setPts(shortMessage.getPts());
                oneMessage.setMsgId(msg.getMsgId());
                MessageHelper.getSingleton().modifyMessageState(oneMessage.getFp(), Constants.SEND_SUCCESS);
            } else {
                LogUtil.d(TAG, "shortMessageHandle new data");
                int msgType = msg.getMsgType();
                MsgBean msgBean = new MsgBean();
                msgBean.setPts(shortMessage.getPts());
                msgBean.setFp(msg.getMsgFp());
                msgBean.setMsgId(msg.getMsgId());
                msgBean.setSourceType(msg.getMsgType());
                msgBean.setActualTime(msg.getSendTime());
                msgBean.setNickName(msg.getUser().getName());
                msgBean.setAvatar(msg.getUser().getAvatar());
                msgBean.setUid(RtcSpUtils.getInstance().getUserUid());
                if (RtcSpUtils.getInstance().getUserUid().equals(msg.getFrom().getId())) {
                    msgBean.setPeerUid(msg.getTo().getId());
                    if (msg.getTo().getType() == MsgBase.UserType.BROADCASTER) {
                        msgBean.setUserType(Constants.TYPE_BROADCASTER);
                    } else {
                        msgBean.setUserType(Constants.TYPE_USER);
                    }
                    msgBean.setSourceType(Constants.MSG_SENDER);
                    msgBean.setAccount(msg.getTo().getAccount());
                } else if (RtcSpUtils.getInstance().getUserUid().equals(msg.getTo().getId())) {
                    msgBean.setPeerUid(msg.getFrom().getId());
                    if (msg.getFrom().getType() == MsgBase.UserType.BROADCASTER) {
                        msgBean.setUserType(Constants.TYPE_BROADCASTER);
                    } else {
                        msgBean.setUserType(Constants.TYPE_USER);
                    }
                    msgBean.setSourceType(Constants.MSG_RECEIVER);
                    msgBean.setAccount(msg.getFrom().getAccount());
                } else {
                    LogUtil.e(TAG, "shortMessageHandle fail the data is error");
                    return false;
                }
                if (msgType == Constants.MSG_SEND_TEXT) {
                    msgBean.setContent(msg.getMsgTxt());
                } else {
                    MediaBase.mediaRecord media = msg.getMedia();
                    if (msgType == Constants.MSG_SEND_IMAGE) {
                        MediaBase.mediaImage mediaImage = MediaBase.mediaImage.parseFrom(media.getMediaContent());
                        msgBean.setLocalPath(mediaImage.getPreview().getUrl());
                        msgBean.setHash(mediaImage.getPreview().getHash());
                        msgBean.setWight(mediaImage.getPreview().getWight());
                        msgBean.setHeight(mediaImage.getPreview().getHeight());
                        msgBean.setSize(mediaImage.getPreview().getSize());
                    } else if (msgType == Constants.MSG_SEND_VOICE) {
                        MediaBase.mediaAudio mediaAudio = MediaBase.mediaAudio.parseFrom(media.getMediaContent());
                        msgBean.setLocalPath(mediaAudio.getUrl());
                        msgBean.setHash(mediaAudio.getHash());
                        msgBean.setSize(mediaAudio.getSize());
                        msgBean.setTime(mediaAudio.getTimeLen());
                    } else {
                        LogUtil.d(TAG, "shortMessageHandle msg type is error");
                        return false;
                    }
                }
                MessageHelper.getSingleton().insertOrReplaceData(msgBean);
                for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                    IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                    if (iRtcEngineEventCallBackHandler != null) {
                        iRtcEngineEventCallBackHandler.receiveMsg(msgBean);
                    }
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "statesHandle throw error:" + e);
            return false;
        }
    }

    private boolean updateNewMessageHandle(byte[] data) {
        try {
            MsgReceiving.updateNewMessages updateNewMessages = MsgReceiving.updateNewMessages.parseFrom(data);
            List<UpdatesBase.updateNewMessage> msgsList = updateNewMessages.getMsgsList();
            LogUtil.d(TAG, "updateNewMessageHandle new msg size is:" + msgsList.size());
            if (msgsList.size() > 0) {
                long lastPts = MessageHelper.getSingleton().getLastPts();
                if (msgsList.get(0).getPts() > lastPts) {
                    LogUtil.d(TAG, "updateNewMessageHandle server pts > local pts");
                    getChatDiffMsg();
                }
                for (UpdatesBase.updateNewMessage dataInfo : msgsList) {
                    updateNewMessageData(dataInfo);
                }
            }

            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "statesHandle throw error:" + e);
            return false;
        }
    }

    private boolean diffMsgHandle(byte[] data) {
        try {
            PaasIm.updatesSlice updateDifferenceSlice = PaasIm.updatesSlice.parseFrom(data);
            List<UpdatesBase.updateNewMessage> msgsList = UpdatesBase.updateDifferenceSlice.parseFrom(updateDifferenceSlice.getUpdatesSlice()).getMsgsList();
            for (UpdatesBase.updateNewMessage dataInfo : msgsList) {
                updateNewMessageData(dataInfo);
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "diffMsgHandle throw error:" + e);
            return false;
        }
    }

    private boolean connectErrorHandle(byte[] data) {
        try {
            ErrorOuterClass.Error error = ErrorOuterClass.Error.parseFrom(data);
            int code = error.getCode();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", code);
            jsonObject.put("message", error.getMessage());
            jsonObject.put("status", error.getStatus());
            jsonObject.put("details", error.getDetailsList());
            for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                if (iRtcEngineEventCallBackHandler != null) {
                    iRtcEngineEventCallBackHandler.connectError(code, jsonObject.toString());
                }
            }
            return true;
        } catch (InvalidProtocolBufferException e) {
            LogUtil.e(TAG, "connectErrorHandle throw error:" + e);
            return false;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateNewMessageData(UpdatesBase.updateNewMessage dataInfo) {
        LogUtil.d(TAG, "updateNewMessageData dataInfo:" + dataInfo);
        try {
            MsgBean msgBean = new MsgBean();
            msgBean.setPts(dataInfo.getPts());
            msgBean.setAvatar(dataInfo.getMsg().getUser().getAvatar());
            msgBean.setFp(dataInfo.getMsg().getMsgFp());
            msgBean.setMsgId(dataInfo.getMsg().getMsgId());
            msgBean.setUid(RtcSpUtils.getInstance().getUserUid());
            msgBean.setActualTime(dataInfo.getMsg().getSendTime());
            msgBean.setNickName(dataInfo.getMsg().getUser().getName());
            if (RtcSpUtils.getInstance().getUserUid().equals(dataInfo.getMsg().getFrom().getId())) {
                msgBean.setSourceType(Constants.MSG_SENDER);
                msgBean.setPeerUid(dataInfo.getMsg().getTo().getId());
                msgBean.setAccount(dataInfo.getMsg().getTo().getAccount());
                if (dataInfo.getMsg().getTo().getType() == MsgBase.UserType.BROADCASTER) {
                    msgBean.setUserType(Constants.TYPE_BROADCASTER);
                } else {
                    msgBean.setUserType(Constants.TYPE_USER);
                }
            } else if (RtcSpUtils.getInstance().getUserUid().equals(dataInfo.getMsg().getTo().getId())) {
                msgBean.setSourceType(Constants.MSG_RECEIVER);
                msgBean.setPeerUid(dataInfo.getMsg().getFrom().getId());
                msgBean.setAccount(dataInfo.getMsg().getFrom().getAccount());
                if (dataInfo.getMsg().getFrom().getType() == MsgBase.UserType.BROADCASTER) {
                    msgBean.setUserType(Constants.TYPE_BROADCASTER);
                } else {
                    msgBean.setUserType(Constants.TYPE_USER);
                }
            } else {
                msgBean.setSourceType(Constants.MSG_UNKNOWN);
                LogUtil.e(TAG, "updateNewMessageData fail the data is error");
                return;
            }
            int msgType = dataInfo.getMsg().getMsgType();
            if (msgType == Constants.MSG_SEND_TEXT) {
                msgBean.setContent(dataInfo.getMsg().getMsgTxt());
            } else {
                MediaBase.mediaRecord media = dataInfo.getMsg().getMedia();
                if (msgType == Constants.MSG_SEND_IMAGE) {
                    MediaBase.mediaImage mediaImage = MediaBase.mediaImage.parseFrom(media.getMediaContent());
                    msgBean.setLocalPath(mediaImage.getPreview().getUrl());
                    msgBean.setHash(mediaImage.getPreview().getHash());
                    msgBean.setWight(mediaImage.getPreview().getWight());
                    msgBean.setHeight(mediaImage.getPreview().getHeight());
                    msgBean.setSize(mediaImage.getPreview().getSize());
                } else if (msgType == Constants.MSG_SEND_VOICE) {
                    MediaBase.mediaAudio mediaAudio = MediaBase.mediaAudio.parseFrom(media.getMediaContent());
                    msgBean.setLocalPath(mediaAudio.getUrl());
                    msgBean.setHash(mediaAudio.getHash());
                    msgBean.setSize(mediaAudio.getSize());
                    msgBean.setTime(mediaAudio.getTimeLen());
                } else {
                    LogUtil.d(TAG, "shortMessageHandle msg type is error");
                    return;
                }
            }
            MessageHelper.getSingleton().insertOrReplaceData(msgBean);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void result(boolean mInitState) {
        if (mInitState) {
            if (isNeedGetState) {
                getStates();
                isNeedGetState = false;
            }
        } else {
            GreenDaoHelper.getSingleTon().initGreenDao(mContext);
        }
    }

    WSManager() {
        GreenDaoHelper.getSingleTon().setGreenDaoInitResultListener(this);
    }

    private static final class SInstanceHolder {
        static final WSManager sInstance = new WSManager();
    }

    public static WSManager getInstance() {

        return SInstanceHolder.sInstance;
    }

    public void setIRtcEngineEventCallBackHandler(String tag, IRtcEngineEventCallBackHandler callBackHandler) {
        callBackHandlerHashMap.put(tag, callBackHandler);
    }

    public void renewToken(String newToken) {
        LogUtil.d(TAG, "renewToken1 is start");
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
        }
        token = newToken;
        access_key_id = "";
        access_key_secret = "";
        session_token = "";
        RtcSpUtils.getInstance().setToken(token);
        RtcSpUtils.getInstance().setAccessKeyId("");
        RtcSpUtils.getInstance().setAccessKeySecret("");
        RtcSpUtils.getInstance().setSessionToken("");
        if (request != null) {
            request = null;
            request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", token).build();
            mWebSocket.close(1003, "renew token");
            mWebSocket = null;
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        }
        LogUtil.d(TAG, "renewToken1 is end");
    }

    public void renewToken(String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "renewToken2 is start");
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
        }
        this.access_key_id = access_key_id;
        this.access_key_secret = access_key_secret;
        this.session_token = session_token;
        token = "";
        RtcSpUtils.getInstance().setAccessKeyId(access_key_id);
        RtcSpUtils.getInstance().setAccessKeySecret(access_key_secret);
        RtcSpUtils.getInstance().setSessionToken(session_token);
        RtcSpUtils.getInstance().setToken("");
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        LogUtil.d(TAG, "sign:" + sign);
        if (request != null) {
            request = null;
            request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign).addHeader("Session-Token", session_token).addHeader("X-Uyj-Timestamp", X_Uyj_Timestamp).addHeader("Content-Type", Content_Type).build();
            mWebSocket.cancel();
            mWebSocket.close(1003, "renew token");
            mWebSocket = null;
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        }
        LogUtil.d(TAG, "renewToken2 is end");
    }

    /**
     * init WebSocket
     */
    public void init(Context context, String uid, String access_key_id, String access_key_secret, String session_token) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            isBroadcaster = false;
            mContext = context;
            initDataProcess();
            GreenDaoHelper.getSingleTon().initGreenDao(context);
            sWeakRefListeners = new HashMap<>();
            heartHandler = new Handler();
            this.access_key_id = access_key_id;
            this.access_key_secret = access_key_secret;
            this.session_token = session_token;
            if (StreamingXRtcManager.getInstance().isEnableDebug) {
                mWbSocketUrl = BASE_TEST_URL;
            } else {
                mWbSocketUrl = BASE_URL;
            }
            RtcSpUtils.getInstance().setAccessKeyId(access_key_id);
            RtcSpUtils.getInstance().setAccessKeySecret(access_key_secret);
            RtcSpUtils.getInstance().setSessionToken(session_token);
            RtcSpUtils.getInstance().setUserUid(uid);
            RtcSpUtils.getInstance().setToken("");
            LogUtil.d(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void init(Context context, String uid, String token) {
        LogUtil.d(TAG, "init is start");
        if (context != null) {
            isBroadcaster = true;
            mContext = context;
            initDataProcess();
            GreenDaoHelper.getSingleTon().initGreenDao(context);
            sWeakRefListeners = new HashMap<>();
            heartHandler = new Handler();
            this.token = token;
            if (StreamingXRtcManager.getInstance().isEnableDebug) {
                mWbSocketUrl = MODEL_BASE_TEST_URL;
            } else {
                mWbSocketUrl = MODEL_BASE_URL;
            }
            RtcSpUtils.getInstance().setToken(token);
            RtcSpUtils.getInstance().setUserUid(uid);
            RtcSpUtils.getInstance().setAccessKeyId("");
            RtcSpUtils.getInstance().setAccessKeySecret("");
            RtcSpUtils.getInstance().setSessionToken("");
            LogUtil.d(TAG, "mWbSocketUrl=" + mWbSocketUrl);
            mClient = new OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).pingInterval(10, TimeUnit.SECONDS).build();
            connect();
        }
        LogUtil.d(TAG, "init is end");
    }

    public void initAWS(Context context) {
        LogUtil.d(TAG, "initAWS is start");
        String sessionToken = awsTranslateMap.get("SessionToken");
        String accessKeyId = awsTranslateMap.get("AccessKeyId");
        String secretAccessKey = awsTranslateMap.get("SecretAccessKey");
        if (!TextUtils.isEmpty(sessionToken) && !TextUtils.isEmpty(accessKeyId) && !TextUtils.isEmpty(secretAccessKey)) {
            LogUtil.d(TAG, "initAWS is ok");
            AWSSessionCredentials credentials = new AWSSessionCredentials() {
                @Override
                public String getSessionToken() {
                    return awsTranslateMap.get("SessionToken");
                }

                @Override
                public String getAWSAccessKeyId() {
                    return awsTranslateMap.get("AccessKeyId");
                }

                @Override
                public String getAWSSecretKey() {
                    return awsTranslateMap.get("SecretAccessKey");
                }
            };
            JSONObject jsonConfig = new JSONObject();
            JSONObject s3TransferUtility = new JSONObject();
            try {
                jsonConfig.putOpt("S3TransferUtility", s3TransferUtility);
                s3TransferUtility.put("Region", Constants.Region);
                s3TransferUtility.put("Bucket", Constants.Bucket);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            AWSConfiguration configuration = new AWSConfiguration(jsonConfig);
            AWSMobileClient.getInstance().initialize(context, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    LogUtil.i(TAG, "AWSMobileClient initialized. User State is " + userStateDetails.getUserState());
                    TransferUtility transferUtility = TransferUtility.builder().context(context).awsConfiguration(configuration).s3Client(new AmazonS3Client(credentials)).defaultBucket(Constants.Bucket).build();
                    if (transferUtility != null) {
                        setTransferUtility(transferUtility);
                    } else {
                        LogUtil.e(TAG, "initAWS error::build transfer utility fail");
                    }
                }

                @Override
                public void onError(Exception e) {
                    LogUtil.e(TAG, "initAWS error: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        } else {
            if (initAwsCount < 5) {
                initAwsCount++;
                HttpRequestUtils.getInstance().requestAwsSts(mContext);
            } else {
                LogUtil.e(TAG, "initAWS fail please check your net");
            }
        }
        LogUtil.d(TAG, "initAWS is end");
    }

    public TransferUtility getTransferUtility() {
        return transferUtility;
    }

    public void setTransferUtility(TransferUtility transferUtility) {
        this.transferUtility = transferUtility;
    }

    public void disconnect(int code, String reason) {
        LogUtil.e(TAG, "disconnect is start code:" + code + " reason:" + reason + " isConnect:" + isConnect());
        if (isConnect()) {
            if (sWeakRefListeners != null) {
                sWeakRefListeners.clear();
            }
            if (heartHandler != null) {
                heartHandler.removeCallbacksAndMessages(null);
                heartHandler = null;
            }
            if (mWebSocket != null) {
                mWebSocket.cancel();
                mWebSocket.close(code, reason);
            }
        }
        closeConnect();
    }

    private void closeConnect() {
        LogUtil.e(TAG, "closeConnect is start isCallIng:" + isCallIng);
        isConnect = false;
        reconnectNum = 0;
        token = "";
        session_token = "";
        access_key_secret = "";
        access_key_id = "";
        request = null;
        mWebSocket = null;
        if (heartHandler != null) {
            heartHandler.removeCallbacksAndMessages(null);
            heartHandler = null;
        }
        if (isCallIng) {
            isCallIng = false;
            StreamingXRtcManager.getInstance().closeVideoChat();
        }
        LogUtil.e(TAG, "closeConnect is end");
    }

    private void reconnect() {
        LogUtil.d(TAG, "reconnect is start reconnectNum:" + reconnectNum);
        if (reconnectNum <= MAX_RECONNECT_NUM) {
            try {
                Thread.sleep(RECONNECT_MILLS);
                connect();
                reconnectNum++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.e(TAG, "reconnect over,please check url or network");
            closeConnect();
        }
    }

    private boolean isConnect() {
        return mWebSocket != null && isConnect;
    }

    private void connect() {
        LogUtil.d(TAG, "connect is start token:" + token + " access_key_id:" + access_key_id + " access_key_secret:" + access_key_secret + " session_token:" + session_token);
        if (!TextUtils.isEmpty(token)) {
            LogUtil.d(TAG, "connect is start token:" + token);
            if (request == null) {
                request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", token).build();
            }
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        } else if (!TextUtils.isEmpty(access_key_id) && !TextUtils.isEmpty(access_key_secret) && !TextUtils.isEmpty(session_token)) {
            LogUtil.d(TAG, "connect is start access_key_id:" + access_key_id + " access_key_secret:" + access_key_secret + " session_token:" + session_token);
            long currentTimeMillis = System.currentTimeMillis();
            String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
            String Content_Type = "application/json";
            String data = X_Uyj_Timestamp + Content_Type;
            String sign = DataUtils.sha256_HMAC(access_key_secret, data);
            LogUtil.d(TAG, "sign:" + sign);
            if (request == null) {
                request = new Request.Builder().url(mWbSocketUrl).addHeader("Authorization", "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign).addHeader("Session-Token", session_token).addHeader("X-Uyj-Timestamp", X_Uyj_Timestamp).addHeader("Content-Type", Content_Type).build();
            }
            mWebSocket = mClient.newWebSocket(request, new WsListener());
        } else {
            LogUtil.e(TAG, "the connect is died");
        }
        LogUtil.d(TAG, "connect is end");
    }

    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosed code:" + code + " reason:" + reason);
            super.onClosed(webSocket, code, reason);
            mWebSocket = null;
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            LogUtil.e(TAG, "onClosing code:" + code + " reason:" + reason);
            super.onClosing(webSocket, code, reason);
            closeConnect();
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            if (response != null) {
                LogUtil.e(TAG, "websocket onFailure:" + response.message());
            }
            if (!TextUtils.isEmpty(t.getMessage())) {
                String message = t.getMessage();
                LogUtil.e(TAG, "websocket fail reason：" + message);
                assert message != null;
                if (message.contains("Canceled") || message.contains("Socket is closed") || message.contains("Code 2293 is reserved and may not be used")) {
                    LogUtil.e(TAG, "connect is need close");
                    if (heartHandler != null && heartBeatRunnable != null) {
                        heartHandler.removeCallbacksAndMessages(null);
                        heartHandler = null;
                    }
                    disconnect(1002, "disconnect");
                } else if (message.contains("Socket closed")) {
                    LogUtil.e(TAG, "Socket is closed");
                    if (heartHandler != null && heartBeatRunnable != null) {
                        heartHandler.removeCallbacksAndMessages(null);
                        heartHandler = null;
                    }
                } else {
                    reconnect();
                }
            }
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            LogUtil.e(TAG, "receive message:" + text);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
            LogUtil.d(TAG, "onMessage receive bytes:" + Arrays.toString(bytes.toByteArray()));
            Base.messageFrame messageFrame;
            try {
                messageFrame = Base.messageFrame.parseFrom(bytes.toByteArray());
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
            int crc32 = messageFrame.getCrc32();
            LogUtil.d(TAG, "onMessage receive crc32:" + crc32);
            String OxCrcId = Integer.toHexString(crc32);
            byte[] resultData = messageFrame.getData().toByteArray();
            LogUtil.d(TAG, "onMessage receive OxCrcId:" + OxCrcId);
            Objects.requireNonNull(actionMappings.get(OxCrcId)).apply(resultData);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            LogUtil.d(TAG, "websocket is connect:" + response);
            mWebSocket = webSocket;
            isConnect = response.code() == 101;
            if (!isConnect) {
                reconnect();
            } else {
                LogUtil.d(TAG, "websocket is connect success");
                boolean initState = GreenDaoHelper.getSingleTon().getInitState();
                if (initState) {
                    getStates();
                } else {
                    isNeedGetState = true;
                }
                if (!isBroadcaster) {
                    HttpRequestUtils.getInstance().requestAwsSts(mContext);
                }
                isReceivePong = true;
                if (heartHandler != null && heartBeatRunnable != null) {
                    heartHandler.post(heartBeatRunnable);
                }
            }
        }
    }

    Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            LogUtil.d(TAG, "heartBeat receive isReceivePong:" + isReceivePong);
            if (heartHandler != null) {
                if (isReceivePong) {
                    isReceivePong = false;
                    disReceivePongIndex = 0;
                    ping();
                    heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
                } else {
                    if (disReceivePongIndex <= MAX_DIS_RECEIVE_NUM) {
                        disReceivePongIndex++;
                        ping();
                        heartHandler.postDelayed(this, GLOBAL_HEART_BEAT_RATE);
                    } else {
                        isReceivePong = true;
                        disReceivePongIndex = 0;
                        reconnect();
                    }
                }
            } else {
                disconnect(1001, "heart beat is disconnect reason:heartHandler is null ERROR!");
            }
        }
    };

    public void joinChannel(String channelId, String token, int uid, int clientRole, int callType) {
        RtcSpUtils.getInstance().setChannelId(channelId);
        mChannelId = channelId;
        mUid = String.valueOf(uid);
        mToken = token;
        isCallIng = true;
        mClientRole = clientRole;
        mCallType = callType;
    }

    public void leaveChannel() {
        RtcSpUtils.getInstance().setChannelId("");
        mChannelId = "";
        mToken = "";
        isCallIng = false;
    }

    protected void ping() {
        LogUtil.d(TAG, "rtc ping is start" + " isCallIng:" + isCallIng + " channelId:" + mChannelId);
        Api.ping ping;
        Api.ping.Builder builder = Api.ping.newBuilder();
        if (!TextUtils.isEmpty(mChannelId)) {
            builder.setChannelId(mChannelId);
        } else {
            if (isCallIng) {
                String channelId = RtcSpUtils.getInstance().getChannelId();
                if (!TextUtils.isEmpty(channelId)) {
                    mChannelId = channelId;
                    builder.setChannelId(mChannelId);
                } else {
                    for (Map.Entry<String, IRtcEngineEventCallBackHandler> entry : callBackHandlerHashMap.entrySet()) {
                        IRtcEngineEventCallBackHandler iRtcEngineEventCallBackHandler = entry.getValue();
                        if (iRtcEngineEventCallBackHandler != null) {
                            iRtcEngineEventCallBackHandler.closeRoom(Constants.ROOM_STATE_ERROR);
                        }
                    }
                }
            }
        }
        ping = builder.build();
        byte[] bytes = DataUtils.assembleData(0x85e792c3, ping.toByteArray());
        LogUtil.d(TAG, "rtc ping send data:" + Arrays.toString(bytes));
        send(ByteString.of(bytes));
    }

    public String sendMsg(int localUid, String msg, ChannelMsgListener channelMsgListener) {
        LogUtil.d(TAG, "send messenger");
        this.channelMsgListener = channelMsgListener;
        if (!TextUtils.isEmpty(mChannelId)) {
            String msgFp = String.valueOf(NettyMsg.getInstance().getMessageID());
            ChannelIm.channelMsgRecord.Builder channelMsgRecord = ChannelIm.channelMsgRecord.newBuilder();
            channelMsgRecord.setMsgFp(msgFp);
            channelMsgRecord.setFrom(String.valueOf(localUid));
            channelMsgRecord.setChannelId(mChannelId);
            channelMsgRecord.setMsg(msg);
            long currentTimeMillis = System.currentTimeMillis();
            channelMsgRecord.setSendTime(currentTimeMillis);
            byte[] bytes = DataUtils.assembleData(0xc60f6256, channelMsgRecord.build().toByteArray());
            LogUtil.d(TAG, "rtc sendMsg data:" + Arrays.toString(bytes));
            send(ByteString.of(bytes));
            return msgFp;
        } else {
            return "channel does not exist.";
        }
    }

    public void getMediaFile(String awsKey, String msgFp, int mediaType, long account, String downloadPath, DownloadListener downloadListener) {
        S3AwsHelper.getInstance().downloadWithTransferUtility(awsKey, msgFp, mediaType, account, downloadPath, new S3AwsHelper.IAWSFileRequest() {
            @Override
            public void aws_success(int requestType, String msgFp, String key) {
                downloadListener.downloadResult(msgFp);
            }

            @Override
            public void aws_progress(int requestType, String msgFp, int progress) {
                downloadListener.downloadProgress(progress);
            }

            @Override
            public void aws_error(int requestType, String msgFp, int error_code, String error_msg) {
                downloadListener.downloadFail(error_code, error_msg);
            }
        });
    }

    public String broadcastMediaSend(MsgBase.paasMsgRecord.Builder msgBase, ChatMsgListener chatMsgListener) {
        this.chatMsgListener = chatMsgListener;
        PaasIm.paasImMsgSend paasImMsgSend = PaasIm.paasImMsgSend.newBuilder().setMsg(msgBase.build()).build();
        byte[] bytes = DataUtils.assembleData(0xa58faca5, paasImMsgSend.toByteArray());
        LogUtil.d(TAG, "broadcastMediaSend sendMediaMsg data:" + Arrays.toString(bytes));
        send(ByteString.of(bytes));
        return msgBase.getMsgFp();
    }

    public String sendMediaMsg(String mUid, String peerUid, boolean isBroadcaster, long account, String filePath, int mediaType, String nickName, String avatar, ChatMsgListener chatMsgListener) {
        File file = new File(filePath);
        if (file.exists()) {
            String fileSuffix = "";
            String hash = "";
            this.chatMsgListener = chatMsgListener;
            MediaBase.mediaImagePreview.Builder mediaImagePreview = null;
            MediaBase.mediaImage.Builder mediaImage = null;
            MediaBase.mediaAudio.Builder mediaAudio = null;

            String msgFp = String.valueOf(NettyMsg.getInstance().getMessageID());
            UserBase.userInfo.Builder userInfo = UserBase.userInfo.newBuilder();
            userInfo.setName(nickName);
            userInfo.setAvatar(avatar);
            MediaBase.mediaRecord.Builder mediaRecord = MediaBase.mediaRecord.newBuilder();
            MsgBase.paasMsgRecord.Builder msgBase = MsgBase.paasMsgRecord.newBuilder();
            msgBase.setMsgFp(msgFp);
            MsgBase.UserId from = MsgBase.UserId.newBuilder().setId(mUid).build();
            MsgBase.UserId.Builder builder = MsgBase.UserId.newBuilder();
            if (account != 0L) {
                builder.setId(peerUid).setType(isBroadcaster ? MsgBase.UserType.BROADCASTER : MsgBase.UserType.USER).setAccount(account).build();
            } else {
                builder.setId(peerUid).setType(isBroadcaster ? MsgBase.UserType.BROADCASTER : MsgBase.UserType.USER).build();
            }
            msgBase.setFrom(from);
            msgBase.setTo(builder.build());
            msgBase.setMsgType(mediaType);
            msgBase.setSendTime(System.currentTimeMillis());
            msgBase.setUser(userInfo.build());
            mediaRecord.setMediaType(mediaType);
            fileSuffix = DataUtils.getFileSuffix(file.getName());
            hash = DataUtils.fileToHash(filePath);
            if (mediaType == Constants.MSG_SEND_IMAGE) {
                mediaImagePreview = MediaBase.mediaImagePreview.newBuilder().setSize((int) file.length()).setHash(hash).setUrl(filePath).setHeight(DataUtils.fileToBitmap(filePath).getHeight()).setWight(DataUtils.fileToBitmap(filePath).getWidth());
                mediaImage = MediaBase.mediaImage.newBuilder().setExt(fileSuffix);
                msgBase.setMedia(mediaRecord.setMediaContent(com.google.protobuf.ByteString.copyFrom(mediaImage.build().toByteArray())).build());
            } else if (mediaType == Constants.MSG_SEND_VOICE) {
                mediaAudio = MediaBase.mediaAudio.newBuilder().setExtName(fileSuffix).setSize((int) file.length()).setHash(hash).setName(file.getName()).setUrl(filePath).setTimeLen((int) DataUtils.getMediaTime(filePath));
                msgBase.setMedia(mediaRecord.setMediaContent(com.google.protobuf.ByteString.copyFrom(mediaAudio.build().toByteArray())).build());
            }
            MessageLoopThread.getInstance().addWaitMsg(chatMsgListener, msgFp, msgBase.getMsgType() + "_" + msgBase.getSendTime());
            MessageHelper.getSingleton().insertOrReplaceDataData(msgBase.build());
            MediaBase.mediaImage.Builder finalMediaImage = mediaImage;
            MediaBase.mediaAudio.Builder finalMediaAudio = mediaAudio;
            MediaBase.mediaImagePreview.Builder finalMediaImagePreview = mediaImagePreview;
            S3AwsHelper.getInstance().uploadWithTransferUtility(msgFp, filePath, fileSuffix, hash, mediaType, account, new S3AwsHelper.IAWSFileRequest() {
                @Override
                public void aws_success(int requestType, String msgFp, String key) {
                    byte[] byteData = new byte[0];
                    int msgType = msgBase.getMsgType();
                    if (msgType == Constants.MSG_SEND_IMAGE) {
                        assert finalMediaImage != null;
                        finalMediaImagePreview.setUrl(key);
                        finalMediaImage.setPreview(finalMediaImagePreview.build());
                        byteData = finalMediaImage.build().toByteArray();
                    } else if (msgType == Constants.MSG_SEND_VOICE) {
                        assert finalMediaAudio != null;
                        finalMediaAudio.setUrl(key);
                        byteData = finalMediaAudio.build().toByteArray();
                    }
                    mediaRecord.setMediaContent(com.google.protobuf.ByteString.copyFrom(byteData));
                    msgBase.setMedia(mediaRecord.build());
                    PaasIm.paasImMsgSend paasImMsgSend = PaasIm.paasImMsgSend.newBuilder().setMsg(msgBase.build()).build();
                    byte[] bytes = DataUtils.assembleData(0xa58faca5, paasImMsgSend.toByteArray());
                    LogUtil.d(TAG, "rtc sendTextMsg data:" + Arrays.toString(bytes));
                    send(ByteString.of(bytes));
                }

                @Override
                public void aws_progress(int requestType, String msgFp, int progress) {
                    MessageHelper.getSingleton().modifyMessageProgress(msgFp, progress);
                    chatMsgListener.sendProgress(msgFp, progress);
                }

                @Override
                public void aws_error(int requestType, String msgFp, int error_code, String error_msg) {
                    MessageHelper.getSingleton().modifyMessageState(msgFp, Constants.SEND_FAIL); //消息发送成功，对方还未阅读
                    chatMsgListener.sendFail(msgFp, error_code, error_msg);
                }
            });
            return msgFp;
        } else {
            return "file does not exist";
        }
    }

    public String sendTextMsg(String mUid, String peerUid, boolean isBroadcast, long account, String msg, String nickName, String avatar, ChatMsgListener chatMsgListener) {
        LogUtil.d(TAG, "sendTextMsg mUid:" + mUid + " peerUid:" + peerUid + " isBroadcast:" + isBroadcast + " msg:" + msg + " nickName:" + nickName + " avatar:" + avatar + " account:" + account);
        this.chatMsgListener = chatMsgListener;
        String msgFp = String.valueOf(NettyMsg.getInstance().getMessageID());
        UserBase.userInfo.Builder userInfo = UserBase.userInfo.newBuilder();
        userInfo.setName(nickName);
        userInfo.setAvatar(avatar);
        MsgBase.paasMsgRecord.Builder msgBase = MsgBase.paasMsgRecord.newBuilder();
        msgBase.setMsgFp(msgFp);
        MsgBase.UserId from = MsgBase.UserId.newBuilder().setId(mUid).build();
        MsgBase.UserId.Builder builder = MsgBase.UserId.newBuilder();
        if (account != 0L) {
            builder.setId(peerUid).setType(isBroadcast ? MsgBase.UserType.BROADCASTER : MsgBase.UserType.USER).setAccount(account).build();
        } else {
            builder.setId(peerUid).setType(isBroadcast ? MsgBase.UserType.BROADCASTER : MsgBase.UserType.USER).build();
        }
        msgBase.setFrom(from);
        msgBase.setTo(builder.build());
        msgBase.setMsgTxt(msg);
        msgBase.setMsgType(Constants.MSG_SEND_TEXT);
        msgBase.setSendTime(System.currentTimeMillis());
        msgBase.setUser(userInfo.build());
        PaasIm.paasImMsgSend paasImMsgSend = PaasIm.paasImMsgSend.newBuilder().setMsg(msgBase.build()).build();
        byte[] bytes = DataUtils.assembleData(0xa58faca5, paasImMsgSend.toByteArray());
        LogUtil.d(TAG, "rtc sendTextMsg data:" + Arrays.toString(bytes));
        send(ByteString.of(bytes));
        MessageLoopThread.getInstance().addWaitMsg(chatMsgListener, msgFp, msgBase.getMsgType() + "_" + msgBase.getSendTime());
        MessageHelper.getSingleton().insertOrReplaceDataData(msgBase.build());
        chatMsgListener.sendResult(msgFp, Constants.SENDING);
        return msgFp;
    }

    public void resendMsg(String fp, ChatMsgListener chatMsgListener) {
        LogUtil.d(TAG, "resendMsg fp:" + fp);
        MsgBean oneMessage = MessageHelper.getSingleton().getOneMessage(fp);
        if (oneMessage.getSourceType() == Constants.MSG_SEND_TEXT) {
            sendTextMsg(oneMessage.getUid(), oneMessage.getPeerUid(), oneMessage.getIsBroadcaster(), oneMessage.getAccount(), oneMessage.getContent(), oneMessage.getNickName(), oneMessage.getAvatar(), chatMsgListener);
        } else {
            //TODO sendMediaMsg
        }
    }

    public void getStates() {
        long lastPts = MessageHelper.getSingleton().getLastPts();
        LogUtil.d(TAG, "getStates lastPts:" + lastPts);
        PaasIm.getStates getStates = PaasIm.getStates.newBuilder().setPts(lastPts).build();
        byte[] bytes = DataUtils.assembleData(0x5b7af35f, getStates.toByteArray());
        send(ByteString.of(bytes));
    }

    public List<ChatListBean> getChatList(String uid) {
        LogUtil.d(TAG, "getChatList is start uid:" + uid);
        return ChatListHelper.getSingleton().getAllChatList(uid);
    }

    public List<MsgBean> getChatMsgList(String uid, String peerUid, int userType, long account) {
        LogUtil.d(TAG, "getChatMsgList is start uid:" + uid + " peerUid:" + peerUid + " userType:" + userType + " account:" + account);
        return MessageHelper.getSingleton().getAllData(uid, peerUid, userType, account);
    }

    public void getChatDiffMsg() {
        long lastPts = MessageHelper.getSingleton().getLastPts();
        long lastPtsDate = MessageHelper.getSingleton().getLastPtsDate();
        LogUtil.d(TAG, "getChatDiffMsg lastPts:" + lastPts + " lastPtsDate:" + lastPtsDate);
        UpdatesBase.updatGetDifference updatGetDifference = UpdatesBase.updatGetDifference.newBuilder().setPts(lastPts).setDate(lastPtsDate).setLimit(Constants.LIMIT).build();
        PaasIm.reqDifference reqDifference = PaasIm.reqDifference.newBuilder().setUid(RtcSpUtils.getInstance().getUserUid()).setReq(updatGetDifference.toByteString()).build();
        byte[] bytes = DataUtils.assembleData(0x961e73bb, reqDifference.toByteArray());
        send(ByteString.of(bytes));
    }

    private void getChannelDiffMsg(int msgId) {
        LogUtil.d(TAG, "get different messenger msgId:" + msgId);
        ChannelIm.getDiffChannelMsgRecord.Builder getDiffChannelMsgRecord = ChannelIm.getDiffChannelMsgRecord.newBuilder();
        getDiffChannelMsgRecord.setChannelId(mChannelId);
        getDiffChannelMsgRecord.setMsgId(msgId);
        byte[] bytes = DataUtils.assembleData(0xebb76c1e, getDiffChannelMsgRecord.build().toByteArray());
        send(ByteString.of(bytes));
    }

    /**
     * send msg
     */
    protected void send(final ByteString message) {
        if (isConnect()) {
            mWebSocket.send(message);
        } else {
            LogUtil.e(TAG, "send fail ,disconnected");
        }
    }

    /**
     * register listener
     */
    public void registerWSDataListener(int tag, WebSocketResultListener listener) {
        if (sWeakRefListeners.get(tag) == null) {
            sWeakRefListeners.put(tag, new WeakReference<>(listener));
        }
    }

    private void eventSuccessWsDataListener(int tag, String data) {
        LogUtil.e(TAG, "eventSuccessWsDataListener is start tag:" + tag);
        WeakReference<WebSocketResultListener> webSocketDataListenerWeakReference = sWeakRefListeners.get(tag);
        if (webSocketDataListenerWeakReference != null) {
            WebSocketResultListener webSocketResultListener = webSocketDataListenerWeakReference.get();
            webSocketResultListener.onSuccess(tag, data);
            unregisterWSDataListener(tag);
        }
    }

    public void eventFailWsDataListener(int tag, int code, String error) {
        LogUtil.e(TAG, "eventFailWsDataListener is start tag:" + tag);
        WeakReference<WebSocketResultListener> webSocketDataListenerWeakReference = sWeakRefListeners.get(tag);
        if (webSocketDataListenerWeakReference != null) {
            WebSocketResultListener webSocketResultListener = webSocketDataListenerWeakReference.get();
            webSocketResultListener.onFailure(code, error);
            unregisterWSDataListener(tag);
        }
    }

    /**
     * unregister listener
     */
    private void unregisterWSDataListener(int tag) {
        LogUtil.e(TAG, "unregisterWSDataListener is start tag:" + tag);
        if (sWeakRefListeners.get(tag) != null) {
            sWeakRefListeners.remove(tag);
        }
    }

    public interface WebSocketResultListener {
        void onSuccess(int code, String data);

        void onFailure(int code, String reason);
    }

    public HashMap<String, String> getAwsTranslateMap() {
        return awsTranslateMap;
    }
}
