package com.aioute.trans.model.bean;

public class BaseBean {

	protected String getSpaceString(int value, int length) {
		StringBuffer sb = new StringBuffer();
		String tempString = Integer.toHexString(value);
		int count = length - tempString.length();
		for (int i = 0; i < count; i++) {
			sb.append(" ");
		}
		sb.append(tempString);
		return sb.toString();
	}

	protected String getZeroStringD(int value, int length) {
		StringBuffer sb = new StringBuffer();
		String tempString = value + "";
		int count = length - tempString.length();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		sb.append(tempString);
		return sb.toString();
	}

	protected String getZeroString(int value, int length) {
		StringBuffer sb = new StringBuffer();
		String tempString = Integer.toHexString(value);
		int count = length - tempString.length();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		sb.append(tempString);
		return sb.toString();
	}

	public String encode() {
		return "";
	}
}
