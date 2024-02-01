package com.code.data.sqlhelper;

import com.code.data.GreenDaoHelper;
import com.code.data.greendao.ChatListBeanDao;
import com.code.data.greendao.DaoSession;
import com.code.data.greendao.UserInfoBeanDao;
import com.code.data.sqlbean.MsgBean;
import com.code.data.sqlbean.UserInfoBean;
import com.code.utils.LogUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class UserInfoHelper {
    private static final String TAG = "UserInfoHelper";
    private UserInfoBeanDao userInfoBeanDao;

    public UserInfoHelper() {
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            userInfoBeanDao = daoSession.getUserInfoBeanDao();
        }
    }

    private static UserInfoHelper userInfoHelper;

    public static UserInfoHelper getSingleton() {
        if (userInfoHelper == null) {
            userInfoHelper = new UserInfoHelper();
        }
        return userInfoHelper;
    }

    public void insertOrReplaceData(MsgBean msgBean) {
        UserInfoBean unique = getUserInfo(msgBean.getUid(), msgBean.getPeerUid(), msgBean.getUserType(), msgBean.getAccount());
        if (unique == null) {
            LogUtil.d(TAG, "insertData this is one new data");
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(msgBean.getUid());
            userInfoBean.setPeerUid(msgBean.getPeerUid());
            userInfoBean.setName(msgBean.getNickName());
            userInfoBean.setAvatar(msgBean.getAvatar());
            userInfoBean.setAccount(msgBean.getAccount());
            userInfoBean.setUserType(msgBean.getUserType());
            if (userInfoBeanDao != null) {
                userInfoBeanDao.insertOrReplace(userInfoBean);
            }
        } else {
            LogUtil.d(TAG, "insertData update data");
            unique.setName(msgBean.getNickName());
            unique.setAvatar(msgBean.getAvatar());
            unique.setAccount(msgBean.getAccount());
            unique.setUserType(msgBean.getUserType());
            if (userInfoBeanDao != null) {
                userInfoBeanDao.update(unique);
            }
        }
    }

    public UserInfoBean getUserInfo(String mUid, String peerUid, int userType, long account) {
        try {
            if (userInfoBeanDao == null) {
                return null;
            }
            QueryBuilder<UserInfoBean> qb = userInfoBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid), ChatListBeanDao.Properties.PeerUid.eq(peerUid), ChatListBeanDao.Properties.UserType.eq(userType), ChatListBeanDao.Properties.Account.eq(account)).build().forCurrentThread().unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateData(UserInfoBean userInfoBean) {
        try {
            if (userInfoBeanDao == null) {
                return;
            }
            userInfoBeanDao.update(userInfoBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserInfoBean> getAllUserList(String mUid) {
        try {
            if (userInfoBeanDao == null) {
                LogUtil.e(TAG, "getAllUserList is start userInfoBeanDao is null");
                return null;
            }
            LogUtil.d(TAG, "getAllUserList is start search mUid:" + mUid);
            QueryBuilder<UserInfoBean> qb = userInfoBeanDao.queryBuilder();
            return qb.where(ChatListBeanDao.Properties.MUid.eq(mUid)).build().forCurrentThread().list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
