package com.aioute.trans.route;

import com.aioute.trans.model.TracketPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础的路由数据信息
 * 
 */
public abstract class BaseRouter {

	public static Map<String, BaseRouter> routerMap = new HashMap<String, BaseRouter>();

	public BaseRouter() {
		addRouter();
	}

	protected abstract void addRouter();

	/**
	 * 进行数据包的一级路由
	 * 
	 * @param tracketPacket
	 */
	public abstract String routePacket(TracketPacket tracketPacket);

}
