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
    private Button HomePageAddTeamButton;

    @FXML
    private Button HomePageLogOutButton;

    @FXML
    private Label HomePageUserNameLabel;

    @FXML
    void OnHomePageAddTeamButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToAddTeamPage(event,coach.getEmailId());
    }

    @FXML
    void OnHomePageLogOutButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }


    private Coach coach;

    void setUserNameLabel(String mailid) throws SQLException {
        DBResources dbResources = new DBResources();
        coach = dbResources.getCoachData(mailid);
        this.HomePageUserNameLabel.setText(coach.getName());
    }
    String getUserNameLabel()
    {
        return HomePageUserNameLabel.getText();
    }
}
