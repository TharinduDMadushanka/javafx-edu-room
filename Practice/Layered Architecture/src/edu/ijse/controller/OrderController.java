package edu.ijse.controller;

import edu.ijse.dto.OrderDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.OrderService;

public class OrderController {
    private OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ORDER);

    public String placeOrder(OrderDto orderDto)throws Exception{
        return orderService.placeOrder(orderDto);
    }
}
