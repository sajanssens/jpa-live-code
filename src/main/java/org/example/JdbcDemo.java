package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // load class via classloader

        DriverManager.getConnection("jdbc:mysql://localhost:3307/hrm", "root", "root");
    }
}
