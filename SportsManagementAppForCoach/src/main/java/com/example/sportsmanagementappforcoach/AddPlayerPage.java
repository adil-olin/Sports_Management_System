package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPlayerPage {

    Coach AddPlayerCoach;
    String AddPlayerTeamName;

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
        sceneController.SwitchToPlayerList(event,AddPlayerCoach.getEmailId(),AddPlayerTeamName);
    }

    @FXML
    void OnAddPlayerSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        String name = AddPlayerNameTextField.getText();
        if(name.isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert A name");
        }
        else if(dbResources.insertPlayer(AddPlayerCoach.getEmailId(),AddPlayerTeamName,name))
        {
            SceneController sceneController = new SceneController();
            sceneController.SwitchToPlayerList(event,AddPlayerCoach.getEmailId(),AddPlayerTeamName);
        }
        else
        {
            AddPlayerPageCheckLabel.setText("This player Alredy exists on this team");
        }

    }


    public void AddPlayerSetData(String emailid,String teamname) throws SQLException {
        DBResources dbResources = new DBResources();
        AddPlayerTeamName = teamname;
        AddPlayerCoach = dbResources.getCoachData(emailid);
    }
}
