package com.example.sportsmanagementappforcoach;

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
        root =  FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToSignUpPage(ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToHomePage(ActionEvent event,String useremailid) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        root = loader.load();
        HomePage homePage = loader.getController();
        homePage.setUserNameLabel(useremailid);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
