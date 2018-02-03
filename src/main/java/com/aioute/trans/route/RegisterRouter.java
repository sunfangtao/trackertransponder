package com.aioute.trans.route;

import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.parse.RegisterParse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 终端注册信息，命令字：BP05
 */
public class RegisterRouter extends BaseRouter {

    @Autowired
    private RegisterParse registerParse;

    @Override
    protected void addRouter() {
        routerMap.put("BP05", this);
    }

    @Override
    public String routePacket(TracketPacket tracketPacket) {
        return registerParse.parse(tracketPacket);
    }
}
