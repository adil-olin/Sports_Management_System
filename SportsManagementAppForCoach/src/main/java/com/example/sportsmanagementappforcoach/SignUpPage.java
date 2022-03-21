package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class SignUpPage {
    @FXML
    private Button Back;

    @FXML
    private Button Submit;

    @FXML
    private TextField UserName;

    @FXML
    private TextField UserPassword;

    public SignUpPage() {
    }

    public void BackButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    public void SubmitButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
}
