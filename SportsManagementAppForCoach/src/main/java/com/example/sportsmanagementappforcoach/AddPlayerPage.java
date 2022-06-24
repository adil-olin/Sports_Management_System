package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import DBUtil.DBResources;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPlayerPage {

    private Coach AddPlayerCoach;
    private String AddPlayerTeamName;
    private int AddPlayerTeamNumber;
    private Player AddPlayerPagePlayer;
    private ArrayList<PlayerSkilL>AddPlayerPagePlayerSkill;

    @FXML
    private Button AddPlayerBackButton;

    @FXML
    private VBox AddPlayerPageSKillVbox;

    @FXML
    private TextField AddPlayerNameTextField;

    @FXML
    private Button AddPlayerSubmitButton;

    @FXML
    private Label AddPlayerPageCheckLabel;

    @FXML
    void OnAddPlayerBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,AddPlayerCoach,AddPlayerTeamNumber);
    }

    @FXML
    void OnAddPlayerSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        String name = AddPlayerNameTextField.getText();
        if(name.isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert A name");
        }
        else if(dbResources.insertPlayer(AddPlayerCoach,AddPlayerTeamNumber,name))
        {
            for(int i=0;i<AddPlayerPageSKillVbox.getChildren().size();i++)
            {
                if(AddPlayerPagePlayerSkill.get(i).getSkillValueType()==1)
                {
                    HBox hbox = (HBox) AddPlayerPageSKillVbox.getChildren().get(i);
                    TextField textField = (TextField) hbox.getChildren().get(1);
                    int x = Integer.parseInt(textField.getText());
                    AddPlayerPagePlayerSkill.get(i).setValue(x);
                }
                else if(AddPlayerPagePlayerSkill.get(i).getSkillValueType()==2)
                {
                    HBox hbox = (HBox) AddPlayerPageSKillVbox.getChildren().get(i);
                    Slider slider = (Slider) hbox.getChildren().get(1);
                    int x = (int) slider.getValue();
                    AddPlayerPagePlayerSkill.get(i).setValue(x);
                }
            }
            AddPlayerPagePlayer = new Player();
            AddPlayerPagePlayer.setPlayerTeamName(AddPlayerTeamName);
            AddPlayerPagePlayer.setSkills(AddPlayerPagePlayerSkill);
            AddPlayerPagePlayer.setName(name);
            AddPlayerPagePlayer.setEmailid(AddPlayerCoach.getEmailid());

            dbResources.InsertPlayerSkill(AddPlayerPagePlayer);
            AddPlayerCoach.getTeamArrayList().get(AddPlayerTeamNumber).setPlayerArrayList(dbResources.getPlayerLists(AddPlayerCoach.getEmailid(),AddPlayerCoach.getTeamArrayList().get(AddPlayerTeamNumber).getName()));
            SceneController sceneController = new SceneController();
            sceneController.SwitchToPlayerList(event,AddPlayerCoach,AddPlayerTeamNumber);
        }
        else
        {
            AddPlayerPageCheckLabel.setText("This player Alredy exists on this team");
        }
    }


    public void AddPlayerSetData(Coach coach, int idx) throws SQLException {
        DBResources dbResources = new DBResources();
        AddPlayerCoach = coach;
        AddPlayerTeamNumber = idx;
        AddPlayerTeamName = AddPlayerCoach.getTeamArrayList().get(AddPlayerTeamNumber).getName();
        AddPlayerPagePlayerSkill = new ArrayList<>();
        AddPlayerPagePlayerSkill = dbResources.getPlayerSkillListdb(coach,idx);
        AddPlayerPageSKillVbox.setSpacing(5);
        for(int i=0;i<AddPlayerPagePlayerSkill.size();i++) {
            Label newlabel = new Label(AddPlayerPagePlayerSkill.get(i).getSkillName());
            newlabel.setPrefWidth(70);
            Slider newslider;
            if (AddPlayerPagePlayerSkill.get(i).getSkillValueType() == 1) {
                TextField newtextField = new TextField();
                newtextField.setPrefWidth(300);
                if (AddPlayerPagePlayerSkill.get(i).getSkillValueType() == 2) {
                    newtextField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                newtextField.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                } else {
                    newtextField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                newtextField.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                }
                HBox tmphBox = new HBox(newlabel, newtextField);
                AddPlayerPageSKillVbox.getChildren().add(tmphBox);
            }
            else if (AddPlayerPagePlayerSkill.get(i).getSkillValueType() == 2) {
                Label valuelabel = new Label();
                newslider = new Slider(0, 100, 50);
                valuelabel.textProperty().bind(
                        Bindings.format(
                                "%.0f",
                                newslider.valueProperty()
                        )
                );
                newslider.setBlockIncrement(1);
                newslider.setPrefWidth(300);
                newslider.setMajorTickUnit(10.0);
                newslider.setMinorTickCount(1);
                newslider.setShowTickMarks(true);
                newslider.setShowTickLabels(true);
                HBox tmphBox = new HBox(newlabel, newslider,valuelabel);
                AddPlayerPageSKillVbox.getChildren().add(tmphBox);
            }
        }
    }
}
