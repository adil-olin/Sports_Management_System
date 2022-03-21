package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class SignUpPage {
    @FXML
    private Button Back;

    @FXML
    private Button Submit;

    @FXML
    private TextField UserName;

    @FXML
    private TextField UserPassword;

    public static void Signup(ActionEvent event, String username, String password)
    {
        Connection connection = null;
        PreparedStatement check = null;
        PreparedStatement coachInsert = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/galib/Documents/GitHub/Sports_Management_System/SportsManagementAppForCoach/src/main/resources/com/example/sportsmanagementappforcoach/sports.db");
            //change url link from database setting.
            check = connection.prepareStatement("SELECT * FROM Coach where UserId = ?");
            check.setString(1,username);
            resultSet = check.executeQuery();
            if(resultSet.isBeforeFirst())
            {
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            }
            else
            {
                coachInsert = connection.prepareStatement("INSERT INTO Coach('UserID','Password') VALUES (?,?)");
                coachInsert.setString(1,username);
                coachInsert.setString(2,password);
                coachInsert.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            resultSet=null;
            coachInsert=null;
            check=null;
            connection=null;
        }
    }
    public void BackButtonPressed(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }
    public void SubmitButtonPressed(ActionEvent event) throws IOException {
        if(!UserName.getText().trim().isEmpty() && !UserPassword.getText().trim().isEmpty())
        {
            Signup(event,UserName.getText(), UserPassword.getText());
        }
        else
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill Information");
            alert.show();
        }
        SceneController sceneController = new SceneController();
        sceneController.SwitchToFirstPage(event);
    }

}
