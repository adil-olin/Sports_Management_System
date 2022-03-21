package com.example.sportsmanagementappforcoach;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root,600,500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}