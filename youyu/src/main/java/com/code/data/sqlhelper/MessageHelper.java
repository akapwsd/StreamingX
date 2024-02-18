package com.code.data.sqlhelper;

import android.text.TextUtils;

import com.code.data.GreenDaoHelper;
import com.code.data.greendao.DaoSession;
import com.code.data.greendao.MsgBeanDao;
import com.code.data.sqlbean.MsgBean;
import com.code.utils.LogUtil;
import com.code.utils.RtcSpUtils;
import com.code.youyu.api.Constants;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import uyujoy.api.paasim.frontend.MediaBase;
import uyujoy.api.paasim.frontend.MsgBase;

public class MessageHelper implements GreenDaoHelper.GreenDaoInitResultListener {
    private static final String TAG = "MessageHelper";
    private Gson gson;
    private static MessageHelper messageHelper;
    private MsgBeanDao msgBeanDao;
    private GreenDaoDataListener greendaoDataListener;
    private String QueryId = "";

    public MessageHelper() {
        GreenDaoHelper.getSingleTon().setGreenDaoInitResultListener(TAG, this);
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

    public void setGreenDaoDataListener(String QueryId, GreenDaoDataListener greendaoDataListener) {
        this.greendaoDataListener = greendaoDataListener;
        this.QueryId = QueryId;
    }

    public void removeGreenDaoDataListener() {
        greendaoDataListener = null;
        QueryId = "";
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

    @Override
    public void result(boolean mInitState) {

    }

    public interface GreenDaoDataListener {
        void DataChange(MsgBean tempChatMessageBean);

        void StateChange(MsgBean tempChatMessageBean);

        void DataProgress(MsgBean tempChatMessageBean);
    }

    public List<MsgBean> getData(String uid, String peerUid, int userType, long account, Long time) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.Uid.eq(uid),
                    MsgBeanDao.Properties.PeerUid.eq(peerUid),
                    MsgBeanDao.Properties.UserType.eq(userType),
                    MsgBeanDao.Properties.Account.eq(account),
                    MsgBeanDao.Properties.ActualTime.ge(time)).orderDesc(MsgBeanDao.Properties.ActualTime).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MsgBean> getData(String uid, String peerUid, int userType, long account, long time, int PageNumber, int IndexNumber) {
        try {
            LogUtil.d(TAG, "getData::uid is: " + uid + " and time is: " + time + " and PageNumber is: " + PageNumber + "IndexNumber is: " + IndexNumber);
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            List<MsgBean> list = qb.where(MsgBeanDao.Properties.Uid.eq(uid),
                    MsgBeanDao.Properties.ActualTime.lt(time),
                    MsgBeanDao.Properties.UserType.eq(userType),
                    MsgBeanDao.Properties.Account.eq(account),
                    MsgBeanDao.Properties.PeerUid.eq(peerUid)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(PageNumber * IndexNumber).limit(IndexNumber).list();
            LogUtil.d(TAG, "getData list:" + list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void insertOrReplaceDataData(MsgBase.paasMsgRecord paasMsgRecord) {
        LogUtil.d(TAG, "insertData start insert data! paasMsgRecord:" + paasMsgRecord);
        try {
            synchronized (msgBeanDao) {
                MsgBean msgBean = new MsgBean();
                msgBean.setAvatar(paasMsgRecord.getUser().getAvatar());
                msgBean.setNickName(paasMsgRecord.getUser().getName());
                msgBean.setActualTime(paasMsgRecord.getSendTime());
                msgBean.setFp(paasMsgRecord.getMsgFp());
                msgBean.setUid(RtcSpUtils.getInstance().getUserUid());
                msgBean.setIsBroadcaster(paasMsgRecord.getTo().getType() == MsgBase.UserType.BROADCASTER);
                if (RtcSpUtils.getInstance().getUserUid().equals(paasMsgRecord.getFrom().getId())) {
                    msgBean.setSourceType(Constants.MSG_SENDER);
                    msgBean.setPeerUid(paasMsgRecord.getTo().getId());
                    msgBean.setAccount(paasMsgRecord.getTo().getAccount());
                    if (paasMsgRecord.getTo().getType() == MsgBase.UserType.BROADCASTER) {
                        msgBean.setUserType(Constants.TYPE_BROADCASTER);
                    } else {
                        msgBean.setUserType(Constants.TYPE_USER);
                    }
                } else if (RtcSpUtils.getInstance().getUserUid().equals(paasMsgRecord.getTo().getId())) {
                    msgBean.setSourceType(Constants.MSG_RECEIVER);
                    msgBean.setPeerUid(paasMsgRecord.getFrom().getId());
                    msgBean.setAccount(paasMsgRecord.getFrom().getAccount());
                    if (paasMsgRecord.getFrom().getType() == MsgBase.UserType.BROADCASTER) {
                        msgBean.setUserType(Constants.TYPE_BROADCASTER);
                    } else {
                        msgBean.setUserType(Constants.TYPE_USER);
                    }
                } else {
                    msgBean.setSourceType(Constants.MSG_UNKNOWN);
                    LogUtil.e(TAG, "insertOrReplaceDataData fail data is error");
                    return;
                }
                msgBean.setState(Constants.SENDING);
                int msgType = paasMsgRecord.getMsgType();
                LogUtil.d(TAG, "insertOrReplaceDataData msgType:" + msgType);
                msgBean.setStatus(msgType);
                if (msgType == Constants.MSG_SEND_IMAGE) {
                    MediaBase.mediaImage mediaImage = MediaBase.mediaImage.parseFrom(paasMsgRecord.getMedia().getMediaContent());
                    msgBean.setLocalPath(mediaImage.getPreview().getUrl());
                    msgBean.setHash(mediaImage.getPreview().getHash());
                    msgBean.setWight(mediaImage.getPreview().getWight());
                    msgBean.setHeight(mediaImage.getPreview().getHeight());
                    msgBean.setSize(mediaImage.getPreview().getSize());
                    msgBean.setExt(mediaImage.getExt());
                } else if (msgType == Constants.MSG_SEND_VOICE) {
                    MediaBase.mediaAudio mediaAudio = MediaBase.mediaAudio.parseFrom(paasMsgRecord.getMedia().getMediaContent());
                    msgBean.setLocalPath(mediaAudio.getUrl());
                    msgBean.setHash(mediaAudio.getHash());
                    msgBean.setTime(mediaAudio.getTimeLen());
                    msgBean.setSize(mediaAudio.getSize());
                    msgBean.setExt(mediaAudio.getExtName());
                } else if (msgType == Constants.MSG_SEND_TEXT) {
                    msgBean.setContent(paasMsgRecord.getMsgTxt());
                } else {
                    LogUtil.e(TAG, "insertOrReplaceDataData fail data type is error");
                    return;
                }
                long l = msgBeanDao.insertOrReplace(msgBean);
                LogUtil.d(TAG, "insertData::msg insert to database index is " + l);
                if (greendaoDataListener != null && QueryId.equals(msgBean.getPeerUid())) {
                    greendaoDataListener.DataChange(msgBean);
                }
                ChatListHelper.getSingleton().insertOrReplaceData(msgBean);
                UserInfoHelper.getSingleton().insertOrReplaceData(msgBean);
            }
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void insertOrReplaceData(MsgBean msgBean) {
        LogUtil.d(TAG, "insertData start insert data! MsgBean:" + msgBean);
        synchronized (msgBeanDao) {
            long l = msgBeanDao.insertOrReplace(msgBean);
            LogUtil.d(TAG, "insertData::msg insert to database index is " + l);
            if (greendaoDataListener != null && QueryId.equals(msgBean.getPeerUid())) {
                greendaoDataListener.DataChange(msgBean);
            }
            ChatListHelper.getSingleton().insertOrReplaceData(msgBean);
            UserInfoHelper.getSingleton().insertOrReplaceData(msgBean);
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
                String fid = unique.getPeerUid();
                if (TextUtils.isEmpty(fid)) {
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
    public void modifyMessageState(final String fp, final int state) {
        LogUtil.d(TAG, "modifyMessageState  fp:" + fp + "   state:" + state);
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
        if (unique != null) {
            int status = unique.getStatus();
            LogUtil.d(TAG, "status:" + status);
            String content = unique.getContent();
            if (status == Constants.MSG_SEND_IMAGE) {
                LogUtil.d(TAG, "picture result:" + unique.getLocalPath() + "\ncontent:" + content);
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
                String fid = unique.getPeerUid();
                if (greendaoDataListener != null && QueryId.equals(fid)) {
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
        LogUtil.d(TAG, "modifyMessageProgress fp :" + fp + "  progress" + progress);
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.Fp.eq(fp)).build().forCurrentThread().unique();
        if (unique != null) {
            String fid = unique.getPeerUid();
            if (progress < 1 && progress >= 0) {
                unique.setState(Constants.SENDING);
            } else {
                unique.setState(Constants.SEND_FAIL);
            }
            unique.setProgress(progress);
            msgBeanDao.update(unique);
            if (greendaoDataListener != null && QueryId.equals(fid)) {
                greendaoDataListener.DataProgress(unique);
            }
        }
    }

    /**
     * Get the last message
     */
    public MsgBean getLastMessage(String uid, String peerUid, int userType, long account) {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean unique = qb.where(MsgBeanDao.Properties.Uid.eq(uid),
                MsgBeanDao.Properties.PeerUid.eq(peerUid),
                MsgBeanDao.Properties.UserType.eq(userType),
                MsgBeanDao.Properties.Account.eq(account)).orderDesc(MsgBeanDao.Properties.ActualTime).limit(1).build().forCurrentThread().unique();
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

    public MsgBean getOneMessage(long pts) {
        if (pts < 0) {
            return null;
        }
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        return qb.where(MsgBeanDao.Properties.Pts.eq(pts)).build().forCurrentThread().unique();
    }

    /**
     * Get a range of data
     */
    public List<MsgBean> getMessage(String uid, int start, int count) {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        return qb.where(MsgBeanDao.Properties.Uid.eq(uid), MsgBeanDao.Properties.Status.eq(0)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(start).limit(count).build().forCurrentThread().list();
    }

    public long getLastPtsDate() {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean msgBean = qb.where(MsgBeanDao.Properties.Uid.eq(RtcSpUtils.getInstance().getUserUid()))
                .orderDesc(MsgBeanDao.Properties.ActualTime)
                .limit(1).build().forCurrentThread().unique();
        if (msgBean != null) {
            return msgBean.getActualTime();
        } else {
            return 0L;
        }
    }

    public long getLastPts() {
        QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
        MsgBean msgBean = qb.where(MsgBeanDao.Properties.Uid.eq(RtcSpUtils.getInstance().getUserUid())).orderDesc(MsgBeanDao.Properties.ActualTime).limit(1).build().forCurrentThread().unique();
        if (msgBean != null) {
            return msgBean.getPts();
        } else {
            return 0L;
        }
    }

    /**
     * Specify person to obtain data
     */
    public List<MsgBean> getData(String uid, String peerUid, int PageNumber, int IndexNumber) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.Uid.eq(uid), MsgBeanDao.Properties.PeerUid.eq(peerUid)).orderDesc(MsgBeanDao.Properties.ActualTime).offset(PageNumber * IndexNumber).limit(IndexNumber).build().forCurrentThread().list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Specify person to obtain all data
     */
    public List<MsgBean> getAllData(String uid, String peerUid, int userType, long account) {
        try {
            QueryBuilder<MsgBean> qb = msgBeanDao.queryBuilder();
            return qb.where(MsgBeanDao.Properties.Uid.eq(uid),
                    MsgBeanDao.Properties.PeerUid.eq(peerUid), MsgBeanDao.Properties.UserType.eq(userType), MsgBeanDao.Properties.Account.eq(account)).orderDesc(MsgBeanDao.Properties.ActualTime).build().forCurrentThread().list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void upData(MsgBean oneMessage) {
        GreenDaoHelper.getSingleTon().getmDaoSession().getMsgBeanDao().update(oneMessage);
    }
}
