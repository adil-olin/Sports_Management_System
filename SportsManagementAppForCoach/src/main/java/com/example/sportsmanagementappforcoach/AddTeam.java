package com.example.sportsmanagementappforcoach;

import COACH.Coach;

import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTeam implements Initializable {

    AddTeamModel addTeamModel = new AddTeamModel();
    @FXML
    private Button AddTeamConfirmButton;

    @FXML
    private TextField AddTeamTeamNameTextField;

    private DBResources AddTeamdbresources;

    Coach coach;

    @FXML
    private Label AddTeamInsertNameLabel;

    @FXML
    void OnAddTeamBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,coach.getEmailId());
    }
    @FXML
    void OnAddTeamConfirmButtonClick(ActionEvent event) throws SQLException, IOException {
        if(AddTeamTeamNameTextField.getText().trim().isEmpty())
        {
            AddTeamInsertNameLabel.setText("Put a name to the text field");
        }
        else
        {
            if(this.addTeamModel.AddTeamModelAddTeam(AddTeamTeamNameTextField.getText(),coach))
            {
                SceneController sceneController = new SceneController();
                sceneController.SwitchToHomePage(event,coach.getEmailId());
            }
            else
            {
                AddTeamInsertNameLabel.setText("This team name already exists");
            }
        }
    }
    void AddTeamSetCoach(String mailid) throws SQLException {
        AddTeamdbresources = new DBResources();
        coach = AddTeamdbresources.getCoachData(mailid);
        System.out.println(coach.getEmailId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(this.addTeamModel.isDataBaseConnected())
        {
            System.out.println("DataBase is connected to AddTeam Class");
        }
        else
        {
            System.out.println("DataBase is not connected to AddTeam Class");
        }
    }
}