package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBConnection;
import DBUtil.DBResources;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable{

    @FXML
    private Button HomePageAddTeamButton;

    @FXML
    private Button HomePageLogOutButton;

    @FXML
    private Label HomePageUserNameLabel;

    ArrayList<String>HomePageTeamNames=new ArrayList<>();

    @FXML
    private VBox HomePageAddTeamButtonVBox;

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

    private ArrayList<Button> HomePageTeamNameButtonList = new ArrayList<>();
    private Coach coach;
    void setUserNameLabel(String mailid) throws SQLException {
        DBResources dbResources = new DBResources();
        HomePageTeamNames = dbResources.getTeamLists(mailid);
        coach = dbResources.getCoachData(mailid);
        this.HomePageUserNameLabel.setText(coach.getName());

        int i = 3;
        for(i = 0; i < HomePageTeamNames.size(); i ++)
        {
            Button temp = new Button();
            temp.setText(HomePageTeamNames.get(i));
            HomePageTeamNameButtonList.add(temp);
        }
        HomePageAddTeamButtonVBox.getChildren().addAll(HomePageTeamNameButtonList);



    }
    @FXML
    void OnHomePageVboxMouseClick(MouseEvent event) {
        for(int i = 0; i < HomePageAddTeamButtonVBox.getChildren().size();i++)
        {
            Button button = (Button) HomePageAddTeamButtonVBox.getChildren().get(i);

        }
    }

    String getUserNameLabel()
    {
        return HomePageUserNameLabel.getText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBResources dbResources = new DBResources();
        Coach coachname = new Coach();
        try {
            HomePageTeamNames = dbResources.getTeamLists(coachname.getEmailId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
