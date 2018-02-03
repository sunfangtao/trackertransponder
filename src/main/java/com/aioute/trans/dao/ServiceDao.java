package com.aioute.trans.dao;

import com.aioute.trans.model.ServiceModel;

import java.util.List;

public interface ServiceDao {

    public List<ServiceModel> getServiceInfo(String type);

    public boolean updateServiceDevice(int serviceId, String deviceNum);

    public List<ServiceModel> findTypeCount(String type);

    public boolean deleteFroService(String deviceNum);
}
