package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddPlayerPage {

    private Coach AddPlayerCoach;
    private String AddPlayerTeamName;
    private int AddPlayerTeamNumber;

    @FXML
    private Button AddPlayerBackButton;

    @FXML
    private TextField AddPlayerNameTextField;

    @FXML
    private Button AddPlayerSubmitButton;

    @FXML
    private Label AddPlayerPageCheckLabel;

    @FXML
    void OnAddPlayerBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,AddPlayerCoach,AddPlayerTeamNumber);
    }

    @FXML
    void OnAddPlayerSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        String name = AddPlayerNameTextField.getText();
        if(name.isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert A name");
        }
        else if(dbResources.insertPlayer(AddPlayerCoach,AddPlayerTeamNumber,name))
        {
            SceneController sceneController = new SceneController();
            sceneController.SwitchToPlayerList(event,AddPlayerCoach,AddPlayerTeamNumber);
        }
        else
        {
            AddPlayerPageCheckLabel.setText("This player Alredy exists on this team");
        }
    }


    public void AddPlayerSetData(Coach coach, int idx) {
        AddPlayerCoach = coach;
        AddPlayerTeamNumber = idx;
    }
}
