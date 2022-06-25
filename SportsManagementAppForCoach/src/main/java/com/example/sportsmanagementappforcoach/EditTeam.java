package com.example.sportsmanagementappforcoach;

import DBUtil.DBResources;
import PROFILE.Coach;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import PROFILE.Team;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditTeam implements Initializable {

    AddTeamModel addTeamModel = new AddTeamModel();

    private Coach EditTeamCoach;
    private Team team;
    private int TeamNumber;
    private ArrayList<PlayerSkilL> EditTeamPlayerSkill;
    private ArrayList<Player> EditTeamPlayer;
    @FXML
    private Button EditTeamAddSkillButton;

    @FXML
    private Label EditTeamPlayerListLabel;

    @FXML
    private Label EditTeamSkillsLabel;

    @FXML
    private Button EditTeamBackButton;

    @FXML
    private Button EditTeamConfirmButton;

    @FXML
    private Label EditTeamInsertNameLabel;

    @FXML
    private VBox EditTeamSkillListVbox;

    @FXML
    private VBox EditTeamPlayerListVbox;

    @FXML
    private Label EditTeamSkillNameErrorLabel;

    @FXML
    private TextField EditTeamSkillNameTextField;

    @FXML
    private ChoiceBox<String> EditTeamSkillTypeChoiceBox;


    @FXML
    private TextField EditTeamTeamNameTextField;

    List<String> choicelist;
    private Button tempButton = new Button("Delete");
    private ArrayList<String> deletedskills = new ArrayList<String>();
    private ArrayList<Player> deletedplayer = new ArrayList<Player>();


    @FXML
    void OnEditTeamAddSkillButtonClick(ActionEvent event) {
        if(EditTeamSkillNameTextField.getText().isEmpty())
        {
            EditTeamSkillNameErrorLabel.setText("Please insert a name for the skill");
        }
        else
        {
            for (int i=0;i<EditTeamPlayerSkill.size();i++)
            {
                if(EditTeamPlayerSkill.get(i).equals(EditTeamSkillNameTextField.getText()))
                {
                    EditTeamSkillNameTextField.setText("");
                    EditTeamSkillNameErrorLabel.setText("This Skill Already Exists, please enter another skill");
                    return;
                }
            }
            TextField textField = new TextField(EditTeamSkillNameTextField.getText());
            Button tempButton = new Button("Delete");

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    System.out.println("yes");
                    for(int i=0;i<EditTeamSkillListVbox.getChildren().size();i++)
                    {
                        HBox tempHbox = (HBox) EditTeamSkillListVbox.getChildren().get(i);
                        Button tempbutton = (Button) tempHbox.getChildren().get(1);
                        if(tempButton.equals(tempbutton))
                        {
                            EditTeamSkillListVbox.getChildren().remove(tempHbox);
                            EditTeamPlayerSkill.remove(i);
                            break;
                        }
                    }
                }
            });

            HBox tmphbox = new HBox(textField,tempButton);
            tmphbox.setSpacing(5);
            EditTeamSkillListVbox.getChildren().add(tmphbox);
            PlayerSkilL tempplayerskill = new PlayerSkilL(EditTeamSkillNameTextField.getText(),0);
            if(EditTeamSkillTypeChoiceBox.getValue().equals(choicelist.get(0)))
            {
                System.out.println("The choice is "+EditTeamSkillTypeChoiceBox.getValue());
                tempplayerskill.setSkillValueType(1);
            }
            else
            {
                System.out.println("The choice is "+EditTeamSkillTypeChoiceBox.getValue());
                tempplayerskill.setSkillValueType(2);
            }
            EditTeamSkillNameTextField.setText("");
            EditTeamSkillTypeChoiceBox.setValue("Value");
            EditTeamPlayerSkill.add(tempplayerskill);
        }
    }


    @FXML
    void OnEditTeamBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToHomePage(event,EditTeamCoach);
    }

    @FXML
    void OnEditTeamConfirmButtonClick(ActionEvent event) throws SQLException, IOException {

        for (int i = 0; i <deletedskills.size();i++)
        {
            DBResources dbResources = new DBResources();
            dbResources.DeleteSkill(EditTeamCoach,EditTeamCoach.getTeamArrayList().get(TeamNumber).getName(),deletedskills.get(i));
        }
        for(int i = 0; i < deletedplayer.size();i++)
        {
            DBResources dbResources = new DBResources();
            dbResources.DeletePlayer(deletedplayer.get(i));
        }
        deletedskills.removeAll(deletedskills);
        deletedplayer.removeAll(deletedplayer);
        if(EditTeamTeamNameTextField.getText().trim().isEmpty())
        {
            EditTeamInsertNameLabel.setText("Put a name to the text field");
        }
        else
        {
            if(this.addTeamModel.AddTeamModelAddTeam(EditTeamTeamNameTextField.getText(),EditTeamCoach)||team.getName().equals(EditTeamTeamNameTextField.getText()))
            {
                SceneController sceneController = new SceneController();
                DBResources dbResources = new DBResources();
                dbResources.InsertUpdateSkillList(EditTeamCoach,EditTeamTeamNameTextField.getText(),EditTeamPlayerSkill);
                sceneController.SwitchToHomePage(event,EditTeamCoach);
            }
            else
            {
                EditTeamInsertNameLabel.setText("This team name already exists");
            }
        }
    }
    void EditTeamSetCoach(Coach coach, int teamNumber) throws SQLException {
        DBResources dbResources = new DBResources();
        EditTeamCoach = coach;
        TeamNumber = teamNumber;
        EditTeamPlayerSkill=dbResources.getPlayerSkillListdb(EditTeamCoach,TeamNumber);
        EditTeamPlayer=dbResources.getPlayerLists(coach.getEmailid(),coach.getTeamArrayList().get(TeamNumber).getName());
        team = EditTeamCoach.getTeamArrayList().get(TeamNumber);
        EditTeamTeamNameTextField.setText(team.getName());
        for(int i = 0; i < EditTeamPlayer.size();i++)
        {
            Button tempButton = new Button("Delete");
            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    for(int i=0;i<EditTeamPlayerListVbox.getChildren().size();i++)
                    {
                        HBox tempHbox = (HBox) EditTeamPlayerListVbox.getChildren().get(i);
                        Button tempbutton = (Button) tempHbox.getChildren().get(1);
                        if(tempButton.equals(tempbutton))
                        {
                            //System.out.println();
                            deletedplayer.add(EditTeamPlayer.get(i));
                            EditTeamPlayerListVbox.getChildren().remove(tempHbox);
                            EditTeamPlayer.remove(i);
                            break;
                        }
                    }
                }
            });
            HBox editplayer = new HBox(new TextField(coach.getTeamArrayList().get(TeamNumber).getPlayerArrayList().get(i).getName()),tempButton);
            EditTeamPlayerListVbox.getChildren().add(editplayer);

        }
        for (int i = 0 ; i <EditTeamPlayerSkill.size();i++)
        {
            Button tempButton = new Button("Delete");
            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    for(int i=0;i<EditTeamSkillListVbox.getChildren().size();i++)
                    {
                        HBox tempHbox = (HBox) EditTeamSkillListVbox.getChildren().get(i);
                        Button tempbutton = (Button) tempHbox.getChildren().get(1);
                        if(tempButton.equals(tempbutton))
                        {
                            deletedskills.add(EditTeamPlayerSkill.get(i).getSkillName());
                            EditTeamSkillListVbox.getChildren().remove(tempHbox);
                            EditTeamPlayerSkill.remove(i);
                            break;
                        }
                    }
                }
            });
            HBox edithbox = new HBox(new TextField(EditTeamPlayerSkill.get(i).getSkillName()),tempButton);
            EditTeamSkillListVbox.getChildren().add(edithbox);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicelist = new ArrayList<String>();
        choicelist.add("Value");
        choicelist.add("Percentage");
        EditTeamSkillTypeChoiceBox.getItems().addAll(choicelist);
        EditTeamSkillTypeChoiceBox.setValue("Value");
        EditTeamSkillListVbox.setSpacing(5);
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
