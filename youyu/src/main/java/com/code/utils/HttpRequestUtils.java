package com.code.utils;

import android.content.Context;

import com.code.bean.AvatarBean;
import com.code.bean.AvatarListBean;
import com.code.bean.ChannelInfoBean;
import com.code.bean.CreateChannelBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.ModelCoverListBean;
import com.code.bean.ModelListBean;
import com.code.bean.NullBean;
import com.code.bean.SmsCodeBean;
import com.code.bean.UploadAvatarBean;
import com.code.bean.UploadUserInfoBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.RequestModelAvatarListListener;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;
import com.code.youyu.api.StreamingXRtcManager;

import java.util.ArrayList;

import io.agora.rtc2.Constants;

public class HttpRequestUtils {
    private static HttpRequestUtils httpRequestUtils;
    public static final String TAG = "HttpRequestUtils";

    public static HttpRequestUtils getInstance() {
        if (httpRequestUtils == null) {
            synchronized (HttpRequestUtils.class) {
                if (httpRequestUtils == null) {
                    httpRequestUtils = new HttpRequestUtils();
                }
            }
        }
        return httpRequestUtils;
    }

    public void getUserState(Context context, int uid, HttpRequestListener httpRequestListener) {
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                RetrofitHelper.createApi(HttpApi.class, context).getAccountState(authorization, X_Uyj_Timestamp, Content_Type, session_token, uid).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                    @Override
                    public void Success(Object modelBeans) {
                        httpRequestListener.requestSuccess(modelBeans);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
            } else {
                httpRequestListener.requestError(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }
    }

    public void getUserInfo(Context context, int uid, HttpRequestListener httpRequestListener) {
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                RetrofitHelper.createApi(HttpApi.class, context).getAccountInfo(authorization, X_Uyj_Timestamp, Content_Type, session_token, uid).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                    @Override
                    public void Success(Object modelBeans) {
                        httpRequestListener.requestSuccess(modelBeans);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
            } else {
                httpRequestListener.requestError(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }
    }

    public void getModelAvatar(Context context, int modelId, RequestModelAvatarListListener requestModelAvatarListListener) {
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                RetrofitHelper.createApi(HttpApi.class, context).getModelAvatar(authorization, X_Uyj_Timestamp, Content_Type, session_token, modelId).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                    @Override
                    public void Success(Object modelBeans) {
                        ModelCoverListBean data = (ModelCoverListBean) modelBeans;
                        requestModelAvatarListListener.onResult(data);
                    }

                    @Override
                    public void error(int code, String error) {
                        requestModelAvatarListListener.onFailure(code, error);
                    }
                });
            } else {
                requestModelAvatarListListener.onFailure(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }
    }

    public void getModelList(Context context, int sort, int page, int limit, RequestModelListListener requestModelListListener) {
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                if (limit < 10) {
                    limit = 10;
                } else if (limit > 50) {
                    limit = 50;
                }
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                RetrofitHelper.createApi(HttpApi.class, context).getModelList(authorization, X_Uyj_Timestamp, Content_Type, session_token, sort, page, limit).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                    @Override
                    public void Success(Object modelBeans) {
                        ModelListBean data = (ModelListBean) modelBeans;
                        requestModelListListener.onResult(data);
                    }

                    @Override
                    public void error(int code, String error) {
                        requestModelListListener.onFailure(code, error);
                    }
                });
            } else {
                requestModelListListener.onFailure(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }
    }

    public void getModelChannelToken(Context context, String token, String channelId, HttpRequestListener httpRequestListener) {
        if (StreamingXRtcManager.getInstance().isInit) {
            long currentTimeMillis = System.currentTimeMillis();
            String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
            RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).getChannelToken(token, X_Uyj_Timestamp, channelId).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
                @Override
                public void Success(Object o) {
                    httpRequestListener.requestSuccess(o);
                }

                @Override
                public void error(int code, String error) {
                    httpRequestListener.requestError(code, error);
                }
            });
        } else {
            httpRequestListener.requestError(-1, "init fail");
        }
    }

    public void getChannelToken(Context context, String channelId, HttpRequestListener httpRequestListener) {
        LogUtil.d(TAG, "getChannelToken is start channelId:" + channelId);
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                RetrofitHelper.createApi(HttpApi.class, context).getChannelToken(authorization, X_Uyj_Timestamp, Content_Type, session_token, channelId).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
                    @Override
                    public void Success(Object o) {
                        httpRequestListener.requestSuccess(o);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
            } else {
                httpRequestListener.requestError(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }

    }

    public void joinChannel(Context context, String token, String channelId, String peerUid, int category, HttpRequestListener httpRequestListener) {
        try {
            if (StreamingXRtcManager.getInstance().isInit) {
                String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
                String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
                String session_token = RtcSpUtils.getInstance().getSessionToken();
                long currentTimeMillis = System.currentTimeMillis();
                String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
                String Content_Type = com.code.youyu.api.Constants.CONTENT_TYPE_JSON;
                String data = X_Uyj_Timestamp + Content_Type;
                String sign = DataUtils.sha256_HMAC(access_key_secret, data);
                String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
                JoinChannelBean joinChannelBean = new JoinChannelBean();
                joinChannelBean.setBroadcaster(peerUid);
                joinChannelBean.setToken(token);
                RetrofitHelper.createApi(HttpApi.class, context).joinChannel(authorization, X_Uyj_Timestamp, Content_Type, session_token, channelId, joinChannelBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
                    @Override
                    public void Success(Object o) {
                        httpRequestListener.requestSuccess(o);
                        ChannelResultBean channelResultBean = (ChannelResultBean) o;
                        ChannelInfoBean ch = channelResultBean.getCh();
                        String serverChannelId = ch.getId();
                        String token = channelResultBean.getToken();
                        String uniqId = channelResultBean.getUniqId();
                        StreamingXRtcManager.getInstance().localUid = Integer.parseInt(uniqId);
                        LogUtil.d(TAG, "joinChannel success channelId:" + channelId + " serverChannelId:" + serverChannelId + " token:" + token + " uniqId:" + uniqId);
                        if (channelId.equals(serverChannelId)) {
                            WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uniqId), Constants.CLIENT_ROLE_BROADCASTER, category);
                        }
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
            } else {
                httpRequestListener.requestError(-1, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
        }
    }
}
