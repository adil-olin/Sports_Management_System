package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import DBUtil.DBResources;
import PROFILE.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerList {

    DBResources PlayerListDBResources = new DBResources();

    private Coach PlayerListCoach;
    private int PlayerListTeamNumber;
    private Player player;
    @FXML
    private Button PlayerListEditTeamButton;

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
        sceneController.SwitchtoAddPlayerPage(event,PlayerListCoach,PlayerListTeamNumber);
    }

    @FXML
    void OnPlayerListBackButtonCLick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event , PlayerListCoach);
    }

    @FXML
    void OnPlayerListLogOutButtonClick(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    @FXML
    void  OnPlayerListEditTeamButtonClick(ActionEvent event) throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToEditTeamPage(event,PlayerListCoach,PlayerListTeamNumber);
    }
    void PlayerListSetData(Coach coach,int idx) throws SQLException {
        DBResources dbResources = new DBResources();
        PlayerListCoach = coach;
        PlayerListTeamNumber = idx;
        this.player=player;
        PlayerListPlayerNameButtonVbox.setSpacing(5);
        PlayerListCoach.getTeamArrayList().get(idx).setPlayerArrayList(dbResources.getPlayerLists(coach.getEmailid(),coach.getTeamArrayList().get(idx).getName()));
        for(int i=0;i<PlayerListCoach.getTeamArrayList().get(idx).getPlayerArrayList().size();i++)
        {
            Button tempButton = new Button(PlayerListCoach.getTeamArrayList().get(idx).getPlayerArrayList().get(i).getName());

            tempButton.setMaxWidth(Double.MAX_VALUE);

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    SceneController sceneController = new SceneController();
                    DBResources dbResources = new DBResources();
                    Player player = new Player();
                    for(int i=0;i<PlayerListCoach.getTeamArrayList().get(PlayerListTeamNumber).getPlayerArrayList().size();i++)
                    {
                        if(tempButton.getText().equals(PlayerListCoach.getTeamArrayList().get(PlayerListTeamNumber).getPlayerArrayList().get(i).getName()))
                        {
                            player = PlayerListCoach.getTeamArrayList().get(PlayerListTeamNumber).getPlayerArrayList().get(i);
                            break;
                        }
                    }
                    try {
                        sceneController.SwitchtoPlayerDetailsPage(event,PlayerListCoach,PlayerListTeamNumber,player);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            PlayerListPlayerNameButtonVbox.getChildren().add(tempButton);
        }
    }
}
