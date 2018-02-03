package com.aioute.trans.sendpacket;

import com.aioute.trans.model.bean.BaseBean;

public class RegisterParam extends BaseSendParam {

	public RegisterParam(BaseBean bean) {
		super(bean);
	}

	@Override
	public String setFunctionCode() {
		return "AP05";
	}

}
