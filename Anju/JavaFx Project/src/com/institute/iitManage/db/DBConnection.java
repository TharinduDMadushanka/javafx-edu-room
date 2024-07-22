package com.institute.iitManage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Step 01
    private static DBConnection dbConnection;

    // Step 02
    private Connection connection;

    // Step 03
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iitmanage","root","Thariya920@");
    }

    // Step 04
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    // Step 05
    public Connection getConnection() {
        return connection;
    }
}
