package com.sunwuo.electronic_mall.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author acy 屋大维
 */
public class MD5Util {

    private static final String addString = "sunwou9527";

    public static String encoderByMd5(String string) {
        //确定计算方法
        try {
            String builder = string + addString;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(builder.getBytes("utf-8")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}