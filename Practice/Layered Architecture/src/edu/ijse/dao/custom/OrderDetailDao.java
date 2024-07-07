package edu.ijse.dao.custom;

import edu.ijse.dao.CrudDao;
import edu.ijse.entity.OrderDetailEntity;

public interface OrderDetailDao extends CrudDao<OrderDetailEntity,Object> {
}
// object type --> because order detail has two primary keys as string type we have to pass both of them