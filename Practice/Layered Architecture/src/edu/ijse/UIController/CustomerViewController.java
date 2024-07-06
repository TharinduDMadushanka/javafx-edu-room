package edu.ijse.UIController;

import edu.ijse.dto.CustomerDto;
import edu.ijse.service.custom.impl.CustomerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CustomerViewController {
    public TextField txtId;
    public TextField txtTitle;
    public TextField txtName;
    public TextField txtDob;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtSalary;
    public TextField txtPostal;

    public TableView<CustomerDto> customerTable;
    public TableColumn<CustomerDto,String> colId;
    public TableColumn<CustomerDto,String>  colTitle;
    public TableColumn<CustomerDto,String>  colName;
    public TableColumn<CustomerDto,String>  colDoB;
    public TableColumn<CustomerDto,String>  colSalary;
    public TableColumn<CustomerDto,String>  colAddress;
    public TableColumn<CustomerDto,String>  colCity;
    public TableColumn<CustomerDto,String>  colProvince;
    public TableColumn<CustomerDto,String>  colPostal;

    private CustomerServiceImpl customerService=new CustomerServiceImpl();
    private ObservableList<CustomerDto> customerList = FXCollections.observableArrayList();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("postal"));

        loadTable();

    }

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtDob.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostal.clear();
    }

    private void loadTable(){
        try {
            ArrayList<CustomerDto> customers = customerService.getAll();
            if(customers != null){
                customerList.addAll(customers);
                customerTable.setItems(customerList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
