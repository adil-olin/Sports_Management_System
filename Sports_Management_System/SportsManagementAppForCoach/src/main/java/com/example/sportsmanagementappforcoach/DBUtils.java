package com.example.sportsmanagementappforcoach;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;

public class DBUtils {
    public static void change_scene(ActionEvent event, String fxmlFile,String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedincontroller = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void signup(ActionEvent event, String username, String password)
    {

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/galib/IdeaProjects/Sports_Management_System/SportsManagementAppForCoach/src/main/resources/com/example/sportsmanagementappforcoach/sportsmanagement.db");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM users where username =?");
            psCheckUserExist.setString(1,username);
            resultSet = psCheckUserExist.executeQuery();
            if(resultSet.isBeforeFirst())
            {
                resultSet=null;
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            }
            else
            {
                psInsert = connection.prepareStatement("INSERT INTO users ('username','password') VALUES (?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.execute();
                System.out.println("yes"+password);
                change_scene(event,"Loggedin.fxml","Welcome",username);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if(connection!=null)
            {
                try{
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExist!=null)
            {
                try{
                    psCheckUserExist.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(resultSet!=null)
            {
                try{
                    resultSet.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psInsert!=null)
            {
                try{
                    psInsert.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }


        }
    }
    public static void logInUser(ActionEvent event, String username, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/galib/IdeaProjects/Sports_Management_System/SportsManagementAppForCoach/src/main/resources/com/example/sportsmanagementappforcoach/sportsmanagement.db");
           preparedStatement = connection.prepareStatement("SELECT * FROM users where username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
            {
                resultSet=null;
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No user");
                alert.show();
            }
            else
            {
                while (resultSet.next())
                {
                    String retrivedpass = resultSet.getString("password");
                    if(retrivedpass.equals(password))
                    {
                        change_scene(event,"Loggedin.fxml","Welcome",username);
                    }
                    else
                    {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Wrong Password");
                        alert.show();
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if(connection!=null)
            {
                try{
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null)
            {
                try{
                    preparedStatement.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(resultSet!=null)
            {
                try{
                    resultSet.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
