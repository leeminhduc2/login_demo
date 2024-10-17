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
        SQLController sqlController = new SQLController();
        String username = userName.getText();
        String pass = password.getText();
        if (sqlController.checkPassword(username, pass)) {
            status.setText("Login Successful");
        } else {
            status.setText("Login Failed");
        }
    }


}
