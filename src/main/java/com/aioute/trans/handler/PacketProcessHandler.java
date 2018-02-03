/**
 * 
 */
package com.aioute.trans.handler;

import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.route.BaseRouter;
import org.apache.log4j.Logger;

/**
 * 
 */
public class PacketProcessHandler {

	private static final Logger logger = Logger.getLogger(PacketProcessHandler.class);

	public void process(TracketPacket packet) {
		String order = packet.order.toUpperCase();
		BaseRouter router = BaseRouter.routerMap.get(order);
		if (router != null) {
			String parseResult = router.routePacket(packet);
			if (parseResult != null) {
				logger.info(parseResult);
			}
		} else {
			logger.info("不识别的命令字:" + order);
		}
	}
}
