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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdatePlayerSkill {

    private Coach UpdatePlayerSkillCoach;
    private String UpdatePlayerSkillTeamName;
    private int UpdatePlayerSkillTeamNumber;
    private Player UpdatePlayerSkillPagePlayer;
    private ArrayList<PlayerSkilL>UpdatePlayerSkillPagePlayerSkill;

    @FXML
    private Button UpdatePlayerSkillBackButton;

    @FXML
    private VBox UpdatePlayerSkillPageSKillVbox;

    @FXML
    private TextField UpdatePlayerSkillNameTextField;

    @FXML
    private Button UpdatePlayerSkillSubmitButton;

    @FXML
    private Label UpdatePlayerSkillPageCheckLabel;

    @FXML
    void OnUpdatePlayerSkillBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,UpdatePlayerSkillCoach,UpdatePlayerSkillTeamNumber);
    }

    @FXML
    void OnUpdatePlayerSkillSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        String name = UpdatePlayerSkillNameTextField.getText();
        for(int i=0;i<UpdatePlayerSkillPageSKillVbox.getChildren().size();i++)
        {
            if(UpdatePlayerSkillPagePlayerSkill.get(i).getSkillValueType()==1)
            {
                HBox hbox = (HBox) UpdatePlayerSkillPageSKillVbox.getChildren().get(i);
                TextField textField = (TextField) hbox.getChildren().get(1);
                int x = Integer.parseInt(textField.getText());
                UpdatePlayerSkillPagePlayerSkill.get(i).setValue(x);
            }
            else if(UpdatePlayerSkillPagePlayerSkill.get(i).getSkillValueType()==2)
            {
                HBox hbox = (HBox) UpdatePlayerSkillPageSKillVbox.getChildren().get(i);
                Slider slider = (Slider) hbox.getChildren().get(1);
                int x = (int) slider.getValue();
                UpdatePlayerSkillPagePlayerSkill.get(i).setValue(x);
            }
        }
        UpdatePlayerSkillPagePlayer = new Player();
        UpdatePlayerSkillPagePlayer.setPlayerTeamName(UpdatePlayerSkillTeamName);
        UpdatePlayerSkillPagePlayer.setSkills(UpdatePlayerSkillPagePlayerSkill);
        UpdatePlayerSkillPagePlayer.setName(name);
        UpdatePlayerSkillPagePlayer.setEmailid(UpdatePlayerSkillCoach.getEmailid());

        dbResources.InsertPlayerSkill(UpdatePlayerSkillPagePlayer);
        UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).setPlayerArrayList(dbResources.getPlayerLists(UpdatePlayerSkillCoach.getEmailid(),UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).getName()));
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,UpdatePlayerSkillCoach,UpdatePlayerSkillTeamNumber);

    }
    public void UpdatePlayerDataset(Coach coach, int idx, Player player) throws SQLException {
        DBResources dbResources = new DBResources();
        UpdatePlayerSkillNameTextField.setText(player.getName());
        UpdatePlayerSkillCoach = coach;
        UpdatePlayerSkillTeamNumber = idx;
        UpdatePlayerSkillTeamName = UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).getName();
        UpdatePlayerSkillPagePlayerSkill = new ArrayList<>();
        UpdatePlayerSkillPagePlayerSkill = dbResources.getPlayerSkillListdb(coach,idx);
        UpdatePlayerSkillPageSKillVbox.setSpacing(5);
        for(int i=0;i<UpdatePlayerSkillPagePlayerSkill.size();i++) {
            Label newlabel = new Label(UpdatePlayerSkillPagePlayerSkill.get(i).getSkillName());
            newlabel.setPrefWidth(70);
            Slider newslider;
            PlayerSkilL skill = UpdatePlayerSkillPagePlayerSkill.get(i);
            for(int j = 0; j < player.getSkills().size();j++)
            {
                if(skill.getSkillName().equals(player.getSkills().get(j).getSkillName()))
                {
                    skill=player.getSkills().get(j);
                    break;
                }
            }
            if (UpdatePlayerSkillPagePlayerSkill.get(i).getSkillValueType() == 1) {
                TextField newtextField = new TextField();
                newtextField.setPrefWidth(300);
                newtextField.setAlignment(Pos.CENTER);
                newtextField.setText(String.valueOf((int)skill.getValue()));
                if (UpdatePlayerSkillPagePlayerSkill.get(i).getSkillValueType() == 2) {
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
                UpdatePlayerSkillPageSKillVbox.getChildren().add(tmphBox);
            }
            else if (UpdatePlayerSkillPagePlayerSkill.get(i).getSkillValueType() == 2) {
                Label valuelabel = new Label();
                newslider = new Slider(0, 100, 50);
                newslider.setValue(skill.getValue());
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
                UpdatePlayerSkillPageSKillVbox.getChildren().add(tmphBox);
            }
        }
    }

    private String toString(double value) {
            return String.valueOf((int)value);
    }
}
