package com.code.utils;

import android.content.Context;

import com.code.bean.AwsStsBean;
import com.code.bean.ChannelInfoBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.MatchAttrBean;
import com.code.bean.MatchBean;
import com.code.bean.MatchExpectBean;
import com.code.bean.ModelCoverListBean;
import com.code.bean.ModelListBean;
import com.code.bean.SkipBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.RequestModelAvatarListListener;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;
import com.code.youyu.api.StreamingXRtcManager;

import java.util.HashMap;

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

    /**
     * @param context
     * @param country
     * @param language
     * @param gender
     * @param name
     * @param photoUrl
     * @param matchType
     * @param httpRequestListener
     */
    public void match(Context context, String country, String language, int peerGender, String name, String photoUrl, int gender, int matchType, HttpRequestListener httpRequestListener) {
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
                MatchBean matchBean = new MatchBean();
                MatchExpectBean matchExpectBean = new MatchExpectBean();
                matchExpectBean.setCountry(country);
                matchExpectBean.setLanguage(language);
                matchExpectBean.setGender(peerGender);
                MatchAttrBean matchAttrBean = new MatchAttrBean();
                matchAttrBean.setGender(gender);
                matchAttrBean.setName(name);
                matchAttrBean.setPhotoUrl(photoUrl);
                matchBean.setExpect(matchExpectBean);
                matchBean.setAttr(matchAttrBean);
                matchBean.setTs(X_Uyj_Timestamp);
                matchBean.setMatchType(matchType);
                RetrofitHelper.createApi(HttpApi.class, context).startMatch(authorization, X_Uyj_Timestamp, Content_Type, session_token, matchBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                    @Override
                    public void Success(Object object) {
                        httpRequestListener.requestSuccess(object);
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
            LogUtil.e(TAG, "match e:" + e);
        }
    }

    public void skip(Context context, String channelId, HttpRequestListener httpRequestListener) {
        try {
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
                    SkipBean skipBean = new SkipBean();
                    skipBean.setId(channelId);
                    RetrofitHelper.createApi(HttpApi.class, context).startSkip(authorization, X_Uyj_Timestamp, Content_Type, session_token, skipBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

                        @Override
                        public void Success(Object object) {
                            httpRequestListener.requestSuccess(object);
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
                LogUtil.e(TAG, "match e:" + e);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "match e:" + e);
        }
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

    public void getActiveModelList(Context context, int sort, int page, int limit, String languageIso, String country, RequestModelListListener requestModelListListener) {
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
                RetrofitHelper.createApi(HttpApi.class, context).getActiveModelList(authorization, X_Uyj_Timestamp, Content_Type, session_token, sort, page, limit, languageIso, country).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

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

    public void getModelList(Context context, int sort, int page, int limit, String languageIso, String country, RequestModelListListener requestModelListListener) {
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
                RetrofitHelper.createApi(HttpApi.class, context).getModelList(authorization, X_Uyj_Timestamp, Content_Type, session_token, sort, page, limit, languageIso, country).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

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

    public void requestAwsSts(Context context) {
        LogUtil.d(TAG, "requestAwsSts is start");
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
                RetrofitHelper.createApi(HttpApi.class, context).requestAwsSts(authorization, X_Uyj_Timestamp, Content_Type, session_token).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
                    @Override
                    public void Success(Object o) {
                        AwsStsBean stsBean = (AwsStsBean) o;
                        LogUtil.d(TAG, "requestAwsSts stsBean:" + stsBean);
                        String accessKeyId = stsBean.getAccessKeyId();
                        String accessKeySecret = stsBean.getAccessKeyId();
                        String sessionToken = stsBean.getSecurityToken();
                        String paasImPrefix = stsBean.getPaasImPrefix();
                        RtcSpUtils.getInstance().setPaasImPrefix(paasImPrefix);
                        HashMap<String, String> awsTranslateMap = WSManager.getInstance().getAwsTranslateMap();
                        awsTranslateMap.put("AccessKeyId", accessKeyId);
                        awsTranslateMap.put("SecretAccessKey", accessKeySecret);
                        awsTranslateMap.put("SessionToken", sessionToken);

                        WSManager.getInstance().initAWS(context);
                    }

                    @Override
                    public void error(int code, String error) {
                        LogUtil.e(TAG, "requestAwsSts fail code:" + code + " error:" + error);
                    }
                });
            } else {
                LogUtil.e(TAG, "init fail");
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "e:" + e);
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

    public void joinChannel(Context context, String channelId, String peerUid, int category, HttpRequestListener httpRequestListener) {
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
