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

public class SceneController<Public> {
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
    public void SwitchToEditTeamPage(ActionEvent event,Coach coach, int idx) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTeam.fxml"));
        root = loader.load();
        EditTeam editTeam = loader.getController();
        editTeam.EditTeamSetCoach(coach, idx);
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
    public void SwitchtoUpdatePlayerSkill(ActionEvent event,Coach coach,int idx,Player player) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePlayerSkill.fxml"));
        root = loader.load();
        UpdatePlayerSkill updatePlayerSkill = loader.getController();
        updatePlayerSkill.UpdatePlayerDataset(coach,idx,player);
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
    public void SwitchtoEditTeamListPage(ActionEvent event,Coach coach) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamListEdit.fxml"));
        root = loader.load();
        TeamListEdit teamListEdit = loader.getController();
        teamListEdit.setTeamListEditData(coach);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void SwitchtoCompareStatPage(ActionEvent event,Coach coach, String teamName, Player player, int tdx) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CompareStat.fxml"));
        root = loader.load();
        CompareStat compareStat = loader.getController();
        compareStat.SetCompareStatData(coach,teamName, player,tdx) ;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void SwitchtoCoachProfile(ActionEvent event,Coach coach) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachProfile.fxml"));
        root = loader.load();
        CoachProfile coachProfile = loader.getController();
        coachProfile.setCoachProfileData(coach); ;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void SwitchtoCoachProfileEdit(ActionEvent event,Coach coach) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachProfileEdit.fxml"));
        root = loader.load();
        CoachProfileEdit coachProfileEdit = loader.getController();
        coachProfileEdit.setCoachProfileEdit(coach) ;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
