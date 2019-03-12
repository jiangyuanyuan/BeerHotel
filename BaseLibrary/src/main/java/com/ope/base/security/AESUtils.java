package com.ope.base.security;


import com.ope.base.helper.DateUtils;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {


	private static String ivParameter = "opeopechain20190";//

	private static String WAYS = "AES";
	private static String MODE = "";
	private static boolean isPwd = true;
	private static String ModeCode = "PKCS5Padding";

	private static int pwdLenght = 16;
	private static String val = "0";

	public static String selectMod(int type) {
		// ECB("ECB", "0"), CBC("CBC", "1"), CFB("CFB", "2"), OFB("OFB", "3");
		switch (type) {
		case 0:
			isPwd = false;
			MODE = WAYS + "/" + AESType.ECB.key() + "/" + ModeCode;

			break;
		case 1:
			isPwd = true;
			MODE = WAYS + "/" + AESType.CBC.key() + "/" + ModeCode;
			break;
		case 2:
			isPwd = true;
			MODE = WAYS + "/" + AESType.CFB.key() + "/" + ModeCode;
			break;
		case 3:
			isPwd = true;
			MODE = WAYS + "/" + AESType.OFB.key() + "/" + ModeCode;
			break;

		}

		return MODE;

	}


	public static String encrypt(String sSrc, String sKey) {
		try {
			sKey = toMakekey(sKey, pwdLenght, val);
			Cipher cipher = Cipher.getInstance(selectMod(1));
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, WAYS);

			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			if (isPwd == false) {// ECB
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			}

			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			System.out.println("encryptedString:" + new String(Base64.encode(encrypted)));
			System.out.println("encryptedURLString:" + URLEncoder.encode(new String(Base64.encode(encrypted)), "UTF-8"));
			return URLEncoder.encode(new String(Base64.encode(encrypted)), "UTF-8");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(sSrc);
			System.out.println("encrypt failed and key is "+sKey);
			return null;
		}

	}

	public static String decrypt(String sSrc, String sKey) {
		try {
			sSrc = URLDecoder.decode(sSrc, "UTF-8");
			sKey = toMakekey(sKey, pwdLenght, val);
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, WAYS);
			Cipher cipher = Cipher.getInstance(selectMod(1));
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			if (isPwd == false) {
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			}
			byte[] encrypted1 = Base64.decode(sSrc.getBytes());
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return "";
		}
	}

	// key
	public static String toMakekey(String str, int strLength, String val) {
		byte[] arrA = new byte[16];
		byte[] arrB = str.getBytes();
		for (int i = 0; i < arrA.length; i++) {
			arrA[i] = arrB[i];
		}

		return new String(arrA);
	}


	public static byte[] newencrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static byte[] newdecrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}


	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) {
//		System.out.println(DateUtils.getUTCTime().getTime());
//		System.out.println(MathUtils.div(6.763300 - 6.7885,6.763300,4));
//		String resultString = decrypt("wI5ewUnO7Ne4PZ1JDYgfCJgG5gK2u66nAq6Y7YqEfbY%3D", "res");
//		System.out.println(resultString);
//		String userType = "USER_TYPE_MOBILE";
//		String account = "18576640992";
//		String password = MD5Utils.md5("123456asdfg");
//		String password = "123456asdfg";
//		System.out.println("password:"+password);
//
//		String time = String.valueOf(DateUtils.getUTCTime().getTime());
//		String time = "1499862940037";
//		System.out.println("time:"+time);
//		System.out.println("type:" + Base64.encode(userType));
//		System.out.println("encryptDataURL:" + AESUtils.encrypt(account, Base64.encode(userType + time)));
//		String encryptAccount = Base64.encode(AESUtils.encrypt(account, Base64.encode(userType + time)));
//		String passwordAccount = Base64.encode(AESUtils.encrypt(password, Base64.encode(userType + time + account)));
//		System.out.println("encryptAccount:"+encryptAccount);
//		System.out.println("passwordAccount:"+passwordAccount);
//		String newAccount = AESUtils.decrypt(Base64.decode(encryptAccount), Base64.encode(userType + time));
//		String newPassword = AESUtils.decrypt(Base64.decode(passwordAccount), Base64.encode(userType + time + account));
//		System.out.println("newAccount:"+newAccount);
//		System.out.println("newPassword:"+newPassword);
//
//		String resultString = decrypt("7a/rH0w5SITLFLAgvQbinA==", "VVNFUl9UWVBFX01PQklMRTE0OTk4NjI5NDAwMzc=");
//		System.out.println("hhshhsh:"+resultString);
//		String userType = "USER_TYPE_MOBILE";
//		String account = "18576640992";
////		String password = MD5Utils.md5("123456asdfg");
//		String password = "EFE8DEEAB66B584B31DCC081BE17956F";
//		System.out.println("password:"+password);
////		{"accountName":"18576640992","password":"EFE8DEEAB66B584B31DCC081BE17956F"}
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("accountName",account);
//		jsonObject.put("password",password);
//
////		String time = String.valueOf(DateUtils.getUTCTime().getTime());
//		String time = "1499862940037";
//		System.out.println("time:"+time);
//		System.out.println("type:" + Base64.encode(userType));
//		System.out.println("data:"+jsonObject.toJSONString());
//		System.out.println("base64:"+ Base64.encode(userType + time));
//		System.out.println("AESdata:"+ AESUtils.encrypt("{\"accountName\":\"18576640992\",\"password\":\"EFE8DEEAB66B584B31DCC081BE17956F\"}","VVNFUl9UWVBFX01P"));
//
//		System.out.println("encryptDataURL:" + AESUtils.encrypt(account, Base64.encode(userType + time)));
//		String encryptAccount = Base64.encode(AESUtils.encrypt(jsonObject.toJSONString(), Base64.encode(userType + time)));
//		String passwordAccount = Base64.encode(AESUtils.encrypt(password, Base64.encode(userType + time + account)));
//		System.out.println("encryptAccount:"+encryptAccount);
//		System.out.println("passwordAccount:"+passwordAccount);
//		sSrc = URLDecoder.decode(sSrc, "UTF-8");
//		String newAccount = AESUtils.decrypt(Base64.decode("S253Y3lXR3JDS1gvQWMvRjVQNDI1Zz09"), Base64.encode("USER_TYPE_MOBILE" + "1499862940037"));
//		String newPassword = AESUtils.decrypt(Base64.decode("N3ZjdFVDbGl4eWJUVjRHeXpWQ2NZWm80TlBZQk8yMHVJYjZWSUxFaUFkVTlUNGM3Rm5iSUt6RTJ5b3dZR0NFcQ=="), Base64.encode("USER_TYPE_MOBILE" + "1499862940037" + "18576640992"));
//		System.out.println("newAccount:"+newAccount);
////		System.out.println("newPassword:"+newPassword);
//		String resultString = decrypt("wI5ewUnO7Ne4PZ1JDYgfCJgG5gK2u66nAq6Y7YqEfbY%3D", "res");
//		System.out.println(resultString);
//		String userType = "USER_TYPE_MOBILE";
//		String account = "18576640992";
//		String password = MD5Utils.md5("123456asdfg");
//		System.out.println("password:"+password);
//
////		String time = String.valueOf(DateUtils.getUTCTime().getTime());
//		String time = "1499862940037";
//		System.out.println("time:"+time);
//		System.out.println("type:" + Base64.encode(userType+ time + account));
//		System.out.println("encryptDataURL:" + AESUtils.encrypt(account, Base64.encode(userType + time)));
//		String encryptAccount = Base64.encode(AESUtils.encrypt(account, Base64.encode(userType + time)));
//		String passwordAccount = Base64.encode(AESUtils.encrypt(password, Base64.encode(userType + time + account)));
//		System.out.println("encryptAccount:"+encryptAccount);
//		System.out.println("passwordAccount:"+passwordAccount);
//		String newAccount = AESUtils.decrypt(Base64.decode(encryptAccount), Base64.encode(userType + time));
//		String newPassword = AESUtils.decrypt(Base64.decode(passwordAccount), Base64.encode(userType + time + account));
//		System.out.println("newAccount:"+newAccount);
//		System.out.println("newPassword:"+newPassword);


	}
}
