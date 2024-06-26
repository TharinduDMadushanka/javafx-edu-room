package Controller;

import Dto.StudentDto;
import Model.StudentModel;

import java.sql.SQLException;

public class StudentController {
    private StudentModel studentModel;

    public StudentController() throws Exception{
        this.studentModel=new StudentModel();
    }

    public String saveStudent(StudentDto studentDto) throws Exception{
        String resp=studentModel.saveStudent(studentDto);
        return resp;
    }
}
