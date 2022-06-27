package com.example.sportsmanagementappforcoach;

import DBUtil.DBConnection;
import DBUtil.DBResources;
import PROFILE.Coach;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CoachProfileEdit {
    private Coach CoachProfileEditCoach;
    @FXML
    private TextField CoachProfileEditAgeTextField;

    @FXML
    private Button CoachProfileEditButton;

    @FXML
    private PasswordField CoachProfileEditChangePasswordPassField;

    @FXML
    private Button CoachProfileEditConfirmButton;

    @FXML
    private Label CoachProfileEditEmailLabel;

    @FXML
    private Label CoachProfileEditNameLabel;

    @FXML
    void OnCoachProfileEditButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,CoachProfileEditCoach);
    }

    @FXML
    void OnCoachProfileEditConfirmButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        dbResources.setCoachAge(CoachProfileEditCoach.getEmailid(),Integer.parseInt(CoachProfileEditAgeTextField.getText()), CoachProfileEditChangePasswordPassField.getText());
        //CoachProfileEditCoach.setAge(CoachProfileEditChangePasswordPassField.getText());
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,CoachProfileEditCoach);
    }
    void setCoachProfileEdit(Coach coach) throws SQLException {
        DBResources dbResources = new DBResources();
        CoachProfileEditCoach = coach;
        coach.setAge(dbResources.getCoachtAge(coach.getEmailid()));
        CoachProfileEditEmailLabel.setText(coach.getEmailid());
        CoachProfileEditNameLabel.setText(coach.getName());
        CoachProfileEditAgeTextField.setText(Integer.toString(coach.getAge()));
        CoachProfileEditChangePasswordPassField.setText("jim");
    }


}
