package com.example.sportsmanagementappforcoach;

import PROFILE.Coach;
import DBUtil.DBResources;
import PROFILE.PlayerSkilL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPlayerPage implements Initializable {

    private Coach AddPlayerCoach;
    private String AddPlayerTeamName;
    private int AddPlayerTeamNumber;
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
                HBox hbox = (HBox) AddPlayerPageSKillVbox.getChildren().get(i);
                TextField textField = (TextField) hbox.getChildren().get(1);
                int x = Integer.parseInt(textField.getText());
                AddPlayerPagePlayerSkill.get(i).setValue(x);
            }
            dbResources.InsertSkillList(AddPlayerCoach,AddPlayerTeamName,AddPlayerPagePlayerSkill);
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
        AddPlayerPagePlayerSkill = new ArrayList<>();
        AddPlayerPagePlayerSkill = dbResources.getPlayerSkillListdb(coach,idx);
        for(int i=0;i<AddPlayerPagePlayerSkill.size();i++)
        {
            Label newlabel = new Label(AddPlayerPagePlayerSkill.get(i).getSkillName());
            TextField newtextField = new TextField("Value");
            Button tmpbutton = new Button("Check");
            System.out.println(AddPlayerPagePlayerSkill.get(i).getSkillName());
            if(AddPlayerPagePlayerSkill.get(i).getSkillValueType()==2)
            {
                newtextField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            newtextField.setText(newValue.replaceAll("[^\\d]", ""));
                            int x = Integer.parseInt(newtextField.getText());
                            if(x>100)
                            {
                                String s = newtextField.getText();
                                s = s.substring(0, s.length() - 1);
                                newtextField.setText(s);
                            }
                        }
                    }
                });
            }
            else
            {
                newtextField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            newtextField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
            }
            HBox tmphBox = new HBox(newlabel,newtextField,tmpbutton);
            AddPlayerPageSKillVbox.getChildren().add(tmphBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddPlayerPageSKillVbox = new VBox();
    }
}
