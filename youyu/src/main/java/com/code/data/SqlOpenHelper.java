package com.code.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.code.data.greendao.DaoMaster;
import com.code.data.greendao.MsgBeanDao;
import com.code.utils.LogUtil;

import org.greenrobot.greendao.database.Database;

public class SqlOpenHelper extends DaoMaster.OpenHelper {

    private static final String TAG = "SqlOpenHelper";

    public SqlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        if (LogUtil.getLogLevel() == LogUtil.Level.Level_HIGH.ordinal()) {
            MigrationHelper.DEBUG = true;
        } else {
            MigrationHelper.DEBUG = false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.d(TAG, "onUpgrade newV:" + newVersion + " oldV:" + oldVersion);
        if (newVersion > oldVersion) {
            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                        @Override
                        public void onCreateAllTables(Database db, boolean ifNotExists) {
                            LogUtil.d(TAG, "================onCreateAllTables================");
                            DaoMaster.createAllTables(db, ifNotExists);
                        }

                        @Override
                        public void onDropAllTables(Database db, boolean ifExists) {
                            LogUtil.d(TAG, "================onDropAllTables================");
                            DaoMaster.dropAllTables(db, ifExists);
                        }
                    }
                    , MsgBeanDao.class
            );
        }
    }
}
