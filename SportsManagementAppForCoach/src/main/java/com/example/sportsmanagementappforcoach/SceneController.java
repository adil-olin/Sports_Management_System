package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import PROFILE.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SwitchScene(ActionEvent event , String fxmlfile) throws IOException {
        root =  FXMLLoader.load(getClass().getResource(fxmlfile));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToLoginPage(ActionEvent event) throws IOException {
        SwitchScene(event,"LoginPage.fxml");
    }
    public void SwitchToFirstPage(ActionEvent event) throws IOException {
        SwitchScene(event,"FirstPage.fxml");
    }
    public void SwitchToSignUpPage(ActionEvent event) throws IOException {
        SwitchScene(event,"SignUpPage.fxml");
    }
    public void SwitchToHomePage(ActionEvent event, Coach coach) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        root = loader.load();
        HomePage homePage = loader.getController();
        homePage.setUserNameLabel(coach);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToAddTeamPage(ActionEvent event ,Coach coach) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));
        root = loader.load();
        AddTeam addTeam = loader.getController();
        addTeam.AddTeamSetCoach(coach);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToPlayerList(ActionEvent event,Coach coach,int idx) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerList.fxml"));
        root = loader.load();
        PlayerList playerList = loader.getController();
        playerList.PlayerListSetData(coach,idx);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoAddPlayerPage(ActionEvent event , Coach coach,int idx) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlayerPage.fxml"));
        root = loader.load();
        AddPlayerPage addPlayerPage = loader.getController();
        addPlayerPage.AddPlayerSetData(coach,idx);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoPlayerDetailsPage(ActionEvent event, Coach coach , int id, Player player) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerDetails.fxml"));
        root = loader.load();
        PlayerDetails playerDetails = loader.getController();
        playerDetails.setPlayerDetailsData(coach,id,player);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
