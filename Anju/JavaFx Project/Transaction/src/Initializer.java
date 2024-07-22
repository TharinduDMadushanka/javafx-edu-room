import java.sql.SQLException;
import java.util.Date;

public class Initializer {

    public static void main(String[] args) {
        CrudManager crudManager = new CrudManager();

        Account account1 = new Account("A-1","Kamal",50000);
        Payment payment1 = new Payment("P-1",new Date(),25000);

        try {

            System.out.println(
                    crudManager.saveData(account1, payment1)
            );

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
}
