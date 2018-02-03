package com.aioute.trans.controller;

import com.aioute.trans.dao.ServiceDao;
import com.aioute.trans.model.ServiceModel;
import com.aioute.util.SendAppJSONUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("service")
public class ServiceController {

    private Logger logger = Logger.getLogger(ServiceController.class);
    @Resource
    private ServiceDao serviceDao;

    /**
     * 获取服务器列表信息
     *
     * @param req
     * @param res
     */
    @RequestMapping("serviceList")
    public void serviceList(HttpServletRequest req, HttpServletResponse res) {
        String resultJson = "";
        String type = req.getParameter("type");
        List<ServiceModel> modelList = serviceDao.findTypeCount(type);

        if (modelList.size() > 0) {
            resultJson = SendAppJSONUtil.getNormalString(modelList);
        } else {
            resultJson = SendAppJSONUtil.getNullResultObject();
        }
        logger.info("获取服务器列表信息：" + resultJson);
        try {
            res.getWriter().write(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
