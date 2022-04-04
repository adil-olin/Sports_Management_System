package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerList {

    Coach PlayerListcoach;
    DBResources PlayerListDBResources = new DBResources();
    String PlayerListTeamName;
    ArrayList<String>PlayerListPlayerNames;

    @FXML
    private Label PlayerListTeamNameLabel;

    @FXML
    private Button PlayerListAddPlayerButton;

    @FXML
    private Button PlayerListBackButton;

    @FXML
    private Button PlayerListLogOutButton;

    @FXML
    private Label UserNameLabel;

    @FXML
    private VBox PlayerListPlayerNameButtonVbox;

    @FXML
    void OnPlayerListAddPlayerButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchtoAddPlayerPage(event,PlayerListcoach.getEmailId(),PlayerListTeamName);
    }

    @FXML
    void OnPlayerListBackButtonCLick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event , PlayerListcoach.getEmailId());
    }

    @FXML
    void OnPlayerListLogOutButtonClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }

    void PlayerListSetData(String emailid,String teamname) throws SQLException {
        DBResources dbResources = new DBResources();
        PlayerListTeamName = teamname;
        PlayerListTeamNameLabel.setText(PlayerListTeamName);
        PlayerListcoach = dbResources.getCoachData(emailid);
        PlayerListPlayerNames = dbResources.getPlayerLists(emailid,PlayerListTeamName);
        for(int i=0;i<PlayerListPlayerNames.size();i++)
        {
            System.out.println(PlayerListPlayerNames.get(i));
            Button tempButton = new Button(PlayerListPlayerNames.get(i));
            tempButton.setMaxWidth(Double.MAX_VALUE);

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    System.out.println("You pressed the button "+tempButton.getText());
                }
            });

            PlayerListPlayerNameButtonVbox.getChildren().add(tempButton);
        }
    }
}
