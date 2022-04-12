package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import DBUtil.DBResources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePage {

    @FXML
    private Button HomePageAddTeamButton;

    @FXML
    private Button HomePageLogOutButton;

    @FXML
    private Label HomePageUserNameLabel;

    private Coach HomePageCoach;

    @FXML
    private VBox HomePageTeamButtonVbox;

    @FXML
    void OnHomePageAddTeamButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToAddTeamPage(event,HomePageCoach);
    }

    @FXML
    void OnHomePageLogOutButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }

    void setUserNameLabel(Coach coach) throws SQLException {
        HomePageCoach = coach;
        HomePageCoach.setTeamArrayList();
        for(int i=0;i<HomePageCoach.getTeamArrayList().size();i++)
        {
            Button tempButton = new Button(HomePageCoach.getTeamArrayList().get(i).getName());
            tempButton.setMaxWidth(Double.MAX_VALUE);
            int finalI = i;
            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    
                    SceneController sceneController = new SceneController();
                    try {
                        sceneController.SwitchToPlayerList(event,HomePageCoach, finalI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            HomePageTeamButtonVbox.getChildren().add(tempButton);
        }
        this.HomePageUserNameLabel.setText(HomePageCoach.getName());
    }
    String getUserNameLabel()
    {
        return HomePageUserNameLabel.getText();
    }
}
