package com.code.utils;

public class LogUtil {
    private static int logLevel = Level.Level_LOW.ordinal();

    public static void setLogLevel(int level) {
        logLevel = level;
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void i(String tag, String msg) {

        if (logLevel >= 0) {

            android.util.Log.i(tag, msg);

        }

    }

    public static void v(String tag, String msg) {

        if (logLevel >= 0) {

            android.util.Log.v(tag, msg);

        }
    }

    public static void w(String tag, String msg) {

        if (logLevel >= 1) {

            android.util.Log.w(tag, msg);

        }
    }

    public static void e(String tag, String msg) {

        if (logLevel >= 1) {

            android.util.Log.e(tag, msg);

        }
    }

    public static void d(String tag, String msg) {

        if (logLevel == 2) {

            android.util.Log.d(tag, msg);

        }
    }

    public static void d(String tag, String msg, Throwable throwable) {

        if (logLevel == 2) {

            android.util.Log.d(tag, msg, throwable);

        }
    }

    public enum Level {

        /**
         * 只打印i,v级别的日志
         */
        Level_LOW,

        /**
         * 多打印w和e日志
         */
        Level_MIDDLE,

        /**
         * 全部打印
         */
        Level_HIGH;

    }
}
