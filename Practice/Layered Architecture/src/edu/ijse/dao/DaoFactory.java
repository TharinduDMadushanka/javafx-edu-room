package edu.ijse.dao;

import edu.ijse.dao.custom.impl.CustomerDaoImpl;
import edu.ijse.dao.custom.impl.ItemDaoImpl;

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
            default:
                return null;
        }
    }

    public enum DaoType{
        ITEM,
        CUSTOMER
    }
}
