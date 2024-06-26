package Model;

import Database.DBConnection;
import Dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentModel {
    private final Connection connection;

    public StudentModel() throws SQLException, ClassNotFoundException {
        this.connection= DBConnection.getInstance().getConnection();
    }

    public String saveStudent(StudentDto studentDto) throws SQLException {
        String sql="INSERT INTO student (name, age, grade) VALUES(?,?,?)";

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,studentDto.getName());
        statement.setInt(2,studentDto.getAge());
        statement.setInt(3,studentDto.getGrade());

        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    }

}
