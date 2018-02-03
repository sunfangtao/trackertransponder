/**
 * 
 */
package com.aioute.trans.handler;

import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.session.SessionManager;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

/**
 *
 */
public class ServerHandler extends IoHandlerAdapter {

	private static final Logger log = Logger.getLogger(ServerHandler.class);
	private static final String PACK_PROCESS_HANDLER = "packetProcessHandler";
	public static final String TERMINAL_ID = "terminalAddress";

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.info("exceptionCaught-------------" + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		TracketPacket packet = (TracketPacket) message;

		log.info("解析结果：" + packet.toString());
		if (packet.order.equals("BP05")) {
			// 设备注册
			log.info("移除之前的连接");
			IoSession existSession = SessionManager.getSession(packet.deviceNumber);
			if (existSession != null)
				existSession.close(true);
		}
		SessionManager.addSession(packet.deviceNumber, session);

		// 服务器端收到消息信息
		PacketProcessHandler processHandler = (PacketProcessHandler) session.getAttribute(PACK_PROCESS_HANDLER);
		if (processHandler == null) {
			processHandler = new PacketProcessHandler();
			session.setAttribute(PACK_PROCESS_HANDLER, processHandler);
		}
		session.setAttribute(TERMINAL_ID, packet.deviceNumber);

		processHandler.process(packet);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("sessionClosed-------------");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
		cfg.setReceiveBufferSize(1024);
		cfg.setReadBufferSize(1024);
		cfg.setKeepAlive(true);
		cfg.setSoLinger(0); // 这个是根本解决问题的设置
		log.info("sessionCreated-------------");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("sessionOpened-------------");
		session.setAttribute(PACK_PROCESS_HANDLER, new PacketProcessHandler());
	}

}
