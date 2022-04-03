package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static java.lang.System.exit;

public class FirstPage {
    @FXML
    private Button FirstPageLoginButton;

    @FXML
    private Button FirstPageSignUpButton;

    @FXML
    private Button FirstPageExitButton;

    public void OnFirstPageLoginButtonClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginPage(event);
    }
    public void OnFirstPageSignUpButtonClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToSignUpPage(event);
    }
    public void OnFirstPageExitButtonClick(ActionEvent event)
    {
        exit(1);
    }
}
