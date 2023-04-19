package com.code.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class RtcSpBase {
    private static final String TAG = "RtcSpBase";
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static final String USER_FILE_NAME = "rtc_sp";

    private RtcSpBase() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void initContent(Context context) {
        LogUtil.d(TAG, "initContent is start");
        mContext = context;
    }

    public static void put(String key, Object object) {
        if (object == null) {
            return;
        }
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            return sp.getString(key, (String) defaultObject);
        }
    }

    public static void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean contains(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll() {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        RtcSpBase.mContext = mContext;
    }
}
