package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;

import DBUtil.DBResources;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AddTeam implements Initializable {

    AddTeamModel addTeamModel = new AddTeamModel();

    private Coach AddTeamCoach;

    private ArrayList<PlayerSkilL>AddTeamPlayerSkill;

    private DBResources AddTeamdbresources;

    @FXML
    private Button AddTeamConfirmButton;

    @FXML
    private TextField AddTeamTeamNameTextField;

    @FXML
    private VBox AddTeamSkillListVbox;

    @FXML
    private Button AddTeamAddSkillButton;

    @FXML
    private Label AddTeamInsertNameLabel;

    @FXML
    private TextField AddTeamSkillNameTextField;

    @FXML
    private Label AddTeamSkillNameErrorLabel;

    @FXML
    private ChoiceBox<String> AddTeamSkillTypeChoiceBox;

    List<String> choicelist;

    @FXML
    void OnAddTeamAddSkillButtonClick(ActionEvent event) {
        if(AddTeamSkillNameTextField.getText().isEmpty())
        {
            AddTeamSkillNameErrorLabel.setText("Please insert a name for the skill");
        }
        else
        {
            for (int i=0;i<AddTeamPlayerSkill.size();i++)
            {
                if(AddTeamPlayerSkill.get(i).equals(AddTeamSkillNameTextField.getText()))
                {
                    AddTeamSkillNameTextField.setText("");
                    AddTeamSkillNameErrorLabel.setText("This Skill Already Exists, please enter another skill");
                    return;
                }
            }
            TextField textField = new TextField(AddTeamSkillNameTextField.getText());
            Button tempButton = new Button("Delete");

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    for(int i=0;i<AddTeamSkillListVbox.getChildren().size();i++)
                    {
                        HBox tempHbox = (HBox) AddTeamSkillListVbox.getChildren().get(i);
                        Button tempbutton = (Button) tempHbox.getChildren().get(1);
                        if(tempButton.equals(tempbutton))
                        {
                            AddTeamSkillListVbox.getChildren().remove(tempHbox);
                            AddTeamPlayerSkill.remove(i);
                            break;
                        }
                    }
                }
            });

            HBox tmphbox = new HBox(textField,tempButton);
            AddTeamSkillListVbox.getChildren().add(tmphbox);
            PlayerSkilL tempplayerskill = new PlayerSkilL(AddTeamSkillNameTextField.getText(),0);
            if(AddTeamSkillTypeChoiceBox.getValue().equals(choicelist.get(0)))
            {
                tempplayerskill.setSkillValueType(1);
            }
            else
            {
                tempplayerskill.setSkillValueType(2);
            }
            AddTeamSkillNameTextField.setText("");
            AddTeamSkillTypeChoiceBox.setValue(choicelist.get(0));
            AddTeamPlayerSkill.add(tempplayerskill);
        }
    }

    @FXML
    void OnAddTeamBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,AddTeamCoach);
    }
    @FXML
    void OnAddTeamConfirmButtonClick(ActionEvent event) throws SQLException, IOException {
        if(AddTeamTeamNameTextField.getText().trim().isEmpty())
        {
            AddTeamInsertNameLabel.setText("Put a name to the text field");
        }
        else
        {
            if(this.addTeamModel.AddTeamModelAddTeam(AddTeamTeamNameTextField.getText(),AddTeamCoach))
            {
                SceneController sceneController = new SceneController();
                DBResources dbResources = new DBResources();
                dbResources.InsertSkillList(AddTeamCoach,AddTeamTeamNameTextField.getText(),AddTeamPlayerSkill);
                sceneController.SwitchToHomePage(event,AddTeamCoach);
            }
            else
            {
                AddTeamInsertNameLabel.setText("This team name already exists");
            }
        }
    }
    void AddTeamSetCoach(Coach coach) throws SQLException {
        DBResources dbResources = new DBResources();
        AddTeamCoach = coach;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddTeamPlayerSkill = new ArrayList<>();
        choicelist = new ArrayList<String>();
        choicelist.add("Value");
        choicelist.add("Percantage");
        AddTeamSkillTypeChoiceBox.getItems().addAll(choicelist);
        AddTeamSkillTypeChoiceBox.setValue("Value");
        System.out.println("The value is : "+ AddTeamSkillTypeChoiceBox.getItems().get(0));
        if(this.addTeamModel.isDataBaseConnected())
        {
            System.out.println("DataBase is connected to AddTeam Class");
        }
        else
        {
            System.out.println("DataBase is not connected to AddTeam Class");
        }
    }
}