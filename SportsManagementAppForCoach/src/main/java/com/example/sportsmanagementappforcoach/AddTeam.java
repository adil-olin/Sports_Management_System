package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddTeam {

    @FXML
    private Button AddTeamAddButton;

    @FXML
    private TextField AddTeamTeamNameTextField;

    private DBResources AddTeamdbresources;

    Coach coach;

    @FXML
    void OnAddButtonClick(ActionEvent event) throws SQLException, IOException {
        AddTeamdbresources = new DBResources();
        //AddTeamdbresources.AddTeamForCoach(this.AddTeamTeamNameTextField.getText(),coach);
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,coach.getEmailId());
    }
    void AddTeamSetCoach(String mailid) throws SQLException {
        AddTeamdbresources = new DBResources();
        coach = AddTeamdbresources.getCoachData(mailid);
        System.out.println(coach.getEmailId());
    }

}