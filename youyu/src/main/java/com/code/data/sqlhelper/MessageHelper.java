package com.code.data.sqlhelper;

import android.text.TextUtils;

import com.code.data.GreenDaoHelper;
import com.code.data.greendao.DaoSession;
import com.code.data.greendao.MsgBeanDao;
import com.code.data.sqlbean.MsgBean;
import com.code.utils.LogUtil;
import com.code.youyu.api.Constants;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.List;

public class MessageHelper {
    private static final String TAG = "MessageHelper";
    private Gson gson;
    private static MessageHelper messageHelper;
    private MsgBeanDao msgBeanDao;
    private GreenDaoDataListener greendaoDataListener;
    private int QueryId = -1;

    public MessageHelper() {
        gson = new Gson();
        DaoSession daoSession = GreenDaoHelper.getSingleTon().getmDaoSession();
        if (daoSession != null) {
            msgBeanDao = daoSession.getMsgBeanDao();
        }
    }

    public synchronized static MessageHelper getSingleton() {
        synchronized (MessageHelper.class) {
            if (messageHelper == null) {
                messageHelper = new MessageHelper();
            }
        }
        return messageHelper;
    }

    public void setGreenDaoDataListener(int QueryId, GreenDaoDataListener greendaoDataListener) {
        this.greendaoDataListener = greendaoDataListener;
        this.QueryId = QueryId;
    }

    public void removeGreenDaoDataListener() {
        greendaoDataListener = null;
        QueryId = -1;
    }

    public synchronized void insertList(List<MsgBean> lists) {
        try {
            synchronized (msgBeanDao) {
                msgBeanDao.insertOrReplaceInTx(lists);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface GreenDaoDataListener {
        void DataChange(MsgBean tempChatMessageBean);

        void StateChange(MsgBean tempChatMessageBean);

        void DataProgress(MsgBean tempChatMessageBean);
    }

    public List<MsgBean> getData(int uid, int peerUid, Long time) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.MUid.eq(uid), MsgBeanDao.Properties.PeerUid.eq(peerUid), MsgBeanDao.Properties.ActualTime.ge(time)).orderDesc(MsgBeanDao.Properties.ActualTime).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MsgBean> getData(int uid, int peerUid, long time, int PageNumber, int IndexNumber) {
        try {
            LogUtil.d(TAG, "getData::uid is: " + uid + " and time is: " + time + " and PageNumber is: " + PageNumber + "IndexNumber is: " + IndexNumber);
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            List<MsgBean> list = qb.where(MsgBeanDao.Properties.MUid.eq(uid)
                    , MsgBeanDao.Properties.ActualTime.lt(time)
                    , MsgBeanDao.Properties.PeerUid.eq(uid)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(PageNumber * IndexNumber).limit(IndexNumber).list();
            LogUtil.d(TAG, "getData list:" + list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void insertData(MsgBean msgBean) {
        LogUtil.d(TAG, "insertData start insert data! MsgBean:" + msgBean);
        synchronized (msgBeanDao) {
            long l = msgBeanDao.insertOrReplace(msgBean);
            LogUtil.d(TAG, "insertData::msg insert to database index is " + l);
            if (greendaoDataListener != null && QueryId == msgBean.getPeerUid()) {
                greendaoDataListener.DataChange(msgBean);
            }
            //TODO 添加聊天列表
        }
    }

    /**
     * delete msg by fp
     */
    public boolean deleteOneMessage(String fp) {
        if (!TextUtils.isEmpty(fp)) {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            MsgBean unique = qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
            if (unique != null) {
                int fid = unique.getPeerUid();
                if (fid == 0) {
                    unique.setContent(null);
                    msgBeanDao.update(unique);
                } else {
                    msgBeanDao.deleteInTx(unique);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * change msg state
     * state //数据状态 1成功 2失败 3发送中 4暂停
     */
    public void modifyMessageState(int peerUid, final String fp, final int state) {
        LogUtil.d(TAG, "modifyMessageState  fp:" + fp + "   state:" + state);
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
        if (unique != null) {
            int mUid = unique.getMUid();
            if (mUid != peerUid) {
                int status = unique.getStatus();
                LogUtil.d(TAG, "status:" + status);
                String content = unique.getContent();
                if (status == Constants.MSG_SEND_IMAGE) {
                    LogUtil.d(TAG, "picture result:" + unique.getLocalPath() + "\ncontent:" + content);
                }
            }
            if (unique.getState() == Constants.SEND_SUCCESS) {
                LogUtil.d(TAG, "=============modifyMessageState=========state is suc and read not need modify");
            } else {
                LogUtil.d(TAG, "modifyMessageState::do modify");
                unique.setState(state);
                if (state == Constants.SEND_SUCCESS) {
                    unique.setProgress(1);
                }
                msgBeanDao.update(unique);
                int fid = unique.getPeerUid();
                if (greendaoDataListener != null && QueryId == fid) {
                    greendaoDataListener.StateChange(unique);
                }
            }
        } else {
            LogUtil.d(TAG, "modifyMessageState::can not find the msg from database");
        }
    }

    /**
     * Modify uploaded file details
     * state //数据状态 1成功 2失败 3发送中 4暂停
     */
    public void modifyMessageProgress(final String fp, final double progress) {
        LogUtil.d("modifyMessageProgress", "fp :" + fp + "  progress" + progress);
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
        if (unique != null) {
            int fid = unique.getPeerUid();
            if (progress < 1 && progress >= 0) {
                unique.setState(Constants.SENDING);
            } else {
                unique.setState(Constants.SEND_FAIL);
            }
            unique.setProgress(progress);
            msgBeanDao.update(unique);
            if (greendaoDataListener != null && QueryId == fid) {
                greendaoDataListener.DataProgress(unique);
            }
        }
    }

    /**
     * Get the last message
     */
    public MsgBean getLastMessage(int uid, int peerUid) {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.MUid.eq(uid), MsgBeanDao.Properties.PeerUid.eq(peerUid)).orderDesc(MsgBeanDao.Properties.ActualTime).limit(1).build().forCurrentThread().unique();
        String content;
        if (unique != null) {
            content = unique.getContent();
            if (!TextUtils.isEmpty(content)) {
                LogUtil.d(TAG, "get last msg before decry is: " + content);
                if (!TextUtils.isEmpty(content)) {
                    unique.setContent(content);
                }
            }
        }
        return unique;
    }

    /**
     * get msg by fp
     */
    public MsgBean getOneMessage(String fp) {
        if (TextUtils.isEmpty(fp)) {
            return null;
        }
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        return qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
    }

    /**
     * Get a range of data
     */
    public List<MsgBean> getMessage(int uid,int start, int count) {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        return qb.where(MsgBeanDao.Properties.MUid.eq(uid), MsgBeanDao.Properties.Status.eq(0)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(start).limit(count).build().forCurrentThread().list();
    }

    /**
     * Specify person to obtain data
     */
    public List<MsgBean> getData(int uid,int peerUid, int PageNumber, int IndexNumber) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.MUid.eq(uid), MsgBeanDao.Properties.PeerUid.eq(peerUid)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(PageNumber * IndexNumber).limit(IndexNumber).build().forCurrentThread().list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Specify person to obtain all data
     */
    public List<MsgBean> getAllData(int uid,int peerUid) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.MUid.eq(uid), MsgBeanDao.Properties.PeerUid.eq(peerUid)).orderDesc(MsgBeanDao.Properties.ActualTime).build().forCurrentThread().list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void upData(MsgBean oneMessage) {
        GreenDaoHelper.getSingleTon().getmDaoSession().getMsgBeanDao().update(oneMessage);
    }
}
