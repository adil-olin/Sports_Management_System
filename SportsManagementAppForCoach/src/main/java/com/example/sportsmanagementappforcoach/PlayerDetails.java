package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayerDetails{
    private Player PlayerDetailssplayer;
    private Coach PlayerDetailsCoach;
    private int PlayerDetailsTeamid;
    private Coach PlayerListCoach;
    private int PlayerListTeamNumber;

    @FXML
    private Label PlayerDetailsAgeLabel;

    @FXML
    private Button PlayerDetailsBackButton;

    @FXML
    private Label PlayerDetailsNameLabel;

    @FXML
    private Label PlayerDetailsRoleLabel;

    @FXML
    private VBox PlayerDetailsSkillListVbox;

    @FXML
    private Button PlayerDetailsUpdateButton;

    @FXML
    private Button PlayerDetailsDeleteButton;

    @FXML
    void OnPlayerDetailsDeleteButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        dbResources.DeletePlayer(PlayerDetailssplayer);
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,PlayerDetailsCoach,PlayerDetailsTeamid);
    }
    @FXML
    void OnPlayerDetailsBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,PlayerDetailsCoach,PlayerDetailsTeamid);
    }

    @FXML
    void OnPlayerDetailsUpdateButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchtoUpdatePlayerSkill(event,PlayerDetailsCoach,PlayerDetailsTeamid,PlayerDetailssplayer);
    }
    public void setPlayerDetailsData(Coach coach , int id ,Player player ) throws SQLException {
        PlayerDetailsCoach = coach;
        PlayerDetailssplayer = player;
        PlayerDetailsTeamid = id;
        PlayerDetailsNameLabel.setText(player.getName());
        PlayerDetailsRoleLabel.setText(player.getRole());
        PlayerDetailsAgeLabel.setText(Integer.toString(player.getAge()));
        PlayerDetailsSkillListVbox.setSpacing(5);
        DBResources dbResources = new DBResources();
        PlayerDetailssplayer.setSkills(dbResources.getSkillList(coach.getEmailid(),coach.getTeamArrayList().get(id).getName(),player.getName()));
        for (int i=0;i<PlayerDetailssplayer.getSkills().size();i++)
        {
            PlayerSkilL skilL = PlayerDetailssplayer.getSkills().get(i);
            Label skillname = new Label(skilL.getSkillName());
            skillname.setPrefWidth(100);
            skillname.setPrefHeight(30);
            if(skilL.getSkillValueType()==1)
            {
                Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                skillvalue.setPrefWidth(200);
                skillvalue.setPrefHeight(30);
                skillvalue.setAlignment(Pos.CENTER);
                HBox tmphbox = new HBox(skillname,skillvalue);
                PlayerDetailsSkillListVbox.getChildren().add(tmphbox);
            }
            else if(skilL.getSkillValueType()==2)
            {
                ProgressBar progressBar = new ProgressBar();
                double tot = skilL.getValue();
                tot/=100.0;
                progressBar.setProgress(tot);
                progressBar.setPrefWidth(200);
                progressBar.setPrefHeight(30);
                Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                skillvalue.setPrefHeight(30);
                skillvalue.setPrefWidth(20);
                HBox tmphbox = new HBox(skillname,progressBar,skillvalue);
                PlayerDetailsSkillListVbox.getChildren().add(tmphbox);
            }
        }
    }
}
