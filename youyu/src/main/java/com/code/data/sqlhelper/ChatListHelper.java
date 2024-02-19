package com.code.data.sqlhelper;

import com.code.data.GreenDaoHelper;
import com.code.data.greendao.ChatListBeanDao;
import com.code.data.greendao.DaoSession;
import com.code.data.sqlbean.ChatListBean;
import com.code.data.sqlbean.MsgBean;
import com.code.data.sqlbean.UserInfoBean;
import com.code.utils.LogUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatListHelper implements GreenDaoHelper.GreenDaoInitResultListener {
    private static final String EVENT_INSERT = "insert";
    private static final String EVENT_DELETE = "delete";
    private static final String EVENT_DELETE_ALL = "deleteAll";
    private static final String EVENT_QUERY_LIST_BEAN = "getChatListBean";
    private static final String EVENT_GET_ALL_DATA = "getAllChatList";
    private static final String TAG = "ChatListHelper";
    private ChatListBeanDao chatListBeanDao;
    private ChatListDataChangeListener dataListener;
    private QueryResultListener queryResultListener;
    private final HashMap<String, Object> eventMap = new HashMap<>();

    public ChatListHelper() {
        GreenDaoHelper.getSingleTon().setGreenDaoInitResultListener(TAG, this);
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            chatListBeanDao = daoSession.getChatListBeanDao();
        } else {
            LogUtil.e(TAG, "daoSession is null");
        }
    }

    private static ChatListHelper chatListHelper;

    public static ChatListHelper getSingleton() {
        if (chatListHelper == null) {
            synchronized (ChatListHelper.class) {
                if (chatListHelper == null) {
                    chatListHelper = new ChatListHelper();
                }
            }
        }
        return chatListHelper;
    }

    @Override
    public void result(boolean mInitState) {
        UserInfoBean userInfoBean;
        if (mInitState) {
            for (Map.Entry<String, Object> entry : eventMap.entrySet()) {
                String key = entry.getKey();
                Object data = entry.getValue();
                switch (key) {
                    case EVENT_INSERT:
                        eventMap.remove(key);
                        insertOrReplaceData((MsgBean) data);
                        break;
                    case EVENT_DELETE:
                        eventMap.remove(key);
                        userInfoBean = (UserInfoBean) data;
                        delete(userInfoBean.getUid(), userInfoBean.getPeerUid(), userInfoBean.getUserType(), userInfoBean.getAccount());
                        break;
                    case EVENT_DELETE_ALL:
                        eventMap.remove(key);
                        deleteAll((String) data);
                        break;
                    case EVENT_QUERY_LIST_BEAN:
                        eventMap.remove(key);
                        userInfoBean = (UserInfoBean) data;
                        getChatListBean(userInfoBean.getUid(), userInfoBean.getPeerUid(), userInfoBean.getUserType(), userInfoBean.getAccount(), queryResultListener);
                        break;
                    case EVENT_GET_ALL_DATA:
                        eventMap.remove(key);
                        getAllChatList((String) data, queryResultListener);
                        break;
                }
            }
        }
    }

    public interface ChatListDataChangeListener {
        void addOneList(ChatListBean chatListBean);

        void dataChange(ChatListBean chatListBean);

        void changeUnReadCount(String peerUid, int count);

        void delete(String peerUid);

        void deleteAll();
    }

    public void setChatListDataChangeListener(ChatListDataChangeListener dataListener) {
        this.dataListener = dataListener;
    }

    public ChatListDataChangeListener getChatListDataChangeListener() {
        return dataListener;
    }

    public void removeChatListDataChangeListener() {
        this.dataListener = null;
    }

    public void insertOrReplaceData(MsgBean msgBean) {
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            chatListBeanDao = daoSession.getChatListBeanDao();
        }
        if (chatListBeanDao == null) {
            eventMap.put(EVENT_INSERT, msgBean);
            return;
        }
        ChatListBean unique = getChatListBean(msgBean.getUid(), msgBean.getPeerUid(), msgBean.getUserType(), msgBean.getAccount());
        if (unique == null) {
            LogUtil.d(TAG, "insertData this is one new data");
            ChatListBean chatListBean = new ChatListBean();
            chatListBean.setMUid(msgBean.getUid());
            chatListBean.setPeerUid(msgBean.getPeerUid());
            chatListBean.setNickName(msgBean.getNickName());
            chatListBean.setSendState(msgBean.getState());
            chatListBean.setUserAvatar(msgBean.getAvatar());
            chatListBean.setOldMessageTime(msgBean.getActualTime());
            chatListBean.setOldMessage(msgBean.getContent());
            chatListBean.setUnreadMsgCount(1);
            chatListBean.setAccount(msgBean.getAccount());
            chatListBean.setSourceType(msgBean.getSourceType());
            chatListBean.setUserType(msgBean.getUserType());
            chatListBean.setStatus(msgBean.getStatus());
            chatListBeanDao.insertOrReplace(chatListBean);
            if (dataListener != null) {
                dataListener.addOneList(chatListBean);
            } else {
                LogUtil.d(TAG, "InsertSingleData::unique is null->insert page error");
            }
        } else {
            LogUtil.d(TAG, "insertData update data");
            int unreadMsgCount = unique.getUnreadMsgCount();
            unreadMsgCount += 1;
            unique.setUnreadMsgCount(unreadMsgCount);
            unique.setOldMessage(msgBean.getContent());
            unique.setOldMessageTime(msgBean.getActualTime());
            unique.setNickName(msgBean.getNickName());
            unique.setUserAvatar(msgBean.getAvatar());
            unique.setSourceType(msgBean.getSourceType());
            unique.setAccount(msgBean.getAccount());
            unique.setUserType(msgBean.getUserType());
            unique.setStatus(msgBean.getStatus());
            chatListBeanDao.update(unique);
            if (dataListener != null) {
                dataListener.dataChange(unique);
            } else {
                LogUtil.d(TAG, "InsertSingleData::unique is null->insert page error");
            }
        }
    }

    public void delete(String mUid, String peerId, int userType, long account) {
        LogUtil.d(TAG, "delete is start mUid:" + mUid + " peerId:" + peerId + " userType:" + userType + " account:" + account);
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            chatListBeanDao = daoSession.getChatListBeanDao();
        }
        if (chatListBeanDao == null) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(mUid);
            userInfoBean.setPeerUid(peerId);
            userInfoBean.setUserType(userType);
            userInfoBean.setAccount(account);
            eventMap.put(EVENT_DELETE, userInfoBean);
            return;
        }
        QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
        ChatListBean unique = qb.where(ChatListBeanDao.Properties.MUid.eq(mUid), ChatListBeanDao.Properties.PeerUid.eq(peerId), ChatListBeanDao.Properties.UserType.eq(userType), ChatListBeanDao.Properties.Account.eq(account)).build().forCurrentThread().unique();
        if (unique != null) {
            chatListBeanDao.delete(unique);
        }
        if (dataListener != null) {
            dataListener.delete(peerId);
        }
        MessageHelper.getSingleton().deleteSomeOneAllMsg(mUid, peerId, userType, account);
        LogUtil.d(TAG, "delete is end");
    }

    public void deleteAll(String mUid) {
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            chatListBeanDao = daoSession.getChatListBeanDao();
        }
        if (chatListBeanDao == null) {
            eventMap.put(EVENT_DELETE_ALL, mUid);
            return;
        }
        ChatListBeanDao tempChatMessageBeanDao = GreenDaoHelper.getSingleTon().getmDaoSession().getChatListBeanDao();
        QueryBuilder<ChatListBean> tempChatMessageBeanQueryBuilder = tempChatMessageBeanDao.queryBuilder();
        List<ChatListBean> list = tempChatMessageBeanQueryBuilder.where(ChatListBeanDao.Properties.MUid.eq(mUid)).build().forCurrentThread().list();
        tempChatMessageBeanDao.deleteInTx(list);
        if (dataListener != null) {
            dataListener.deleteAll();
        }
    }

    private ChatListBean getChatListBean(String mUid, String peerUid, int userType, long account) {
        try {
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid), ChatListBeanDao.Properties.PeerUid.eq(peerUid), ChatListBeanDao.Properties.UserType.eq(userType), ChatListBeanDao.Properties.Account.eq(account)).build().forCurrentThread().unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getChatListBean(String mUid, String peerUid, int userType, long account, QueryResultListener listener) {
        try {
            DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
            if (daoSession != null) {
                chatListBeanDao = daoSession.getChatListBeanDao();
            }
            queryResultListener = listener;
            if (chatListBeanDao == null) {
                UserInfoBean userInfoBean = new UserInfoBean();
                userInfoBean.setUid(mUid);
                userInfoBean.setPeerUid(peerUid);
                userInfoBean.setUserType(userType);
                userInfoBean.setAccount(account);
                eventMap.put(EVENT_QUERY_LIST_BEAN, userInfoBean);
                return;
            }
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            ChatListBean unique = qb.where(ChatListBeanDao.Properties.MUid.eq(mUid), ChatListBeanDao.Properties.PeerUid.eq(peerUid), ChatListBeanDao.Properties.UserType.eq(userType), ChatListBeanDao.Properties.Account.eq(account)).build().forCurrentThread().unique();
            List<ChatListBean> list = new ArrayList<>();
            list.add(unique);
            listener.result(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllChatList(String mUid, QueryResultListener listener) {
        try {
            DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
            if (daoSession != null) {
                chatListBeanDao = daoSession.getChatListBeanDao();
            }
            queryResultListener = listener;
            if (chatListBeanDao == null) {
                LogUtil.e(TAG, "getAllChatList is start chatListBeanDao is null");
                eventMap.put(EVENT_GET_ALL_DATA, mUid);
                return;
            }
            LogUtil.d(TAG, "getAllChatList is start search mUid:" + mUid);
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            List<ChatListBean> list = qb.where(ChatListBeanDao.Properties.MUid.eq(mUid)).orderDesc(ChatListBeanDao.Properties.OldMessageTime).build().forCurrentThread().list();
            listener.result(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUnreadToCount(String mUid, String peerUid, int userType, long account, int count) {
        LogUtil.d(TAG, "setUnreadToCount::set unread count id is: " + peerUid + " count:" + count);
        if (count < 0) {
            count = 0;
        }
        final int finalCount = count;
        ChatListBean unique = getChatListBean(mUid, peerUid, userType, account);
        if (unique != null) {
            LogUtil.d(TAG, "SetUnreadToCount::do set unread");
            unique.setUnreadMsgCount(finalCount);
            if (chatListBeanDao != null) {
                chatListBeanDao.update(unique);
            }
            if (dataListener != null) {
                dataListener.changeUnReadCount(peerUid, finalCount);
            }
        } else {
            LogUtil.d(TAG, "SetUnreadToCount::can not find the user");
        }
    }

    public interface QueryResultListener {
        void result(List<ChatListBean> result);
    }
}
