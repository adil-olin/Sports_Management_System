package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpPage implements Initializable {
    SignUpModel signUpModel = new SignUpModel();

    @FXML
    private TextField ConfirmPasswordTextField;

    @FXML
    private Label ShowStatusLabel;

    @FXML
    private Label dbStatus;

    @FXML
    private TextField EmailIdLabel;

    @FXML
    private Button Back;

    @FXML
    private Button Submit;

    @FXML
    private TextField UserName;

    @FXML
    private TextField UserPassword;

    public void BackButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    public void SubmitButtonPressed(ActionEvent event) throws IOException, SQLException {
        if(!UserName.getText().trim().isEmpty() && !UserPassword.getText().trim().isEmpty() && !EmailIdLabel.getText().trim().isEmpty() && !ConfirmPasswordTextField.getText().trim().isEmpty())
        {
            if(!UserPassword.getText().equals( ConfirmPasswordTextField.getText()))
            {
                ShowStatusLabel.setText("Password didn't match");
            }
            else
            {
                if(this.signUpModel.Signup(event,UserName.getText(),EmailIdLabel.getText(), UserPassword.getText()))
                {
                    SceneController sceneController = new SceneController();
                    sceneController.SwitchToFirstPage(event);
                }
                else
                {
                    ShowStatusLabel.setText("This Email already exists");
                }
            }
        }
        else
        {
            ShowStatusLabel.setText("Fill up all the data");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(this.signUpModel.isDataBaseConnected())
        {
            this.dbStatus.setText("Connected");
        }
        else
        {
            this.dbStatus.setText("Not Connected");
        }
    }
}
