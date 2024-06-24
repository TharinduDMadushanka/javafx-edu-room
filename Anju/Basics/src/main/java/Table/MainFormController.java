package Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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




        ObservableList<CustomerTM> custList= FXCollections.observableArrayList();

        for(Customer c :Database.customersDataList){

            Button button=new Button("Delete");

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
