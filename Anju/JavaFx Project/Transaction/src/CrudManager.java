import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudManager {

    public boolean saveData(Account account, Payment payment) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement(
                "INSERT INTO account VALUES(?,?,?)"
        );
        preparedStatement1.setString(1, account.getAccount_id());
        preparedStatement1.setString(2, account.getName());
        preparedStatement1.setDouble(3, account.getAmount());

        boolean isSaved = preparedStatement1.executeUpdate() > 0;

        if (isSaved) {
            PreparedStatement prepareStatement2 = connection.prepareStatement(
                    "INSERT INTO payment VALUES(?,?,?,?)"
            );

            prepareStatement2.setString(1, payment.getPay_id());
            prepareStatement2.setObject(2, payment.getPay_date());
            prepareStatement2.setDouble(3, payment.getPayment());
            prepareStatement2.setString(4, account.getAccount_id());

            return prepareStatement2.executeUpdate() > 0;
        } else {
            return false;
        }
    }

}
