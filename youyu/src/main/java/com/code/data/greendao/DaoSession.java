package com.code.data.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.code.data.sqlbean.ChatListBean;
import com.code.data.sqlbean.MsgBean;
import com.code.data.sqlbean.UserInfoBean;

import com.code.data.greendao.ChatListBeanDao;
import com.code.data.greendao.MsgBeanDao;
import com.code.data.greendao.UserInfoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig chatListBeanDaoConfig;
    private final DaoConfig msgBeanDaoConfig;
    private final DaoConfig userInfoBeanDaoConfig;

    private final ChatListBeanDao chatListBeanDao;
    private final MsgBeanDao msgBeanDao;
    private final UserInfoBeanDao userInfoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        chatListBeanDaoConfig = daoConfigMap.get(ChatListBeanDao.class).clone();
        chatListBeanDaoConfig.initIdentityScope(type);

        msgBeanDaoConfig = daoConfigMap.get(MsgBeanDao.class).clone();
        msgBeanDaoConfig.initIdentityScope(type);

        userInfoBeanDaoConfig = daoConfigMap.get(UserInfoBeanDao.class).clone();
        userInfoBeanDaoConfig.initIdentityScope(type);

        chatListBeanDao = new ChatListBeanDao(chatListBeanDaoConfig, this);
        msgBeanDao = new MsgBeanDao(msgBeanDaoConfig, this);
        userInfoBeanDao = new UserInfoBeanDao(userInfoBeanDaoConfig, this);

        registerDao(ChatListBean.class, chatListBeanDao);
        registerDao(MsgBean.class, msgBeanDao);
        registerDao(UserInfoBean.class, userInfoBeanDao);
    }
    
    public void clear() {
        chatListBeanDaoConfig.clearIdentityScope();
        msgBeanDaoConfig.clearIdentityScope();
        userInfoBeanDaoConfig.clearIdentityScope();
    }

    public ChatListBeanDao getChatListBeanDao() {
        return chatListBeanDao;
    }

    public MsgBeanDao getMsgBeanDao() {
        return msgBeanDao;
    }

    public UserInfoBeanDao getUserInfoBeanDao() {
        return userInfoBeanDao;
    }

}
