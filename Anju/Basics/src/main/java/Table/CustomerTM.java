package Table;

import javafx.scene.control.Button;

public class CustomerTM {

    String nic;
    String name;
    String address;
    double salary;
    Button button;

    public CustomerTM() {
    }

    public CustomerTM(String nic, String name, String address, double salary, Button button) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.button = button;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
