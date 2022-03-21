package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField CoachName;

    @FXML
    private TextField Password;

    @FXML
    private Button loginButton;


    @FXML
    String getCoachName() {
        return CoachName.getText();
    }

    @FXML
    String getPassword() {
        return Password.getText();
    }

    @FXML
    private Label wrongLoginInformation;

    @FXML
    void onLoginClick(ActionEvent event) {
        String UserName = getCoachName();
        String UserPassword = getPassword();
        wrongLoginInformation.setText("Your Password of User Name is incorrect");
    }


}
