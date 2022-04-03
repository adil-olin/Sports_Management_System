package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

    LoginModel LoginPageloginModel = new LoginModel();

    @FXML
    private TextField LoginPagePasswordTextField;

    @FXML
    private TextField LoginPageEmailidTextField;

    @FXML
    private PasswordField LoginPagePasswordPasswordField;

    @FXML
    private Button LoginPageLoginButton;

    @FXML
    private Label LoginPageWrongLoginInformation;

    @FXML
    private Button LoginPageCancelButton;

    @FXML
    private CheckBox LoginPageShowPasswordCheckBox;
    @FXML
    void OnLoginPageShowPasswordCheckBoxClick(ActionEvent event) {
        if(LoginPageShowPasswordCheckBox.isSelected())
        {
            LoginPagePasswordTextField.setText(LoginPagePasswordPasswordField.getText());
            LoginPagePasswordPasswordField.setVisible(false);
            LoginPagePasswordTextField.setVisible(true);
            return;
        }
        LoginPagePasswordPasswordField.setText(LoginPagePasswordTextField.getText());
        LoginPagePasswordTextField.setVisible(false);
        LoginPagePasswordPasswordField.setVisible(true);
    }
    @FXML
    public void OnLoginPageLoginButtonClick(ActionEvent event) throws SQLException, IOException {
        String UserName = LoginPageEmailidTextField.getText();
        String UserPassword = LoginPagePasswordPasswordField.getText();
        if(this.LoginPageloginModel.isLogin(UserName,UserPassword))
        {
            SceneController sceneController = new SceneController();
            sceneController.SwitchToHomePage(event,UserName);
        }
        else
        {
            LoginPageWrongLoginInformation.setText("Your Password or User Name is incorrect");
        }
    }
    public void OnLoginPageCancelButtonClick(ActionEvent event) throws IOException, SQLException {
        this.LoginPageloginModel.connection.close();
        System.out.println("Login Page DataBase is not DisConnected");
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        LoginPagePasswordPasswordField.setVisible(true);
        LoginPagePasswordTextField.setVisible(false);

        if(this.LoginPageloginModel.isDataBaseConnected())
        {
            System.out.println("Login Page DataBase is Connected");
        }
        else
        {
            System.out.println("Login Page DataBase is not Connected");
        }
    }
}
