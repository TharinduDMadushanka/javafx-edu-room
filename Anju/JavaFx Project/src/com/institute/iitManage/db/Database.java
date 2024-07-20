package com.institute.iitManage.db;

import com.institute.iitManage.model.*;
import com.institute.iitManage.util.security.PasswordManager;
import javafx.scene.control.Button;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student> studentTable = new ArrayList<>();
    public static ArrayList<Teacher> teacherTable = new ArrayList<>();
    public static ArrayList<Course> courseTable = new ArrayList<>();
    public static ArrayList<Intake> intakeTable = new ArrayList<>();
    public static ArrayList<Registration> registrationTable = new ArrayList<>();

    static {
        // Initialize userTable
        userTable.add(new User(
                "Amal",
                "Perera",
                "a",
                new PasswordManager().encrypt("1")
        ));

        // Define the date format based on the input string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Parse the date string to LocalDate
        LocalDate localDate = LocalDate.parse("2024/07/20", formatter);

        // Convert LocalDate to java.sql.Date
        Date sqlDate = Date.valueOf(localDate);

        // Initialize intakeTable
        intakeTable.add(new Intake(
                "I-1",
                "Summar",
                localDate, // LocalDate in the object
                "SE",
                false,
                new Button("Delete")
        ));

        // Initialize registrationTable with some sample data
        registrationTable.add(new Registration(
                "R-1",
                "John Doe",
                LocalDate.of(2024, 7, 20),
                "SE",
                "Paid"
        ));
        registrationTable.add(new Registration(
                "R-2",
                "Jane Smith",
                LocalDate.of(2024, 7, 21),
                "IT",
                "Pending"
        ));

//        static {
//            courseTable.add(new Course("Java Basics"));
//            courseTable.add(new Course("Advanced Java"));
//            courseTable.add(new Course("Database Management"));
//            // Add other courses as needed
//        }



    }
}
