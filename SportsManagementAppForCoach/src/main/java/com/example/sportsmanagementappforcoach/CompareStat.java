package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompareStat {

    @FXML
    private Button CompareStatBackButton;

    @FXML
    private Label CompareStatPlayer1NameLabel;

    @FXML
    private VBox CompareStatPlayerSkill2Vbox;

    @FXML
    private VBox CompareStatPlayerSkill1Vbox;

    @FXML
    private ChoiceBox<String> CompareStatPlayerNameChoiceBox;

    @FXML
    private Label CompareStatPlayerNameLabel;

    private Coach CompareStatCoach;
    private String TeamName;
    private int setValueInt;
    private Player CompareStatPlayer1;
    private Player CompareStatPlayer2;
    private int TeamId;
    @FXML
    void OnCompareStatBackButtonClick(ActionEvent event) throws SQLException, IOException {
        setValueInt = 0;
        SceneController sceneController = new SceneController();
        sceneController.SwitchtoPlayerDetailsPage(event,CompareStatCoach,TeamId,CompareStatPlayer1);
    }
    void SetCompareStatData(Coach coach, String teamname, Player player, int tdx) throws SQLException {
        CompareStatCoach = coach;
        TeamName = teamname;
        TeamId = tdx;
        CompareStatPlayer1 = player;
        CompareStatPlayer1NameLabel.setText(player.getName());
        DBResources dbResources = new DBResources();
        if (setValueInt==0)
        {
            CompareStatPlayerNameChoiceBox.setValue("Player");
        }
        ArrayList<Player> Players = dbResources.getPlayerLists(coach.getEmailid(),teamname);
        for (int i = 0; i < Players.size();i++)
        {
            CompareStatPlayerNameChoiceBox.getItems().add(Players.get(i).getName());
        }
        CompareStatPlayerSkill1Vbox.setSpacing(5);
        CompareStatPlayer1.setSkills(dbResources.getSkillList(coach.getEmailid(),teamname, player.getName()));
        for (int i=0;i<CompareStatPlayer1.getSkills().size();i++)
        {
            PlayerSkilL skilL = CompareStatPlayer1.getSkills().get(i);
            Label skillname = new Label(skilL.getSkillName());
            skillname.setPrefWidth(70);
            skillname.setPrefHeight(30);
            if(skilL.getSkillValueType()==1)
            {
                Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                skillvalue.setPrefWidth(100);
                skillvalue.setPrefHeight(30);
                skillvalue.setAlignment(Pos.CENTER);
                HBox tmphbox = new HBox(skillname,skillvalue);
                CompareStatPlayerSkill1Vbox.getChildren().add(tmphbox);
            }
            else if(skilL.getSkillValueType()==2)
            {
                ProgressBar progressBar = new ProgressBar();
                double tot = skilL.getValue();
                tot/=100.0;
                progressBar.setProgress(tot);
                progressBar.setPrefWidth(100);
                progressBar.setPrefHeight(30);
                Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                skillvalue.setPrefHeight(30);
                skillvalue.setPrefWidth(30);
                HBox tmphbox = new HBox(skillname,progressBar,skillvalue);
                CompareStatPlayerSkill1Vbox.getChildren().add(tmphbox);
            }
        }
        CompareStatPlayerNameChoiceBox.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue)->
        {
            CompareStatPlayerSkill2Vbox.getChildren().clear();
            CompareStatPlayerNameLabel.setText(newvalue);
            Player player2=null;
            for(int j = 0; j < coach.getTeamArrayList().get(tdx).getPlayerArrayList().size();j++)
            {
                if(coach.getTeamArrayList().get(tdx).getPlayerArrayList().get(j).getName().equals(newvalue))
                {
                    player2 = coach.getTeamArrayList().get(tdx).getPlayerArrayList().get(j);
                    break;
                }
            }
            CompareStatPlayer2 = player2;
            try {
                CompareStatPlayer2.setSkills(dbResources.getSkillList(coach.getEmailid(),coach.getTeamArrayList().get(tdx).getName(),player2.getName()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < CompareStatPlayer2.getSkills().size(); i++) {
                PlayerSkilL skilL = CompareStatPlayer2.getSkills().get(i);
                Label skillname = new Label(skilL.getSkillName());
                skillname.setPrefWidth(70);
                skillname.setPrefHeight(30);
                if (skilL.getSkillValueType() == 1) {
                    Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                    skillvalue.setPrefWidth(120);
                    skillvalue.setPrefHeight(30);
                    skillvalue.setAlignment(Pos.CENTER);
                    HBox tmphbox = new HBox(skillname, skillvalue);
                    CompareStatPlayerSkill2Vbox.getChildren().add(tmphbox);
                } else if (skilL.getSkillValueType() == 2) {
                    ProgressBar progressBar = new ProgressBar();
                    double tot = skilL.getValue();
                    tot /= 100.0;
                    progressBar.setProgress(tot);
                    progressBar.setPrefWidth(120);
                    progressBar.setPrefHeight(30);
                    Label skillvalue = new Label(Integer.toString(skilL.getValue()));
                    skillvalue.setPrefHeight(30);
                    skillvalue.setPrefWidth(30);
                    HBox tmphbox = new HBox(skillname, progressBar, skillvalue);
                    CompareStatPlayerSkill2Vbox.getChildren().add(tmphbox);
                }
            }
        });
    }


}
