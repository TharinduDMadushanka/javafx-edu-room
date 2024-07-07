package edu.ijse.dao;

import edu.ijse.dao.custom.impl.CustomerDaoImpl;
import edu.ijse.dao.custom.impl.ItemDaoImpl;
import edu.ijse.dao.custom.impl.OrderDaoImpl;
import edu.ijse.dao.custom.impl.OrderDetailDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    public SuperDao getDao(DaoType type) {
        switch (type){
            case ITEM:
                return new ItemDaoImpl();
            case CUSTOMER:
                return new CustomerDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDER_DETAIL:
                return new OrderDetailDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoType{
        ITEM,
        CUSTOMER,
        ORDER,
        ORDER_DETAIL
    }
}
