package com.aioute.trans.model.bean;

public class IPPortBean extends BaseBean {

	/**
     * Ip
	 */
	public String ip;
	/**
	 * 端口
	 */
	public int port;

	@Override
	public String encode() {
		StringBuffer sb = new StringBuffer();
		String[] ips = ip.replace(".", "_").split("_");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3 - ips[i].length(); j++) {
				sb.append("0");
			}
			sb.append(ips[i]);
		}
		String str = port + "";
		for (int j = 0; j < 5 - str.length(); j++) {
			sb.append("0");
		}
		sb.append(str);

		return sb.toString();
	}

}