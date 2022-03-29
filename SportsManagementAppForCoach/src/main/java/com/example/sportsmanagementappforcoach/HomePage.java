package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

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
    void setUserNameLabel(String mailid) throws SQLException {
        DBResources dbResources = new DBResources();
        String name = dbResources.getCoachData(mailid).getName();
        this.UserNameLabel.setText(name);
    }
    String getUserNameLabel()
    {
        return UserNameLabel.getText();
    }

}
