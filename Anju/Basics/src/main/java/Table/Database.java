package Table;

import java.util.ArrayList;

public class Database {

    public static ArrayList<Customer> customersDataList = new ArrayList<>();

    static {
        customersDataList.add(new Customer("01","Nimal","Galle",75000));
        customersDataList.add(new Customer("02","Kamal","Matara",65000));
        customersDataList.add(new Customer("03","Amal","Colombo",90000));
        customersDataList.add(new Customer("04","Sunil","Hambanthota",45000));
    }
}
