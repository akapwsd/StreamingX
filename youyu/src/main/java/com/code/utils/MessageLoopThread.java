package com.code.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.code.data.sqlhelper.MessageHelper;
import com.code.listener.ChatMsgListener;
import com.code.youyu.api.Constants;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MessageLoopThread {
    private static final String TAG = "MessageLoopThread";
    private static MessageLoopThread messageLoopThread;
    private String lastMsgFP;
    private static final int TIME_OUT = 10000;
    private static final int IMAGE_VIDEO_TIME_OUT = TIME_OUT * 6 * 10;
    private ExcuteHandler mHandler;
    private boolean checkThreadWork;
    private ConcurrentHashMap<String, String> waitingReceiptMap;
    private ChatMsgListener chatMsgListener;

    public MessageLoopThread() {
        waitingReceiptMap = new ConcurrentHashMap<>();
        HandlerThread mHandlerThread = new HandlerThread("SingleMessageSendResultThread");
        mHandlerThread.start();
        mHandler = new ExcuteHandler(mHandlerThread.getLooper());
        mHandler.sendEmptyMessageDelayed(Constants.START_CHECK, 1000);
    }

    public synchronized static MessageLoopThread getInstance() {
        synchronized (MessageLoopThread.class) {
            if (messageLoopThread == null) {
                messageLoopThread = new MessageLoopThread();
            }
        }
        return messageLoopThread;
    }

    public synchronized void addWaitMsg(ChatMsgListener chatMsgListener, String fp, String msgInfo) {
        this.chatMsgListener = chatMsgListener;
        if (!TextUtils.isEmpty(fp)) {
            synchronized (waitingReceiptMap) {
                lastMsgFP = fp;
                waitingReceiptMap.put(fp, msgInfo);
                LogUtil.d(TAG, "addWaitHz fp is: " + fp + " and checkThreadWork is: " + checkThreadWork);
                if (!checkThreadWork) {
                    mHandler.sendEmptyMessageDelayed(Constants.CHECK_DATA, 500);
                }
            }
        }
    }

    public synchronized void removeReceivedMsg(String fp, int status) {
        LogUtil.d(TAG, "removeReceivedHz::fp is: " + fp + " and status is: " + status);
        if (!TextUtils.isEmpty(fp)) {
            if (status == Constants.SEND_SUCCESS) {
                boolean b = waitingReceiptMap.containsKey(fp);
                if (b) {
                    synchronized (waitingReceiptMap) {
                        waitingReceiptMap.remove(fp);
                        LogUtil.d(TAG, "removeReceivedHz::remove fp: " + fp);
                    }
                }
                MessageHelper.getSingleton().modifyMessageState(fp, Constants.SEND_SUCCESS); //消息发送成功，对方还未阅读
            } else {
                handSendFailMsg(fp);
            }
        }

    }

    private void handSendFailMsg(String fp) {
        if (fp.equalsIgnoreCase("temp") && !TextUtils.isEmpty(lastMsgFP)) {
            fp = lastMsgFP;
        } else {
            return;
        }
        if (waitingReceiptMap != null) {
            boolean b = waitingReceiptMap.containsKey(fp);
            if (b) {
                synchronized (waitingReceiptMap) {
                    waitingReceiptMap.remove(fp);
                    LogUtil.d(TAG, "handSendFailMsg::remove fp : " + fp);
                }
            } else {
                LogUtil.d(TAG, "handSendFailMsg::con not find the msg fp: " + fp);
                return;
            }
            MessageHelper.getSingleton().modifyMessageState(fp, Constants.SEND_FAIL);
            chatMsgListener.sendFail(Constants.SEND_FAIL, "send msg fail");
        }
    }

    private class ExcuteHandler extends Handler {
        public ExcuteHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.START_CHECK:
                    mHandler.sendEmptyMessageDelayed(Constants.CHECK_DATA, 500);
                    break;
                case Constants.CHECK_DATA:
                    if (waitingReceiptMap.size() == 0) {
                        LogUtil.d(TAG, "CHECK_DATA::stop check handle");
                        mHandler.removeMessages(Constants.CHECK_DATA);
                        checkThreadWork = false;
                        return;
                    }
                    checkThreadWork = true;
                    long nowTime = System.currentTimeMillis();
                    Set<Map.Entry<String, String>> entries = waitingReceiptMap.entrySet();
                    for (Map.Entry<String, String> entry : entries) {
                        try {
                            int outTime = TIME_OUT;
                            String fp = entry.getKey();
                            String messageInfo = entry.getValue();
                            if (!TextUtils.isEmpty(messageInfo)) {
                                String[] infoList = messageInfo.split("_");
                                if (infoList.length == 2) {
                                    int status = Integer.parseInt(infoList[0]);
                                    long time = Long.parseLong(infoList[1]);
                                    if (status == Constants.MSG_SEND_IMAGE || status == Constants.MSG_SEND_VOICE) {
                                        outTime = IMAGE_VIDEO_TIME_OUT;
                                    }
                                    if (nowTime - time > outTime) {
                                        MessageHelper.getSingleton().modifyMessageState(fp, Constants.SEND_FAIL);
                                        waitingReceiptMap.remove(fp);
                                        chatMsgListener.sendFail(Constants.SEND_TIMEOUT, "send msg time out");
                                    }
                                }
                            } else {
                                LogUtil.e(TAG, "CHECK_DATA::msg info is null");
                                MessageHelper.getSingleton().modifyMessageState(fp, Constants.SEND_FAIL);
                                waitingReceiptMap.remove(fp);
                                chatMsgListener.sendFail(Constants.SEND_TIMEOUT, "send msg time out");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendEmptyMessageDelayed(Constants.CHECK_DATA, 500);
                    LogUtil.d(TAG, "CHECK_DATA::end check loop");
                    break;
            }
        }
    }
}
