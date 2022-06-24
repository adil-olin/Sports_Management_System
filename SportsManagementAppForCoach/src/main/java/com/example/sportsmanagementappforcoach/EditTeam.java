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
    private ArrayList<PlayerSkilL> EditTeamPlayerSkill;
    private DBResources EditTeamdbresources;
    private int idx;
    @FXML
    private Button EditTeamAddSkillButton;

    @FXML
    private Button EditTeamBackButton;

    @FXML
    private Button EditTeamConfirmButton;

    @FXML
    private Label EditTeamInsertNameLabel;

    @FXML
    private VBox EditTeamSkillListVbox;

    @FXML
    private Label EditTeamSkillNameErrorLabel;

    @FXML
    private TextField EditTeamSkillNameTextField;

    @FXML
    private ChoiceBox<String> EditTeamSkillTypeChoiceBox;

    @FXML
    private TextField EditTeamTeamNameTextField;

    List<String> choicelist;
    Button tempButton = new Button("Delete");


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
                if(EditTeamPlayerSkill.get(i).getSkillName().equals(EditTeamSkillNameTextField.getText()))
                {
                    EditTeamSkillNameTextField.setText("");
                    EditTeamSkillNameErrorLabel.setText("This Skill Already Exists, please enter another skill");
                    return;
                }
            }
            TextField textField = new TextField(EditTeamSkillNameTextField.getText());

            tempButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
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
            Button tempButton = new Button("Delete");
            HBox tmphbox = new HBox(textField,tempButton);
            tmphbox.setSpacing(5);
            EditTeamSkillListVbox.getChildren().add(tmphbox);
            PlayerSkilL tempplayerskill = new PlayerSkilL(EditTeamSkillNameTextField.getText(),0);
            if(EditTeamSkillTypeChoiceBox.getValue().equals(choicelist.get(0)))
            {
                tempplayerskill.setSkillValueType(1);
            }
            else
            {
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
    void EditTeamSetCoach(Coach coach, int TeamNumber) throws SQLException {
        DBResources dbResources = new DBResources();
        EditTeamCoach = coach;
        EditTeamPlayerSkill=dbResources.getPlayerSkillListdb(EditTeamCoach,TeamNumber);
        team = EditTeamCoach.getTeamArrayList().get(TeamNumber);
        EditTeamTeamNameTextField.setText(team.getName());
        for (int i = 0 ; i <EditTeamPlayerSkill.size();i++)
        {
            Button tempButton = new Button("Delete");
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
