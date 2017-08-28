package com.telek.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description:生成摘要码工具类 HMACUtil.java Create on 2012-10-11 下午2:56:03
 * 
 * @author jxj
 * @version 1.0 Copyright (c) 2012 telek. All Rights Reserved.
 */
public class HMACUtil {
	private final static Logger log = LoggerFactory.getLogger(HMACUtil.class);

	private HMACUtil() {
	}

	/**
	 * 
	 * Description:生成HMAC摘要码 Date:2012-10-11
	 * 
	 * @author jxj
	 * @param data
	 * @param key
	 *            密钥
	 * @throws Exception
	 * @return String
	 */
	public static String encryptHMAC(byte[] data, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] returnByte = mac.doFinal(data);
			return byte2Hex(returnByte);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	private static String byte2Hex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}
}