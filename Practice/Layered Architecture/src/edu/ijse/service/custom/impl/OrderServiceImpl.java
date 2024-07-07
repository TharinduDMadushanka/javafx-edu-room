package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.ItemDao;
import edu.ijse.dao.custom.OrderDao;
import edu.ijse.dao.custom.OrderDetailDao;
import edu.ijse.db.DBConnection;
import edu.ijse.dto.OrderDetailDto;
import edu.ijse.dto.OrderDto;
import edu.ijse.entity.ItemEntity;
import edu.ijse.entity.OrderDetailEntity;
import edu.ijse.entity.OrderEntity;
import edu.ijse.service.custom.OrderService;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = (OrderDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDER);
    private OrderDetailDao orderDetailDao= (OrderDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDER_DETAIL);
    private ItemDao itemDao= (ItemDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ITEM);

    @Override
    public String placeOrder(OrderDto orderDto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            OrderEntity orderEntity = new OrderEntity(orderDto.getOrederId(),orderDto.getCustId(),orderDto.getDate());
            if (orderDao.create(orderEntity)){
                boolean isOrderDetailSaved = true;

                for (OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()){
                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity(
                            orderDto.getOrederId(),
                            orderDetailDto.getItemCode(),
                            orderDetailDto.getQty(),
                            orderDetailDto.getDiscount()
                    );
                    if (!orderDetailDao.create(orderDetailEntity)){
                        isOrderDetailSaved = false;
                    }
                }

                if (isOrderDetailSaved){

                    boolean isItemSaved = true;

                    for(OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()){
                        ItemEntity itemEntity = itemDao.get(orderDetailDto.getItemCode());
                        if (itemEntity != null){
                            itemEntity.setQoh(itemEntity.getQoh()-orderDetailDto.getQty());

                            if (!itemDao.update(itemEntity)){
                                isItemSaved = false;
                            }
                        }
                    }
                    if (isItemSaved){
                        connection.commit();
                        return "Success";
                    }else {
                        connection.rollback();
                        return "Item Update Failed";
                    }
                }else {
                    connection.rollback();
                    return "OrderDetail Insert Failed";
                }

            }else {
                connection.rollback();
                return "Order Insert Failed";
            }

        }catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
