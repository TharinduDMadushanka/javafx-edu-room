package Table;

public class Customer {

    String nic;
    String name;
    String address;
    double salary;

    public Customer() {
    }

    public Customer(String nic, String name, String address, double salary) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.salary = salary;
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
}
