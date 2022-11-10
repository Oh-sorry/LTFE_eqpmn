package com.mtpms.AES128;

import java.security.Key;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AES128 {
    private static byte[] ips;
    private static SecretKeySpec keySpec;
    private static String key = "mcst5800mcst5800";
    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;

    static {
          try {
              ips = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
              keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

              encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
              decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

              encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec,new IvParameterSpec(ips));
              decryptCipher.init(Cipher.DECRYPT_MODE, keySpec,new IvParameterSpec(ips));
          } catch (Exception e) {
        	  System.out.println(e.getMessage());
          }
    }

    public static String encrypt(String str) throws Exception {
        try {
            byte[] encrypted = encryptCipher.doFinal(str.getBytes("UTF-8"));
            String Str = byteArrayToHex(encrypted);

            return Str;
        } catch (Exception e) {
             throw new Exception(e.getMessage());
        }
    }

    public static String decrypt(String str) throws Exception {
        try {

            if (str == null || "".equals(str)) return "";

            byte[] byteStr = hexToByteArray(str);
            String Str = new String(decryptCipher.doFinal(byteStr), "UTF-8");

            return Str;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

	public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }
        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

  public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString().toUpperCase();
    }

}
