package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomePage {

    @FXML
    private Button FirstPageButton;

    @FXML
    private Label UserNameLabel;

    @FXML
    void OnFirstPageClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    void setUserNameLabel(String name)
    {
        this.UserNameLabel.setText(name);
    }
    String getUserNameLabel()
    {
        return UserNameLabel.getText();
    }

}
