package com.example.login_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLController {

    // Change "user" and "password" variables to your MySQL username and password
    final private String USER = "root";
    final private String PASSWORD = "";


    public boolean checkPassword(String username, String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        try {
            Class.forName(  "com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo_db", USER, PASSWORD
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select password from account_info where username=\"" + username+"\"");
            if (resultSet.next()) {
                if (resultSet.getString(1).equals(password)) {
                    return true;
                }
                return false;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;


    }
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo_db", USER, PASSWORD
            );

                //Create a "demo_db" database if it doesn't exist
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("CREATE DATABASE IF NOT EXISTS demo_db");

                //Go to "demo_db" database
                statement.execute("USE demo_db");

                //Create a "account_info" table if it doesn't exist
                statement.execute("CREATE TABLE `account_info` (\n" +
                        "  `account_id` int(10) NOT NULL,\n" +
                        "  `username` varchar(50) NOT NULL,\n" +
                        "  `password` varchar(50) NOT NULL,\n" +
                        "  `isAdmin` tinyint(1) NOT NULL\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci");

                statement.execute("INSERT INTO `account_info` (`account_id`, `username`, `password`, `isAdmin`) VALUES\n" +
                        "(1, 'admin', 'root', 1),\n" +
                        "(2, 'leeminhduc2', '123456', 0)");


            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
