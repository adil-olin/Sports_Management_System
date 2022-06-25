package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import PROFILE.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamListEdit {

    private Coach TeamListEditCoach;
    ArrayList<Team> TeamListEditTeam = new ArrayList<Team>();
    ArrayList<Team> deletedTeam = new ArrayList<Team>();

    @FXML
    private Label TeamListEditTeamListLabel;

    @FXML
    private VBox TeamListEditTeamListVBox;

    @FXML
    private Button TeamListEditBackButton;

    @FXML
    private Button TeamListEditConfirmButton;

    @FXML
    void OnTeamListEditConfirmButtonClick(ActionEvent event) throws SQLException, IOException {
        for (int i = 0; i < deletedTeam.size();i++)
        {
            DBResources dbResources = new DBResources();
            dbResources.DeleteTeam(TeamListEditCoach,deletedTeam.get(i));
        }
        deletedTeam.removeAll(deletedTeam);
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,TeamListEditCoach);
    }
    @FXML
    void OnTeamListEditBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,TeamListEditCoach);
    }
    void setTeamListEditData(Coach coach) throws SQLException {
        TeamListEditCoach = coach;
        DBResources dbResources = new DBResources();
        TeamListEditTeam=dbResources.getTeamLists(coach);
        for (int i = 0; i < TeamListEditTeam.size();i++)
        {
            TextField textField = new TextField(TeamListEditTeam.get(i).getName());
            Button tempButton = new Button("Delete");
            tempButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent event) {
                    for (int i = 0; i < TeamListEditTeamListVBox.getChildren().size();i++)
                    {
                        HBox tempHbox = (HBox) TeamListEditTeamListVBox.getChildren().get(i);
                        Button tempbutton = (Button) tempHbox.getChildren().get(1);
                        if(tempButton.equals(tempbutton))
                        {
                            deletedTeam.add(TeamListEditTeam.get(i));
                            TeamListEditTeamListVBox.getChildren().remove(tempHbox);
                            TeamListEditTeam.remove(i);
                            break;
                        }
                    }
            }});
            HBox teamlisthbox = new HBox(textField,tempButton);
            TeamListEditTeamListVBox.getChildren().add(teamlisthbox);
        }

    }

}
