package org.example;

import org.example.domain.Person;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hrm", "root", "root")) {
            // Class.forName("com.mysql.cj.jdbc.Driver"); // load class via classloader
            // Not needed: SPI does this.

            // create Person table
            Statement statement = connection.createStatement();

            DatabaseMetaData metaData1 = connection.getMetaData();

            statement.execute("CREATE TABLE IF NOT EXISTS Person (id int primary key not null AUTO_INCREMENT, name varchar(255) not null, age int)");

            // insert persons
            statement.execute("INSERT INTO Person(name, age) VALUES ('Bram', 43)");

            // select persons: print results
            ResultSet rs = statement.executeQuery("SELECT * FROM Person");
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                // Map resultset to person objects
                Person build = Person.builder().id(id).name(name).age(age).build();
                System.out.println(build);
            }

            PreparedStatement ps = connection.prepareStatement("UPDATE Person SET age = age+1 WHERE id = ?");
            ps.setInt(1, 1);
            ps.execute();

            try {
                connection.setAutoCommit(false);
                statement.execute("INSERT INTO Person(name, age) VALUES ('Trans', 1)");
                statement.execute("INSERT INTO Person(name, age) VALUES ('Trans', 2)");
                statement.execute("INSERT INTO Person(name, age) VALUES ('Trans', 3)");
                connection.commit();
            } catch (SQLException e) {
                System.err.println("Transaction failed: " + e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }

            // connection sluiten V
        }
    }
}
