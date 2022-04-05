package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;

import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTeam implements Initializable {

    AddTeamModel addTeamModel = new AddTeamModel();
    @FXML
    private Button AddTeamConfirmButton;

    @FXML
    private TextField AddTeamTeamNameTextField;

    private DBResources AddTeamdbresources;

    private Coach AddTeamCoach;

    @FXML
    private Label AddTeamInsertNameLabel;

    @FXML
    void OnAddTeamBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,AddTeamCoach);
    }
    @FXML
    void OnAddTeamConfirmButtonClick(ActionEvent event) throws SQLException, IOException {
        if(AddTeamTeamNameTextField.getText().trim().isEmpty())
        {
            AddTeamInsertNameLabel.setText("Put a name to the text field");
        }
        else
        {
            if(this.addTeamModel.AddTeamModelAddTeam(AddTeamTeamNameTextField.getText(),AddTeamCoach))
            {
                SceneController sceneController = new SceneController();
                DBResources dbResources = new DBResources();
                AddTeamCoach.setTeamArrayList(dbResources.getTeamLists(AddTeamCoach));
                sceneController.SwitchToHomePage(event,AddTeamCoach);
            }
            else
            {
                AddTeamInsertNameLabel.setText("This team name already exists");
            }
        }
    }
    void AddTeamSetCoach(Coach coach) throws SQLException {
        AddTeamCoach = coach;
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