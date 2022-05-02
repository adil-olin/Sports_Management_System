package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class PlayerDetails {
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
    private Button PlayerListUpdateButton;

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
        PlayerDetailsSkillListVbox = new VBox();
        DBResources dbResources = new DBResources();
        PlayerDetailssplayer.setSkills(dbResources.getSkillList(coach.getEmailid(),coach.getTeamArrayList().get(id).getName(),player.getName()));
        for (int i=0;i<PlayerDetailssplayer.getSkills().size();i++)
        {
            PlayerSkilL skilL = new PlayerSkilL();
            TextField skillname = new TextField(PlayerDetailssplayer.getSkills().get(i).getSkillName());
            skillname.setPrefWidth(100);
            if(skilL.getSkillValueType()==1)
            {
                TextField skillvalue = new TextField(Integer.toString(PlayerDetailssplayer.getSkills().get(i).getValue()));
                skillvalue.setPrefWidth(100);
                HBox tmphbox = new HBox(skillname,skillvalue);
                PlayerDetailsSkillListVbox.getChildren().add(tmphbox);
            }
            else if(skilL.getSkillValueType()==2)
            {
                ProgressBar progressBar = new ProgressBar(PlayerDetailssplayer.getSkills().get(i).getValue());
                progressBar.setPrefWidth(80);
                TextField skillvalue = new TextField(Integer.toString(PlayerDetailssplayer.getSkills().get(i).getValue())+"%");

                skillvalue.setPrefWidth(20);
                HBox tmphbox = new HBox(skillname,progressBar,skillvalue);
                PlayerDetailsSkillListVbox.getChildren().add(tmphbox);
            }
        }
    }
}
