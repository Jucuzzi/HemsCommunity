package com.telek.util;

import java.security.Key;

import javax.crypto.Cipher;

/**
 * 加密解密工具类
 * 
 * @author wang-xinglei(王星磊) 2015-5-26 上午10:56:32
 */
public class AESUtil {

	private static String AES = "AES";

	public static String KEY = "c4c081c26b4974b0";

	/**
	 * Description:对字符数组进行加密 Date:2012-10-11
	 * 
	 * @author jxj
	 * @param str
	 * @param key
	 *            密钥
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] str, byte[] key) {
		// 根据给定的字节数组构造一个密钥。
		Key skey = new javax.crypto.spec.SecretKeySpec(key, AES);

		Cipher encryptCipher;
		try {
			encryptCipher = Cipher.getInstance(AES);
			encryptCipher.init(Cipher.ENCRYPT_MODE, skey);
			return encryptCipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Description:对字节数组进行解密 Date:2012-10-11
	 * 
	 * @author jxj
	 * @param str
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] decrypt(byte[] str, byte[] key) {
		Key skey = new javax.crypto.spec.SecretKeySpec(key, AES);
		Cipher decryptCipher;
		try {
			decryptCipher = Cipher.getInstance(AES);
			decryptCipher.init(Cipher.DECRYPT_MODE, skey);
			// 按单部分操作加密或解密数据，或者结束一个多部分操作,数据将被加密或解密
			return decryptCipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Description:根据需加密byte数组长度获取加密后byte数组长度 Date:2012-10-15
	 * 
	 * @author jxj
	 * @param inputLen
	 *            需加密byte数组长度
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 * @return int 加密后byte数组长度
	 */
	public static int getEncryptLength(int inputLen, byte[] key) {
		Key skey = new javax.crypto.spec.SecretKeySpec(key, AES);
		Cipher encryptCipher;
		try {
			encryptCipher = Cipher.getInstance(AES);
			encryptCipher.init(Cipher.ENCRYPT_MODE, skey);
			return encryptCipher.getOutputSize(inputLen);
		} catch (Exception e) {
			return -1;
		}
	}

	public static String byte2Hex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	public static byte[] hex2Byte(String src) {
		if (src.length() < 1) {
			return null;
		}
		byte[] encrypted = new byte[src.length() / 2];
		for (int i = 0; i < src.length() / 2; i++) {
			int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);

			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}
}
