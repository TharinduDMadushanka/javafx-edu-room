package edu.ijse.UIController;

import edu.ijse.dto.ItemDto;
import edu.ijse.service.custom.impl.ItemServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ItemViewController {
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQoh;
    public TableView<ItemDto> itemTable;
    public TableColumn<ItemDto,String> colItemId;
    public TableColumn<ItemDto,String> colDescription;
    public TableColumn<ItemDto,String> colPackSize;
    public TableColumn<ItemDto,String> colQoh;
    public TableColumn<ItemDto,String> colUnitPrice;

    private ItemServiceImpl itemService  = new ItemServiceImpl();
    private ObservableList<ItemDto> itemList  = FXCollections.observableArrayList();

    public void initialize() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("pack"));
        colQoh.setCellValueFactory(new PropertyValueFactory<>("qoh"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        loadItem();
    }

    public void saveOnAction(ActionEvent actionEvent) {

        ItemDto item = new ItemDto(
                txtCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Integer.parseInt(txtQoh.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );

        try{
            String result = itemService.save(item);
            if ("Success".equals(result)) {
                itemList.add(item);
                itemTable.refresh();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Item Saved").showAndWait();
            }
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in Save...!").show();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    private void loadItem(){
        try{
            ArrayList<ItemDto> items = itemService.getAll();
            if(items != null){
                itemList.setAll(items);
                itemTable.setItems(itemList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearFields(){
        txtCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtQoh.clear();
        txtUnitPrice.clear();
    }
}
