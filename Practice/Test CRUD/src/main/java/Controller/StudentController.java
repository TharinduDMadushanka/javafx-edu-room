package Controller;

import Dto.StudentDto;
import Model.StudentModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    private StudentModel studentModel;

    public StudentController() throws Exception{
        this.studentModel=new StudentModel();
    }

    public String saveStudent(StudentDto studentDto) throws Exception{
        String resp=studentModel.saveStudent(studentDto);
        return resp;
    }

    public ArrayList<StudentDto> getStudentList() throws Exception{
        ArrayList<StudentDto> studentDtos=new ArrayList<>();
        return studentDtos;
    }

    public StudentDto searchStudent(String name) throws Exception{
        StudentDto studentDto=studentModel.getStudent(name);
        return studentDto;
    }
}
