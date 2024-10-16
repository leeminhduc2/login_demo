package com.example.login_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.sql.*;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Text status;

    @FXML
    private TextField userName;

    @FXML
    void Login(ActionEvent event) {
        String username = userName.getText();
        String pass = password.getText();
        if (checkPassword(username, pass)) {
            status.setText("Login Successful");
        } else {
            status.setText("Login Failed");
        }
    }

    @FXML
    private boolean checkPassword(String username, String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo_db", "root", ""
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



}
