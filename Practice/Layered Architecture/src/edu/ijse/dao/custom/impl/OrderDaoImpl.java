package edu.ijse.dao.custom.impl;

import edu.ijse.dao.custom.OrderDao;
import edu.ijse.entity.OrderEntity;

import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean create(OrderEntity orderEntity) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderEntity orderEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public OrderEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderEntity> getAll() throws Exception {
        return null;
    }
}
