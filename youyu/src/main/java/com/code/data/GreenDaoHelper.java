package com.code.data;

import android.content.Context;

import com.code.data.greendao.DaoMaster;
import com.code.data.greendao.DaoSession;
import com.code.utils.LogUtil;
import com.code.utils.ThreadPoolUtils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public class GreenDaoHelper {
    private static final String TAG = "GreenDaoHelper";
    private static final String DB_NAME = "streaming_dao.db";
    private SqlOpenHelper mHelper;
    private Database db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private boolean mInitState;

    private static GreenDaoHelper greenDaoUtils;

    private GreenDaoHelper() {
    }

    public static GreenDaoHelper getSingleTon() {
        if (null == greenDaoUtils) {
            synchronized (GreenDaoHelper.class) {
                if (null == greenDaoUtils) {
                    greenDaoUtils = new GreenDaoHelper();
                }
            }
        }
        return greenDaoUtils;
    }

    public void initGreenDao(Context context) {
        ThreadPoolUtils.execute(() -> {
            LogUtil.d(TAG, "initGreenDao is start!");
            mHelper = new SqlOpenHelper(context, DB_NAME, null);
            LogUtil.d(TAG, "===============================NORMAL DATABASE==========================");
            db = mHelper.getWritableDb();
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession(IdentityScopeType.None);
            if (mDaoMaster != null && mDaoSession != null) {
                LogUtil.d(TAG, "initGreenDao daoComplete");
                mInitState = true;
            } else {
                LogUtil.d(TAG, "initGreenDao::listener is null");
                mInitState = false;
            }
            LogUtil.d(TAG, "initGreenDao is end!");
        });
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public Database getDb() {
        return db;
    }

    public boolean getInitState() {
        return mInitState;
    }

    public void deleteSQL() {
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster.dropAllTables(daoMaster.getDatabase(), true);
        DaoMaster.createAllTables(daoMaster.getDatabase(), true);
    }

}
