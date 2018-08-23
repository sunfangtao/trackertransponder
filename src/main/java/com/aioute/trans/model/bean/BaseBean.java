package com.aioute.trans.model.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaseBean {

	public static void main(String[] args) throws ParseException {
		String s = "16-01-22";
		SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd");
		Date date = format.parse(s);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.YEAR));
	}
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
