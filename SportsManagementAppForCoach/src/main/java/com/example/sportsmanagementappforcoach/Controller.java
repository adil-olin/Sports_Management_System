package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private Button loginButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private TextField TextFieldUsername;
    @FXML
    private TextField TextFieldPassword;

    @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, TextFieldUsername.getText(), TextFieldPassword.getText());
            }
        });
        SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  DBUtils.change_scene(event,"signup.fxml","SignUp",null);
            }
        });
    }
}
