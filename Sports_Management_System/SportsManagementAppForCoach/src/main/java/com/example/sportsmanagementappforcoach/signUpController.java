package com.example.sportsmanagementappforcoach;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class signUpController implements Initializable {
    @FXML
    private Button SignUpButton;
    @FXML
    private TextField TextFieldUsername;
    @FXML
    private TextField TextFieldPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!TextFieldUsername.getText().trim().isEmpty() && !TextFieldPassword.getText().trim().isEmpty())
                {
                    DBUtils.signup(event,TextFieldUsername.getText(),TextFieldPassword.getText());
                }
                else
                {
                    System.out.println("Please Fill all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill all information");
                    alert.show();
                }
            }
        });
    }
}
