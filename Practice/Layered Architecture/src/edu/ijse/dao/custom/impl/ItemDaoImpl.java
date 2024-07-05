package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.ItemDao;
import edu.ijse.entity.ItemEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao{

    @Override
    public boolean create(ItemEntity t) throws Exception {//Inserts a new item into the database.
        return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?)", t.getItemCode(), t.getDescription(), t.getPack(), t.getUnitPrice(), t.getQoh());
    }


    @Override
    public boolean update(ItemEntity t) throws Exception {// Updates an existing item in the database.
        return CrudUtil.executeUpdate("UPDATE ITEM SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?",
                t.getDescription(), t.getPack(), t.getUnitPrice(), t.getQoh(), t.getItemCode());
    }

    @Override
    public boolean delete(String id) throws Exception {// Deletes an item from the database based on its item code.
        return CrudUtil.executeUpdate("DELETE FROM item WHERE ItemCode = ?", id);
    }

    @Override
    public ItemEntity get(String id) throws Exception {//Retrieves an item from the database based on its item code.
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item WHERE ItemCode = ?", id);
        if(rst.next()){
            ItemEntity entity = new ItemEntity(rst.getString("ItemCode"),
                    rst.getString("Description"), rst.getString("PackSize"),
                    rst.getInt("QtyOnHand"), rst.getDouble("UnitPrice"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<ItemEntity> getAll() throws Exception {//Retrieves all items from the database.
        ArrayList<ItemEntity> itemEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            ItemEntity entity = new ItemEntity(rst.getString("ItemCode"),
                    rst.getString("Description"), rst.getString("PackSize"),
                    rst.getInt("QtyOnHand"), rst.getDouble("UnitPrice"));
            itemEntities.add(entity);
        }
        return itemEntities;
    }
}
