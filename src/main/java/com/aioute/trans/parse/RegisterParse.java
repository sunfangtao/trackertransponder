package com.aioute.trans.parse;

import com.aioute.trans.handler.RegisterHandler;
import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.send.PacketSend;
import com.aioute.trans.sendpacket.RegisterParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterParse extends BasePacketParse {

    private Logger logger = Logger.getLogger(RegisterParse.class);

    @Resource
    RegisterHandler registerHandler;

    @Override
    public String parse(final TracketPacket packet) {
        try {
            logger.info("设备注册:" + packet.deviceNumber);

            // 终端注册回复
            RegisterParam param = new RegisterParam(null);
            TracketPacket returnPacket = param.getPacket(packet.deviceNumber, false);
            try {
                new PacketSend().sendPacket(returnPacket);
            } catch (PacketSend.NOSessionException e) {
                logger.info("注册回复NOSessionException");
            }

            registerHandler.handlerPacket(packet);
        } catch (Exception e) {

        }
        return null;
    }
}
