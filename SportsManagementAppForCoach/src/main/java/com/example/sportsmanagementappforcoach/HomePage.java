package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePage {

    @FXML
    private Button HomePageAddTeamButton;

    @FXML
    private Button HomePageLogOutButton;

    @FXML
    private Label HomePageUserNameLabel;

    ArrayList<String>HomePageTeamNames;
    @FXML
    private VBox HomePageTeamButtonVbox;

    @FXML
    void OnHomePageAddTeamButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToAddTeamPage(event,coach.getEmailId());
    }

    @FXML
    void OnHomePageLogOutButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }


    private Coach coach;

    void setUserNameLabel(String mailid) throws SQLException {
        DBResources dbResources = new DBResources();
        HomePageTeamNames = dbResources.getTeamLists(mailid);
        for(int i=0;i<HomePageTeamNames.size();i++)
        {
            Button tempButton = new Button(HomePageTeamNames.get(i));
            tempButton.setMaxWidth(Double.MAX_VALUE);

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    SceneController sceneController = new SceneController();
                    try {
                        sceneController.SwitchToPlayerList(event,coach.getEmailId(),tempButton.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            HomePageTeamButtonVbox.getChildren().add(tempButton);
        }
        coach = dbResources.getCoachData(mailid);
        this.HomePageUserNameLabel.setText(coach.getName());
    }
    String getUserNameLabel()
    {
        return HomePageUserNameLabel.getText();
    }
}
