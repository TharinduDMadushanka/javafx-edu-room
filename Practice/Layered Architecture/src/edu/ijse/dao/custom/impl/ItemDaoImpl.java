package edu.ijse.dao.custom.impl;

import edu.ijse.dao.custom.ItemDao;
import edu.ijse.entity.ItemEntity;

import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean create(ItemEntity itemEntity) throws Exception {
        return false;
    }

    @Override
    public boolean update(ItemEntity itemEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ItemEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ItemEntity> getAll() throws Exception {
        return null;
    }
}
