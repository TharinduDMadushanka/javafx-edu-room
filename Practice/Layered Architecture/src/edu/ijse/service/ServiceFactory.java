package edu.ijse.service;

import edu.ijse.service.custom.ItemService;
import edu.ijse.service.custom.impl.CustomerServiceImpl;
import edu.ijse.service.custom.impl.ItemServiceImpl;
import edu.ijse.service.custom.impl.OrderServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType type){
        switch (type) {
            case ITEM:
                return new ItemServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceType {
        ITEM,
        CUSTOMER,
        ORDER
    }
}
