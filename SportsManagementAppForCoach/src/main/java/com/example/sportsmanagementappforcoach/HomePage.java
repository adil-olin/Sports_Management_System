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
    private Button HomePageAddTeamButton;

    private Coach coach;

    @FXML
    void OnHomePageAddTeamButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToAddTeamPage(event,coach.getEmailId());

    }


    @FXML
    void OnFirstPageClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    void setUserNameLabel(String mailid) throws SQLException {
        DBResources dbResources = new DBResources();
        coach = dbResources.getCoachData(mailid);
        this.UserNameLabel.setText(coach.getName());
    }
    String getUserNameLabel()
    {
        return UserNameLabel.getText();
    }

}
