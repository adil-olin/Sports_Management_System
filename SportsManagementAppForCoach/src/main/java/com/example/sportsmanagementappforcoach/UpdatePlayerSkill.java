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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdatePlayerSkill implements Initializable {

    private Coach UpdatePlayerSkillCoach;
    private String UpdatePlayerSkillTeamName;
    private int UpdatePlayerSkillTeamNumber;
    private Player UpdatePlayerSkillPagePlayer;
    private ArrayList<PlayerSkilL>UpdatePlayerSkillPagePlayerSkill;
    @FXML
    private Button UpdatePlayerSKillChoosePhotoButton;

    @FXML
    private AnchorPane UpdatePlayerSKillMainAnchorPane;

    @FXML
    private TextField UpdatePlayerSKillRoleTextFIeld;

    @FXML
    private TextField UpdatePlayerSkillAgeTextField;

    @FXML
    private Button UpdatePlayerSkillBackButton;

    @FXML
    private ImageView UpdatePlayerSkillImageView;

    @FXML
    private Label UpdatePlayerSkillNameLabel;

    @FXML
    private Label UpdatePlayerSkillPageCheckLabel;

    @FXML
    private VBox UpdatePlayerSkillPageSKillVbox;

    @FXML
    private Button UpdatePlayerSkillSubmitButton;

    @FXML
    void OnUpdatePlayerSkillChoosePhotoButtonClicked(ActionEvent event) {
        FileChooser open= new FileChooser();
        Stage stage=(Stage) UpdatePlayerSKillMainAnchorPane.getScene().getWindow();
        File file=open.showOpenDialog(stage);
        if(file!=null)
        {
            String img_path=file.getAbsolutePath();

            img_path=img_path.replace("\\","\\\\");

            Image image= new Image(file.toURI().toString(),120,120,false,true);
            UpdatePlayerSkillImageView.setImage(image);
            UpdatePlayerSkillPagePlayer.setImagePath(img_path);
            System.out.println(UpdatePlayerSkillPagePlayer.getImagePath());
        }
        else
        {
            System.out.println("pic is missing");
        }
    }

    private int MakeInt(String num)
    {
        int ans = 0 ;
        for(int i=0;i<num.length();i++)
        {
            ans*=10;
            ans+=num.charAt(i);
            ans-='0';
        }
        return ans;
    }

    @FXML
    void OnUpdatePlayerSkillBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,UpdatePlayerSkillCoach,UpdatePlayerSkillTeamNumber);
    }

    @FXML
    void OnUpdatePlayerSkillSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        UpdatePlayerSkillPagePlayer.setRole(UpdatePlayerSKillRoleTextFIeld.getText());
        UpdatePlayerSkillPagePlayer.setAge(MakeInt(UpdatePlayerSkillAgeTextField.getText()));
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
        UpdatePlayerSkillPagePlayer.setSkills(UpdatePlayerSkillPagePlayerSkill);

        dbResources.InsertPlayerSkill(UpdatePlayerSkillPagePlayer);
        dbResources.playerInfoUpdate(UpdatePlayerSkillPagePlayer);
        UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).setPlayerArrayList(dbResources.getPlayerLists(UpdatePlayerSkillCoach.getEmailid(),UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).getName()));
        SceneController sceneController = new SceneController();
        sceneController.SwitchtoPlayerDetailsPage(event,UpdatePlayerSkillCoach,UpdatePlayerSkillTeamNumber,UpdatePlayerSkillPagePlayer);

    }
    public void UpdatePlayerDataset(Coach coach, int idx, Player player) throws SQLException, MalformedURLException {
        UpdatePlayerSkillNameLabel.setText(player.getName());
        DBResources dbResources = new DBResources();
        UpdatePlayerSkillNameLabel.setText(player.getName());
        UpdatePlayerSkillCoach = coach;
        UpdatePlayerSkillTeamNumber = idx;
        UpdatePlayerSkillTeamName = UpdatePlayerSkillCoach.getTeamArrayList().get(UpdatePlayerSkillTeamNumber).getName();
        UpdatePlayerSkillPagePlayerSkill = new ArrayList<>();
        UpdatePlayerSkillPagePlayerSkill = dbResources.getPlayerSkillListdb(coach,idx);
        UpdatePlayerSkillPageSKillVbox.setSpacing(5);
        UpdatePlayerSKillRoleTextFIeld.setText(player.getRole());
        UpdatePlayerSkillAgeTextField.setText(toString(player.getAge()));
        UpdatePlayerSkillPagePlayer = player;

        Path imageFile = Paths.get(player.getImagePath());
        UpdatePlayerSkillImageView.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdatePlayerSkillAgeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    UpdatePlayerSkillAgeTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
