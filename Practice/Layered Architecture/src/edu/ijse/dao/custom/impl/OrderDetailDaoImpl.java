package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.OrderDetailDao;
import edu.ijse.entity.OrderDetailEntity;

import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean create(OrderDetailEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES(?,?,?,?)",t.getOrderId(),t.getItemCode(),t.getQty(),t.getDiscount());
    }

    @Override
    public boolean update(OrderDetailEntity orderDetailEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public OrderDetailEntity get(Object o) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetailEntity> getAll() throws Exception {
        return null;
    }
}
