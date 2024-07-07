package edu.ijse.service.custom;

import edu.ijse.dto.OrderDto;
import edu.ijse.service.SuperService;

public interface OrderService extends SuperService {
    public String placeOrder(OrderDto orderDto)throws Exception;
}
