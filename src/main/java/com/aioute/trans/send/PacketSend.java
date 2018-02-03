package com.aioute.trans.send;

import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.session.SessionManager;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class PacketSend {

    private static Logger logger = Logger.getLogger(PacketSend.class);

    /**
     * @param packet 要发送的数据包
     * @return
     * @throws NOSessionException
     */
    public void sendPacket(TracketPacket packet) throws NOSessionException {
        String terminalAddress = packet.deviceNumber;
        IoSession session = SessionManager.getSession(terminalAddress);
        if (session == null) {
            throw new NOSessionException("找不到该设备的链接：" + terminalAddress);
        }
        String sendMessage = packet.encode();
        logger.info("发送数据的设备" + terminalAddress + " packet=" + sendMessage);
        session.write(sendMessage);
    }

    public class NOSessionException extends Exception {

        private static final long serialVersionUID = 1L;

        public NOSessionException(String message) {
            super(message);
        }
    }
}
