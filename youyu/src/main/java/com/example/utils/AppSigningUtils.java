package com.example.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

public class AppSigningUtils {
    public final static String TAG = "AppSigningUtils";
    public final static String MD5 = "MD5";
    public final static String SHA1 = "SHA1";
    public final static String SHA256 = "SHA256";
    private static final HashMap<String, ArrayList<String>> mSignMap = new HashMap<>();

    public static ArrayList<String> getSignInfo(Context context, String type) {
        if (context == null || type == null) {
            return null;
        }
        String packageName = context.getPackageName();
        if (packageName == null) {
            return null;
        }
        if (mSignMap.get(type) != null) {
            return mSignMap.get(type);
        }
        ArrayList<String> mList = new ArrayList<>();
        try {
            Signature[] signs = getSignatures(context, packageName);
            assert signs != null;
            for (Signature sig : signs) {
                String tmp = "error!";
                switch (type) {
                    case MD5:
                        tmp = getSignatureByteString(sig, MD5);
                        break;
                    case SHA1:
                        tmp = getSignatureByteString(sig, SHA1);
                        break;
                    case SHA256:
                        tmp = getSignatureByteString(sig, SHA256);
                        break;
                }
                mList.add(tmp);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.toString());
        }
        mSignMap.put(type, mList);
        return mList;
    }

    /**
     * get sign Sha1
     */
    public static String getSha1(Context context) {
        String res = "";
        ArrayList<String> mList = getSignInfo(context, SHA1);
        if (mList != null && mList.size() != 0) {
            res = mList.get(0);
        }
        return res;
    }

    /**
     * get sign MD5
     */
    public static String getMD5(Context context) {
        String res = "";
        ArrayList<String> mList = getSignInfo(context, MD5);
        if (mList != null && mList.size() != 0) {
            res = mList.get(0);
        }
        return res;
    }

    /**
     * get sign SHA256
     */
    public static String getSHA256(Context context) {
        String res = "";
        ArrayList<String> mList = getSignInfo(context, SHA256);
        if (mList != null && mList.size() != 0) {
            res = mList.get(0);
        }
        return res;
    }

    /**
     * get sign info
     */
    private static Signature[] getSignatures(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (Exception e) {
            LogUtil.e(TAG, e.toString());
        }
        return null;
    }

    private static String getSignatureString(Signature sig, String type) {
        byte[] hexBytes = sig.toByteArray();
        String fingerprint = "error!";
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            byte[] digestBytes = digest.digest(hexBytes);
            StringBuilder sb = new StringBuilder();
            for (byte digestByte : digestBytes) {
                sb.append((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3));
            }
            fingerprint = sb.toString();
        } catch (Exception e) {
            LogUtil.e(TAG, e.toString());
        }

        return fingerprint;
    }

    private static String getSignatureByteString(Signature sig, String type) {
        byte[] hexBytes = sig.toByteArray();
        String fingerprint = "error!";
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            byte[] digestBytes = digest.digest(hexBytes);
            StringBuilder sb = new StringBuilder();
            for (byte digestByte : digestBytes) {
                sb.append(((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3)).toUpperCase());
                sb.append(":");
            }
            fingerprint = sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            LogUtil.e(TAG, e.toString());
        }

        return fingerprint;
    }
}
