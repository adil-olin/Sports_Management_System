package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FirstPage {
    @FXML
    private Button FirstPageLoginButton;

    @FXML
    private Button SignUp;

    public void LoginButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginPage(event);
    }
    public void FirstPageSignUpButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToSignUpPage(event);
    }

}
