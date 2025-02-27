package com.vt.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static final private String URL = "jdbc:mysql://localhost:3306/project";
    static final private String USERNAME = "root";
    static final private String PASSWORD = "22**31";

    public final static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
