package com.aioute.trans.handler;

import com.aioute.trans.dao.ServiceDao;
import com.aioute.trans.model.ServiceModel;
import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.model.bean.IPPortBean;
import com.aioute.trans.send.PacketSend;
import com.aioute.trans.sendpacket.IPPortParam;
import com.aioute.trans.util.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterHandler {

    @Resource
    ServiceDao serviceDao;

    public synchronized void handlerPacket(TracketPacket packet) throws PacketSend.NOSessionException {

        serviceDao.deleteFroService(packet.deviceNumber);
        List<ServiceModel> serviceList = serviceDao.findTypeCount(Constant.TRACKER);
        int minCount = Integer.MAX_VALUE;
        ServiceModel serviceModel = null;
        for (ServiceModel model : serviceList) {
            if (minCount > model.getCount()) {
                minCount = model.getCount();
                serviceModel = model;
            }
        }

        IPPortBean bean = new IPPortBean();
        bean.ip = serviceModel.getIp();
        bean.port = serviceModel.getPort();
        IPPortParam param = new IPPortParam(bean);
        System.out.println("bean.ip=" + bean.ip);
//        if (bean.ip.equals("123.56.196.109")) {
            TracketPacket returnPacket = param.getPacket(packet.deviceNumber, true);
            new PacketSend().sendPacket(returnPacket);
//        }
        serviceDao.updateServiceDevice(serviceModel.getId(), packet.deviceNumber);
    }
}
