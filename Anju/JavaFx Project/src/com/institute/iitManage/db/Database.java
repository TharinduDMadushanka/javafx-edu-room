package com.institute.iitManage.db;

import com.institute.iitManage.model.User;
import com.institute.iitManage.util.security.PasswordManager;

import java.util.ArrayList;

public class Database {

    public static ArrayList<User> userTable = new ArrayList();

    static {
        userTable.add(new User(
                "Amal",
                "Perera",
                "amal@gmail.com",
                new PasswordManager().encrypt("1234")
        ));
    }
}
