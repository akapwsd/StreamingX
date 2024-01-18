package com.code.utils;

import android.text.TextUtils;

import com.google.protobuf.ByteString;

import java.nio.ByteBuffer;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import uyujoy.com.api.gateway.frontend.Base;

public class DataUtils {
    public static final String TAG = "DataUtils";

    public static ByteBuffer byte2ByteBuffer(byte[] byteArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(byteArray.length);
        byteBuffer.put(byteArray);
        byteBuffer.flip();
        return byteBuffer;
    }

    public static byte[] bytebuffer2ByteArray(ByteBuffer buffer) {
        buffer.flip();
        int len = buffer.limit() - buffer.position();
        byte[] bytes = new byte[len];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer.get();
        }
        return bytes;
    }

    public static String sha256_HMAC(String secret, String data) {
        LogUtil.d(TAG, "sha256_HMAC start secret:" + secret + " data:" + data);
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(data.getBytes());
            hash = byteArrayToHexString(bytes);
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    public static byte[] assembleData(int crcId, byte[] data) {
        Base.messageFrame messageFrame = Base.messageFrame.newBuilder()
                .setCrc32(crcId)
                .setData(ByteString.copyFrom(data))
                .build();
        return messageFrame.toByteArray();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    public static String getFileSuffix(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}
