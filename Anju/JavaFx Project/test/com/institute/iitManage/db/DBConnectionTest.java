/**
 * create test case for check if DBConnection is singleton or not
 */

package com.institute.iitManage.db;

//import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

class DBConnectionTest {

    //@org.junit.jupiter.api.Test

    void getInstance() {

        try {

            Connection c1 = DBConnection.getInstance().getConnection();
            Connection c2 = DBConnection.getInstance().getConnection();
            Connection c3 = DBConnection.getInstance().getConnection();
            Connection c4 = DBConnection.getInstance().getConnection();
            Connection c5 = DBConnection.getInstance().getConnection();
            Connection c6 = DBConnection.getInstance().getConnection();

            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);
            System.out.println(c4);
            System.out.println(c5);
            System.out.println(c6);

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new DBConnectionTest().getInstance();
    }
}