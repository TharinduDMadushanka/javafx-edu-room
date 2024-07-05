package edu.ijse.service.custom;

import edu.ijse.dto.ItemDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface ItemService extends SuperService {
    String save(ItemDto itemDto)throws Exception;// Saves a new item using the data provided in the ItemDto object.
    String update(ItemDto itemDto)throws Exception;//Updates an existing item using the data provided in the ItemDto object.
    String delete(String itemCode)throws Exception;//Deletes an item identified by the itemCode.
    ItemDto get(String itemCode)throws Exception;// Retrieves an item identified by the itemCode.
    ArrayList<ItemDto> getAll()throws Exception;// Retrieves a list of all items.
}
