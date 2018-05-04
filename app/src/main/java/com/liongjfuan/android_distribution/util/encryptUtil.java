package com.liongjfuan.android_distribution.util;

import android.os.Message;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.11
 */

public class encryptUtil {

    private static final String TAG = "encryptUtil";

    /**
     * 加密成Base64
     * @param input
     * @return
     */
    public static String encryptBase64(String input) {
        Log.i(TAG, "encryptBase64: String");
        String enToStr = Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
        Log.i(TAG, "encryptBase64: Base64加密后的密文为：" + enToStr);
        return enToStr;
    }

    public static String encryptBase64(byte[] input) {
        Log.i(TAG, "encryptBase64: byte[]");
        String enToStr = Base64.encodeToString(input, Base64.DEFAULT);
        return enToStr;
    }

    /**
     * 从Base64解码
     * @param input
     * @return
     */
    public static String decipherBase64ToString(String input) {
        Log.i(TAG, "decipherBase64: String: ");
        String decodedString = new String(Base64.decode(input, Base64.DEFAULT));
        return decodedString;
    }

    public static byte[] decipherBase64ToByte(String input) {
        Log.i(TAG, "decipherBase64: byte: ");
        byte[] decodedByteArray = Base64.decode(input, Base64.DEFAULT);
        return decodedByteArray;
    }

    /**
     * 加密成MD5
     * @param password
     * @return
     */
    public static String encryptMD5(String password) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            Log.i(TAG, "encryptMD5: MD5加密后的密文为：" + result);
            return result;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将url编码成MD5
     * @param url
     * @return
     */
    public static String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}




















