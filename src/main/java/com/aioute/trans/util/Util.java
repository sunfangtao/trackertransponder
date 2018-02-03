package com.aioute.trans.util;

import com.aioute.trans.model.TracketPacket;

import java.util.List;

public class Util {

	public static String getPacketString(List<Integer> values) {
		int size = values.size();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append((char) (int) (values.get(i)));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 根据给定的字符串生成发送的二进制报文字符串
	 *
	 * @param source
	 * @return
	 */
	public static String getBinString(String source) {
		if (source == null || source.length() == 0)
			return "";
		byte[] sources = source.getBytes();
		int length = sources.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(getBinString((int) sources[i], 8));
		}
		return sb.toString();
	}

	/**
	 * 根据给定的字符串生成发送的十六进制报文字符串
	 *
	 * @param source
	 * @return
	 */
	public static String getHexString(String source) {
		if (source == null || source.length() == 0)
			return "";
		byte[] sources = source.getBytes();
		int length = sources.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(getHexString((int) sources[i], 2));
			sb.append(" ");
		}
		return sb.toString();
	}

	private static String getBinString(int value, int count) {
		String result = Integer.toBinaryString(value);
		int length = 8 - result.length();
		if (length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				sb.append("0");
			}
			result = sb.toString() + result;
		}
		return result.substring(0, 8);
	}

	private static String getHexString(int value, int count) {
		String result = Integer.toHexString(value);
		int length = count - result.length();
		if (length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				sb.append("0");
			}
			result = sb.toString() + result;
		}
		return result.substring(0, count);
	}

	public static String getKeyByPacket(TracketPacket packet) {
		return packet.deviceNumber + "_" + packet.order;
	}


}