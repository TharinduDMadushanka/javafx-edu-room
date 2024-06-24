package Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class MainFormController {
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM,String> colId;
    public TableColumn<CustomerTM,String>  colName;
    public TableColumn<CustomerTM,String>  colAddress;
    public TableColumn<CustomerTM,Double>  colSalary;
    public TableColumn<CustomerTM, Button>  colDelete;

    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadData();

    }

    private void loadData(){

        ObservableList<CustomerTM> custList= FXCollections.observableArrayList();

        for(Customer c :Database.customersDataList){

            Button button=new Button("Delete");

            button.setOnAction(actionEvent -> {// remove when click delete button

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure ?", ButtonType.NO,ButtonType.YES);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if(buttonType.get() == ButtonType.YES){
                    Database.customersDataList.remove(c);
                    loadData();
                }

            });

            custList.add(new CustomerTM(
                    c.getNic(),
                    c.getName(),
                    c.getAddress(),
                    c.getSalary(),
                    button
            ));
        }

        tblCustomer.setItems(custList);
    }
}
