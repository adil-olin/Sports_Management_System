package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
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
    private Button SignUpPageBackButton;

    @FXML
    private PasswordField SignUpPageConfirmPasswordPasswordField;

    @FXML
    private TextField SignUpPageConfirmPasswordTextField;

    @FXML
    private TextField SignUpPageEmailTextField;

    @FXML
    private PasswordField SignUpPagePasswordPasswordField;

    @FXML
    private TextField SignUpPagePasswordTextField;

    @FXML
    private CheckBox SignUpPageShowConfirmPasswordCheckBox;

    @FXML
    private CheckBox SignUpPageShowPasswordCheckBox;

    @FXML
    private Label SignUpPageShowStatusLabel;

    @FXML
    private Button SignUpPageSubmitButton;

    @FXML
    private TextField SignUpPageUserNameTextField;

    @FXML
    void OnSignUpPageShowConfirmPasswordCheckBoxClick(ActionEvent event) {
        if(SignUpPageShowConfirmPasswordCheckBox.isSelected())
        {
            SignUpPageConfirmPasswordTextField.setText(SignUpPageConfirmPasswordPasswordField.getText());
            SignUpPageConfirmPasswordPasswordField.setVisible(false);
            SignUpPageConfirmPasswordTextField.setVisible(true);
            return;
        }
        SignUpPageConfirmPasswordPasswordField.setText(SignUpPageConfirmPasswordTextField.getText());
        SignUpPageConfirmPasswordTextField.setVisible(false);
        SignUpPageConfirmPasswordPasswordField.setVisible(true);
        return;
    }

    @FXML
    void OnSignUpPageShowPasswordCheckBoxClick(ActionEvent event) {
        if(SignUpPageShowPasswordCheckBox.isSelected())
        {
            SignUpPagePasswordTextField.setText(SignUpPagePasswordPasswordField.getText());
            SignUpPagePasswordPasswordField.setVisible(false);
            SignUpPagePasswordTextField.setVisible(true);
            return;
        }
        SignUpPagePasswordPasswordField.setText(SignUpPagePasswordTextField.getText());
        SignUpPagePasswordTextField.setVisible(false);
        SignUpPagePasswordPasswordField.setVisible(true);
        return;
    }

    @FXML
    void OnSignUpPageSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        if(!SignUpPageShowPasswordCheckBox.isSelected())
        {
            SignUpPagePasswordTextField.setText(SignUpPagePasswordPasswordField.getText());
        }
        if(!SignUpPageShowConfirmPasswordCheckBox.isSelected())
        {
            SignUpPageConfirmPasswordTextField.setText(SignUpPageConfirmPasswordPasswordField.getText());
        }
        if(!SignUpPageUserNameTextField.getText().trim().isEmpty() && !SignUpPagePasswordTextField.getText().trim().isEmpty() && !SignUpPageEmailTextField.getText().trim().isEmpty() && !SignUpPageConfirmPasswordTextField.getText().trim().isEmpty())
        {
            if(!SignUpPagePasswordTextField.getText().equals( SignUpPageConfirmPasswordTextField.getText()))
            {
                SignUpPageShowStatusLabel.setText("Password didn't match");
            }
            else
            {
                if(this.signUpModel.Signup(event,SignUpPageUserNameTextField.getText(),SignUpPageEmailTextField.getText(), SignUpPagePasswordTextField.getText()))
                {
                    SceneController sceneController = new SceneController();
                    DBResources dbResources = new DBResources();
                    Coach coach = dbResources.getCoachData(SignUpPageEmailTextField.getText());
                    sceneController.SwitchToHomePage(event,coach);
                }
                else
                {
                    SignUpPageShowStatusLabel.setText("This Email already exists");
                }
            }
        }
        else
        {
            SignUpPageShowStatusLabel.setText("Fill up all the data");
        }
    }

    @FXML
    void OnSignupPageBackButtonClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SignUpPageConfirmPasswordPasswordField.setVisible(true);
        SignUpPageConfirmPasswordTextField.setVisible(false);
        SignUpPagePasswordTextField.setVisible(false);
        SignUpPagePasswordPasswordField.setVisible(true);
        if(this.signUpModel.isDataBaseConnected())
        {
            System.out.println("DataBase is connected in Signup Page");
        }
        else
        {
            System.out.println("DataBase is not connected in Signup Page");
        }
    }
}
