package com.aioute.trans.sendpacket;

import com.aioute.trans.model.bean.BaseBean;
import com.aioute.trans.model.TracketPacket;

public abstract class BaseSendParam {

	protected TracketPacket sendPacket;

	public BaseSendParam(BaseBean bean) {
		sendPacket = new TracketPacket();
		sendPacket.header = "(";
		if (bean == null) {
			sendPacket.body = "";
		} else {
			sendPacket.body = bean.encode();
		}
		sendPacket.end = ")";
	}

	/**
	 * 
	 * @param deviceNumber
	 *            设备号
	 * @param isConfirm
	 *            是否需要设备确认
	 * @return
	 */
	public TracketPacket getPacket(String deviceNumber, boolean isConfirm) {
		sendPacket.deviceNumber = deviceNumber;
		sendPacket.order = setFunctionCode();
		sendPacket.isConfirm = isConfirm;
		return sendPacket;
	}

	public abstract String setFunctionCode();
}
