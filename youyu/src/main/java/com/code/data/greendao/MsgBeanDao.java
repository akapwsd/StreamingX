package com.code.data.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.code.data.sqlbean.MsgBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MSG_BEAN".
*/
public class MsgBeanDao extends AbstractDao<MsgBean, Long> {

    public static final String TABLENAME = "MSG_BEAN";

    /**
     * Properties of entity MsgBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Fp = new Property(1, String.class, "fp", false, "FP");
        public final static Property IsBroadcaster = new Property(2, boolean.class, "isBroadcaster", false, "IS_BROADCASTER");
        public final static Property MsgId = new Property(3, long.class, "msgId", false, "MSG_ID");
        public final static Property Pts = new Property(4, long.class, "pts", false, "PTS");
        public final static Property MUid = new Property(5, String.class, "mUid", false, "M_UID");
        public final static Property PeerUid = new Property(6, String.class, "peerUid", false, "PEER_UID");
        public final static Property SourceType = new Property(7, int.class, "sourceType", false, "SOURCE_TYPE");
        public final static Property Content = new Property(8, String.class, "content", false, "CONTENT");
        public final static Property LocalPath = new Property(9, String.class, "localPath", false, "LOCAL_PATH");
        public final static Property Status = new Property(10, int.class, "status", false, "STATUS");
        public final static Property State = new Property(11, int.class, "state", false, "STATE");
        public final static Property Progress = new Property(12, double.class, "progress", false, "PROGRESS");
        public final static Property ActualTime = new Property(13, long.class, "actualTime", false, "ACTUAL_TIME");
        public final static Property NickName = new Property(14, String.class, "nickName", false, "NICK_NAME");
        public final static Property Avatar = new Property(15, String.class, "avatar", false, "AVATAR");
    }


    public MsgBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MsgBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MSG_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FP\" TEXT," + // 1: fp
                "\"IS_BROADCASTER\" INTEGER NOT NULL ," + // 2: isBroadcaster
                "\"MSG_ID\" INTEGER NOT NULL ," + // 3: msgId
                "\"PTS\" INTEGER NOT NULL ," + // 4: pts
                "\"M_UID\" TEXT NOT NULL ," + // 5: mUid
                "\"PEER_UID\" TEXT NOT NULL ," + // 6: peerUid
                "\"SOURCE_TYPE\" INTEGER NOT NULL ," + // 7: sourceType
                "\"CONTENT\" TEXT," + // 8: content
                "\"LOCAL_PATH\" TEXT," + // 9: localPath
                "\"STATUS\" INTEGER NOT NULL ," + // 10: status
                "\"STATE\" INTEGER NOT NULL ," + // 11: state
                "\"PROGRESS\" REAL NOT NULL ," + // 12: progress
                "\"ACTUAL_TIME\" INTEGER NOT NULL ," + // 13: actualTime
                "\"NICK_NAME\" TEXT," + // 14: nickName
                "\"AVATAR\" TEXT);"); // 15: avatar
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MSG_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MsgBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fp = entity.getFp();
        if (fp != null) {
            stmt.bindString(2, fp);
        }
        stmt.bindLong(3, entity.getIsBroadcaster() ? 1L: 0L);
        stmt.bindLong(4, entity.getMsgId());
        stmt.bindLong(5, entity.getPts());
        stmt.bindString(6, entity.getMUid());
        stmt.bindString(7, entity.getPeerUid());
        stmt.bindLong(8, entity.getSourceType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(10, localPath);
        }
        stmt.bindLong(11, entity.getStatus());
        stmt.bindLong(12, entity.getState());
        stmt.bindDouble(13, entity.getProgress());
        stmt.bindLong(14, entity.getActualTime());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(15, nickName);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(16, avatar);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MsgBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fp = entity.getFp();
        if (fp != null) {
            stmt.bindString(2, fp);
        }
        stmt.bindLong(3, entity.getIsBroadcaster() ? 1L: 0L);
        stmt.bindLong(4, entity.getMsgId());
        stmt.bindLong(5, entity.getPts());
        stmt.bindString(6, entity.getMUid());
        stmt.bindString(7, entity.getPeerUid());
        stmt.bindLong(8, entity.getSourceType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(10, localPath);
        }
        stmt.bindLong(11, entity.getStatus());
        stmt.bindLong(12, entity.getState());
        stmt.bindDouble(13, entity.getProgress());
        stmt.bindLong(14, entity.getActualTime());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(15, nickName);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(16, avatar);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MsgBean readEntity(Cursor cursor, int offset) {
        MsgBean entity = new MsgBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // fp
            cursor.getShort(offset + 2) != 0, // isBroadcaster
            cursor.getLong(offset + 3), // msgId
            cursor.getLong(offset + 4), // pts
            cursor.getString(offset + 5), // mUid
            cursor.getString(offset + 6), // peerUid
            cursor.getInt(offset + 7), // sourceType
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // content
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // localPath
            cursor.getInt(offset + 10), // status
            cursor.getInt(offset + 11), // state
            cursor.getDouble(offset + 12), // progress
            cursor.getLong(offset + 13), // actualTime
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // nickName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // avatar
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MsgBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFp(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsBroadcaster(cursor.getShort(offset + 2) != 0);
        entity.setMsgId(cursor.getLong(offset + 3));
        entity.setPts(cursor.getLong(offset + 4));
        entity.setMUid(cursor.getString(offset + 5));
        entity.setPeerUid(cursor.getString(offset + 6));
        entity.setSourceType(cursor.getInt(offset + 7));
        entity.setContent(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLocalPath(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setStatus(cursor.getInt(offset + 10));
        entity.setState(cursor.getInt(offset + 11));
        entity.setProgress(cursor.getDouble(offset + 12));
        entity.setActualTime(cursor.getLong(offset + 13));
        entity.setNickName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAvatar(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MsgBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MsgBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MsgBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}