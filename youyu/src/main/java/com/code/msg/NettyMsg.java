package com.code.msg;

import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;

public class NettyMsg {
    public static final String TAG = "NettyMsg";
    private static NettyMsg instance;

    public synchronized static NettyMsg getInstance() {
        synchronized (NettyMsg.class) {
            if (instance == null) {
                instance = new NettyMsg();
            }
        }
        return instance;
    }

    public long getMessageID() {
        long result;
        long messageId = RtcSpUtils.getInstance().getMessageId();
        LogUtil.d(TAG, "getMessageID::localMessage:" + messageId);
        result = (long) (System.currentTimeMillis() / 1000.0 * 4294967296.0);
        if (messageId == 0L) {
            LogUtil.d(TAG, "getMessageID::1 messageId:" + result);
        } else {
            LogUtil.d(TAG, "getMessageID::2 messageId:" + result);
            if (result == messageId) {
                result += 1;
                LogUtil.d(TAG, "getMessageID::3 messageId:" + result);
            }
        }
        RtcSpUtils.getInstance().setMessageId(result);
        LogUtil.d(TAG, "getMessageID::ok messageId:" + result);
        return result;
    }
}
