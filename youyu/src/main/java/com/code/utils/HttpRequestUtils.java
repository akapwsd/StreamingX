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

import io.agora.rtc.Constants;

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

    public void getModelAvatar(Context context, int modelId, RequestModelAvatarListListener requestModelAvatarListListener) {
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
    }

    public void getAccountAvatar(Context context, String token, int uid, RequestModelAvatarListListener requestModelAvatarListListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).getAccountAvatar(token, X_Uyj_Timestamp, uid).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

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
    }

    public void getModelList(Context context, int sort, int page, int limit, RequestModelListListener requestModelListListener) {
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
    }

    public void getModelChannelToken(Context context, String token, String channelId, HttpRequestListener httpRequestListener) {
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
    }

    public void getChannelToken(Context context, String channelId, HttpRequestListener httpRequestListener) {
        LogUtil.d(TAG, "getChannelToken is start channelId:" + channelId);
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
    }

    public void joinChannel(Context context, String token, String channelId, String peerUid, int category, HttpRequestListener httpRequestListener) {
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
    }

    public void createChannel(Context context, String token, int category, HttpRequestListener httpRequestListener) {
        LogUtil.d(TAG, "createChannel is start category:" + category);
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        CreateChannelBean createChannelBean = new CreateChannelBean();
        createChannelBean.setCategory(category);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).createChannel(token, X_Uyj_Timestamp, createChannelBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
                ChannelResultBean channelResultBean = (ChannelResultBean) o;
                ChannelInfoBean ch = channelResultBean.getCh();
                String channelId = ch.getId();
                String token = channelResultBean.getToken();
                String uniqId = channelResultBean.getUniqId();
                StreamingXRtcManager.getInstance().localUid = Integer.parseInt(uniqId);
                LogUtil.d(TAG, "createChannel success channelId:" + channelId + " token:" + token + " uniqId:" + uniqId);
                WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uniqId), Constants.CLIENT_ROLE_BROADCASTER, category);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void requestPhoneCode(Context context, String phone, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        NullBean nullBean = new NullBean();
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).registerWithPhone(X_Uyj_Timestamp, phone, nullBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void validateSmsCode(Context context, String receipt, String code, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        SmsCodeBean smsCodeBean = new SmsCodeBean();
        smsCodeBean.setSmsCode(code);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).validateSmsCode(X_Uyj_Timestamp, receipt, smsCodeBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void uploadNickname(Context context, String nickName, String token, int uid, HttpRequestListener httpRequestListener) {
        UploadUserInfoBean uploadUserInfoBean = new UploadUserInfoBean();
        uploadUserInfoBean.setNick(nickName);
        uploadUserInfoBean.setUid(uid);
        uploadUserInfo(context, token, uploadUserInfoBean, httpRequestListener);
    }

    public void uploadBirthday(Context context, String birthday, String token, int uid, HttpRequestListener httpRequestListener) {
        UploadUserInfoBean uploadUserInfoBean = new UploadUserInfoBean();
        uploadUserInfoBean.setBirthday(birthday);
        uploadUserInfoBean.setUid(uid);
        uploadUserInfo(context, token, uploadUserInfoBean, httpRequestListener);
    }

    public void uploadUserInfo(Context context, String token, UploadUserInfoBean userInfoBean, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).uploadUserInfo(token, X_Uyj_Timestamp, userInfoBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void applyModel(Context context, String token, UploadUserInfoBean userInfoBean, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).applyModel(token, X_Uyj_Timestamp, userInfoBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void checkApplyStatus(Context context, String token, int uid, int aType, int page, int limit, int aState, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).checkApplyStatus(token, X_Uyj_Timestamp, uid, aType, page, limit, aState).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void uploadAvatar(Context context, String token, String key, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        UploadAvatarBean uploadAvatarBean = new UploadAvatarBean();
        uploadAvatarBean.setMd5(Md5Utils.md5(key));
        uploadAvatarBean.setAvatarOriginalUrl(key);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).uploadAvatar(token, X_Uyj_Timestamp, uploadAvatarBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void setDefaultAvatar(Context context, int uid, String token, String md5, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).setDefaultAvatar(token, X_Uyj_Timestamp, uid, md5).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void deleteAvatar(Context context, int uid, String token, String md5, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).deleteAvatar(token, X_Uyj_Timestamp, uid, md5).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void getAccountInfo(Context context, int uid, String token, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).getAccountInfo(token, X_Uyj_Timestamp, uid).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void getToken(Context context, int uid, String token, HttpRequestListener httpRequestListener) {
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        RetrofitHelper.createApi(HttpApi.class, context, RetrofitHelper.MODEL).getToken(token, X_Uyj_Timestamp, uid).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }
}
