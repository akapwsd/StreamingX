package com.code.data.sqlhelper;


import com.code.data.GreenDaoHelper;
import com.code.data.greendao.ChatListBeanDao;
import com.code.data.greendao.DaoSession;
import com.code.data.sqlbean.ChatListBean;
import com.code.data.sqlbean.MsgBean;
import com.code.utils.LogUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class ChatListHelper {
    private static final String TAG = "TempChatListHelper";
    private ChatListBeanDao chatListBeanDao;
    private ChatListDataChangeListener dataListener;

    public ChatListHelper() {
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            chatListBeanDao = daoSession.getChatListBeanDao();
        }
    }

    private static ChatListHelper chatListHelper;

    public static ChatListHelper getSingleton() {
        if (chatListHelper == null) {
            chatListHelper = new ChatListHelper();
        }
        return chatListHelper;
    }

    public interface ChatListDataChangeListener {
        void addOneList(ChatListBean chatListBean);

        void dataChange(ChatListBean chatListBean);

        void changeUnReadCount(String peerUid, int count);

        void delete(int peerUid);

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

    public void insertData(MsgBean msgBean) {
        ChatListBean unique = getChatListBean(msgBean.getMUid(), msgBean.getPeerUid());
        if (unique == null) {
            ChatListBean chatListBean = new ChatListBean();
            chatListBean.setUnreadMsgCount(1);
            if (chatListBeanDao != null) {
                chatListBeanDao.insertOrReplace(chatListBean);
            }
            if (dataListener != null) {
                dataListener.addOneList(chatListBean);
            } else {
                LogUtil.d(TAG, "InsertSingleData::unique is null->insert page error");
            }
        } else {
            int unreadMsgCount = unique.getUnreadMsgCount();
            unreadMsgCount += 1;
            unique.setUnreadMsgCount(unreadMsgCount);
        }
        if (chatListBeanDao != null) {
            chatListBeanDao.update(unique);
        }
        if (dataListener != null) {
            dataListener.dataChange(unique);
        } else {
            LogUtil.d(TAG, "InsertSingleData::unique not null->insert page error");
        }
    }

    public ChatListBean select(int mUid, int peerUid) {
        if (chatListBeanDao == null) {
            return null;
        }
        QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
        return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid),
                        ChatListBeanDao.Properties.PeerUid.eq(peerUid))
                .build()
                .forCurrentThread()
                .unique();
    }

    public void delete(int mUid, int peerId) {
        if (chatListBeanDao == null) {
            return;
        }
        QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
        ChatListBean unique = qb.where(ChatListBeanDao.Properties.MUid.eq(mUid),
                        ChatListBeanDao.Properties.PeerUid.eq(peerId))
                .build()
                .forCurrentThread()
                .unique();
        if (unique != null) {
            chatListBeanDao.delete(unique);
        }
        if (dataListener != null) {
            dataListener.delete(peerId);
        }
        ChatListBeanDao tempChatMessageBeanDao = GreenDaoHelper.getSingleTon().getmDaoSession().getChatListBeanDao();
        QueryBuilder<ChatListBean> tempChatMessageBeanQueryBuilder = tempChatMessageBeanDao.queryBuilder();
        List<ChatListBean> list = tempChatMessageBeanQueryBuilder.where(ChatListBeanDao.Properties.MUid.eq(mUid)
                        , ChatListBeanDao.Properties.PeerUid.eq(peerId))
                .build()
                .forCurrentThread()
                .list();
        if (list != null) {
            tempChatMessageBeanDao.deleteInTx(list);
        }
    }

    public void deleteAll(int mUid) {
        if (chatListBeanDao == null) {
            return;
        }
        ChatListBeanDao tempChatMessageBeanDao = GreenDaoHelper.getSingleTon().getmDaoSession().getChatListBeanDao();
        QueryBuilder<ChatListBean> tempChatMessageBeanQueryBuilder = tempChatMessageBeanDao.queryBuilder();
        List<ChatListBean> list = tempChatMessageBeanQueryBuilder.where(ChatListBeanDao.Properties.MUid.eq(mUid))
                .build()
                .forCurrentThread()
                .list();
        tempChatMessageBeanDao.deleteInTx(list);
        if (dataListener != null) {
            dataListener.deleteAll();
        }
    }

    public ChatListBean getChatListBean(String mUid, String peerUid) {
        try {
            if (chatListBeanDao == null) {
                return null;
            }
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid),
                            ChatListBeanDao.Properties.PeerUid.eq(peerUid))
                    .build()
                    .forCurrentThread()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateData(ChatListBean chatListBean) {
        try {
            if (chatListBeanDao == null) {
                return;
            }
            chatListBeanDao.update(chatListBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChatListBean> getAllChatList(int mUid) {
        try {
            if (chatListBeanDao == null) {
                return null;
            }
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid))
                    .orderDesc(ChatListBeanDao.Properties.OldMessageTime)
                    .build()
                    .forCurrentThread()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUnreadToCount(String mUid, String peerUid, int count) {
        LogUtil.d(TAG, "setUnreadToCount::set unread count id is: " + peerUid + " count:" + count);
        if (count < 0) {
            count = 0;
        }
        final int finalCount = count;
        ChatListBean unique = getChatListBean(mUid, peerUid);
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
}