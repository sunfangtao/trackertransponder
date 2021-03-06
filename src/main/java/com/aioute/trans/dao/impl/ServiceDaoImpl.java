package com.aioute.trans.dao.impl;

import com.aioute.trans.dao.ServiceDao;
import com.aioute.trans.db.SqlConnectionFactory;
import com.aioute.trans.model.ServiceModel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ServiceDaoImpl implements ServiceDao {

    @Resource
    private SqlConnectionFactory sqlConnectionFactory;

    public List<ServiceModel> getServiceInfo(String type) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sb = new StringBuffer();
        sb.append("select * from trackerserver");
        if (type != null) {
            sb.append(" where type = ?");
        }
        List<ServiceModel> serviceList = new ArrayList<ServiceModel>();
        try {
            con = sqlConnectionFactory.getConnection();
            ps = con.prepareStatement(sb.toString());
            ps.setString(1, type);

            rs = ps.executeQuery();
            while (rs.next()) {
                ServiceModel serviceModel = new ServiceModel();
                serviceModel.setId(rs.getInt("id"));
                serviceModel.setIp(rs.getString("ip"));
                serviceModel.setPort(rs.getInt("port"));
                serviceModel.setType(rs.getString("type"));
                serviceList.add(serviceModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnectionFactory.closeConnetion(con, ps, rs);
        }
        return serviceList;
    }

    public boolean updateServiceDevice(int serviceId, String deviceNum) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sb = new StringBuffer();
        sb.append("insert into serverdevice (id,devicenum,time) values (?,?,?)");
        try {
            con = sqlConnectionFactory.getConnection();
            ps = con.prepareStatement(sb.toString());
            ps.setInt(1, serviceId);
            ps.setString(2, deviceNum);
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnectionFactory.closeConnetion(con, ps, rs);
        }
        return false;
    }

    public List<ServiceModel> findTypeCount(String type) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sb = new StringBuffer();
        sb.append("select a.*, count(b.id) as count from trackerserver a left join serverdevice b on a.id = b.id");
        if (type != null) {
            sb.append(" where a.type = ?");
        }
        sb.append(" group by a.id");
        ;

        List<ServiceModel> modelList = new ArrayList<ServiceModel>();
        try {
            con = sqlConnectionFactory.getConnection();
            ps = con.prepareStatement(sb.toString());
            if (type != null) {
                ps.setString(1, type);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                ServiceModel serviceModel = new ServiceModel();
                serviceModel.setId(rs.getInt("id"));
                serviceModel.setIp(rs.getString("ip"));
                serviceModel.setPort(rs.getInt("port"));
                serviceModel.setType(rs.getString("type"));
                serviceModel.setCount(rs.getInt("count"));

                modelList.add(serviceModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnectionFactory.closeConnetion(con, ps, rs);
        }
        return modelList;
    }

    public boolean deleteFroService(String deviceNum) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuffer sb = new StringBuffer();
        sb.append("delete from serverdevice where devicenum = ?");

        try {
            con = sqlConnectionFactory.getConnection();
            ps = con.prepareStatement(sb.toString());
            ps.setString(1, deviceNum);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnectionFactory.closeConnetion(con, ps, rs);
        }
        return false;
    }
}
