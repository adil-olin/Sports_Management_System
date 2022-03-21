package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Label LabelWelcome;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.change_scene(event,"Main.fxml","Login", null);
            }
        });
    }

}
