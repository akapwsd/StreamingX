package com.example.utils;

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

    /**
     * 初始化上下文
     */
    public static void initContent(Context context) {
        mContext = context;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        if(object == null){
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

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @return
     */
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
        }else {
            return sp.getString(key, (String) defaultObject);
        }
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }
    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = mContext.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }
    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     *
     */
    public static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
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
