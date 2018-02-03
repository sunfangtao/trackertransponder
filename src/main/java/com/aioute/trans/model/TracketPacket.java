package com.aioute.trans.model;

import com.aioute.trans.util.Util;

public class TracketPacket {

	/**
	 * 包头
	 */
	public String header;
	/**
	 * 设备号
	 */
	public String deviceNumber;
	/**
	 * 命令字
	 */
	public String order;
	/**
	 * 消息体
	 */
	public String body = "";
	/**
	 * 包尾
	 */
	public String end;
	/**
	 * 是否需要应答
	 */
	public boolean isConfirm = false;

	public String parse(String source) {
		int length = source.length();
		if (length < 18)
			return "包长度错误";

		String tempString = source.substring(0, 1);
		if ("(".equals(tempString)) {
			header = "(";
		} else {
			return "消息头错误:" + tempString;
		}

		tempString = source.substring(length - 1);
		if (")".equals(tempString)) {
			end = ")";
		} else {
			return "消息尾错误:" + ")";
		}

		deviceNumber = source.substring(1, 13);

		order = source.substring(13, 17);

		body = source.substring(17, source.length() - 1);

		return null;
	}

	public String encode() {
		return Util.getHexString(header + deviceNumber + order + body + end);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("   设备号:").append(deviceNumber);
		sb.append("   命令字:").append(order);
		sb.append("   消息体:").append(body);
		return sb.toString();
	}
}
