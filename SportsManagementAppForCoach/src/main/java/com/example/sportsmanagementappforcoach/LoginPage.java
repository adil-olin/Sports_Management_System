package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

    LoginModel loginModel = new LoginModel();
    @FXML
    private Label dbStatus;

    @FXML
    private TextField CoachName;

    @FXML
    private PasswordField Password;

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
    private Button Back;

    @FXML
    public void onLoginClick(ActionEvent event) throws SQLException, IOException {
        String UserName = getCoachName();
        String UserPassword = getPassword();
        if(this.loginModel.isLogin(UserName,UserPassword))
        {
            SceneController sceneController = new SceneController();
            sceneController.SwitchToHomePage(event,UserName);
        }
        else
        {
            wrongLoginInformation.setText("Your Password or User Name is incorrect");
        }

    }
    public void BackButtonPressed(ActionEvent event) throws IOException, SQLException {
        this.loginModel.connection.close();
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        if(this.loginModel.isDataBaseConnected())
        {
            this.dbStatus.setText("Connected");
        }
        else
        {
            this.dbStatus.setText("Not Connected");
        }
    }
}
