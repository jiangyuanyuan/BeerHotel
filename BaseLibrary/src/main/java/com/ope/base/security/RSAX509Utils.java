package com.ope.base.security;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RSAX509Utils {
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static byte[] encrypt(byte[] text, PublicKey pubRSA) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
        int inputLen = text.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(text, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(text, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = hexStrToBytes(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return (PublicKey) keyFactory.generatePublic(spec);
    }

    public static final String bytesToHexStr(byte[] bcd){
        StringBuffer s = new StringBuffer(bcd.length * 2);
        for (int i = 0; i < bcd.length; i++){
            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
            s.append(bcdLookup[bcd[i] & 0x0f]);
        }
        return s.toString();
    }

    private static final byte[] hexStrToBytes(String value) {
        byte[] bytes;
        bytes = new byte[value.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(value.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * RSA加密数据
     */
     public static String getRSAEncode(String publicKey, String data) {
        try {
            PublicKey rsaPublicKey = RSAX509Utils.getPublicKey(publicKey);
            String value = RSAX509Utils.bytesToHexStr(RSAX509Utils.encrypt(
                    data.getBytes("utf-8"), rsaPublicKey));
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}

