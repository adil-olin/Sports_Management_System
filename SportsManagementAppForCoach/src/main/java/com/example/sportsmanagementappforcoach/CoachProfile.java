package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class CoachProfile {
    Coach CoachProfileCoach;
    @FXML
    private Label CoachProfileAgeLabel;

    @FXML
    private Label CoachProfileNameLabel;

    @FXML
    private Button CoachProfileUpdateButton;

    @FXML
    private Label CoachProfileEmailLabel;

    @FXML
    void OnCoachProfileUpdateButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchtoCoachProfileEdit(event,CoachProfileCoach);
    }

    @FXML
    void OnCoachProfileBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,CoachProfileCoach);
    }
    void setCoachProfileData(Coach coach) throws SQLException {
        DBResources dbResources = new DBResources();
        CoachProfileCoach = coach;
        CoachProfileNameLabel.setText(coach.getName());
        CoachProfileAgeLabel.setText(Integer.toString(dbResources.getCoachtAge(coach.getEmailid())));
        CoachProfileEmailLabel.setText(coach.getEmailid());

    }
}
