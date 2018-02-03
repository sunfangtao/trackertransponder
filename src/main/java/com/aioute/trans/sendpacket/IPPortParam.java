package com.aioute.trans.sendpacket;

import com.aioute.trans.model.bean.BaseBean;

public class IPPortParam extends BaseSendParam {

	public IPPortParam(BaseBean bean) {
        super(bean);
	}

	@Override
	public String setFunctionCode() {
		return "AP03";
	}

}