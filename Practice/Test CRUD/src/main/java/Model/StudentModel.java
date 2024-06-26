package Model;

import Database.DBConnection;
import Dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<StudentDto> getAllStudent() throws SQLException {
        String sql = "SELECT * FROM student";
        PreparedStatement statement=connection.prepareStatement(sql);
        ResultSet rst=statement.executeQuery();

        ArrayList<StudentDto> studentDtos=new ArrayList<>();

        while(rst.next()){
            StudentDto studentDto=new StudentDto(rst.getString("name"),rst.getInt("age"),rst.getInt("grade"));
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public StudentDto getStudent(String studentName) throws SQLException {
        String sql = "SELECT * FROM student WHERE name = ?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,studentName);
        ResultSet rst=statement.executeQuery();

        if(rst.next()){
            return new StudentDto(rst.getString("name"),rst.getInt("age"),rst.getInt("grade"));

        }
        return null;
    }
}
