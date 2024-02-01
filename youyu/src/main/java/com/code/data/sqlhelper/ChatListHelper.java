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
    private static final String TAG = "ChatListHelper";
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
            if (chatListBeanDao != null) {
                chatListBeanDao.insertOrReplace(chatListBean);
            }
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
            if (chatListBeanDao != null) {
                chatListBeanDao.update(unique);
            }
            if (dataListener != null) {
                dataListener.dataChange(unique);
            } else {
                LogUtil.d(TAG, "InsertSingleData::unique is null->insert page error");
            }
        }
    }

    public ChatListBean select(String mUid, String peerUid) {
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

    public void delete(String mUid, String peerId) {
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

    public void deleteAll(String mUid) {
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

    public ChatListBean getChatListBean(String mUid, String peerUid, int userType, long account) {
        try {
            if (chatListBeanDao == null) {
                return null;
            }
            QueryBuilder<ChatListBean> qb = chatListBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid),
                            ChatListBeanDao.Properties.PeerUid.eq(peerUid),
                            ChatListBeanDao.Properties.UserType.eq(userType),
                            ChatListBeanDao.Properties.Account.eq(account))
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

    public List<ChatListBean> getAllChatList(String mUid) {
        try {
            if (chatListBeanDao == null) {
                LogUtil.e(TAG, "getAllChatList is start chatListBeanDao is null");
                return null;
            }
            LogUtil.d(TAG, "getAllChatList is start search mUid:" + mUid);
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
}
