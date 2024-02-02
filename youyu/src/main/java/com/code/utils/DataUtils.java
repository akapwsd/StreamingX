package com.code.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;

import com.google.protobuf.ByteString;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String fileToHash(String file) {
        FileInputStream fs;
        try {
            fs = new FileInputStream(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while (-1 != (len = fs.read(buffer))) {
                outStream.write(buffer, 0, len);
            }
            outStream.close();
            fs.close();
            return md5byte(outStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5byte(byte[] string) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string);
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Bitmap fileToBitmap(String imageFile) {
        return BitmapFactory.decodeFile(imageFile);
    }

    public static long getMediaTime(String mediaFile) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mediaFile);
            mediaPlayer.prepare();
            return mediaPlayer.getDuration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
