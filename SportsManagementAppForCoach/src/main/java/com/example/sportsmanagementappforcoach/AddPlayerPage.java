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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPlayerPage implements Initializable{

    private Coach AddPlayerCoach;
    private String AddPlayerTeamName;
    private int AddPlayerTeamNumber;
    private Player AddPlayerPagePlayer;
    private ArrayList<PlayerSkilL>AddPlayerPagePlayerSkill;

    @FXML
    private Button AddPlayerBackButton;

    @FXML
    private Label AddPlayerPageInsertPhotoLabel;

    @FXML
    private TextField AddPlayerNameTextField;

    @FXML
    private TextField AddPlayerPageAgeTextField;

    @FXML
    private Label AddPlayerPageCheckLabel;

    @FXML
    private Button AddPlayerPageChoosePhotoButton;

    @FXML
    private ImageView AddPlayerPageImageView;

    @FXML
    private AnchorPane AddPlayerPageMainAnchorPane;

    @FXML
    private TextField AddPlayerPageRoleTextfield;

    @FXML
    private VBox AddPlayerPageSKillVbox;

    @FXML
    private Button AddPlayerSubmitButton;


    @FXML
    void OnAddPlayerPageChoosePhotoButtonClicked(ActionEvent event) {
        FileChooser open= new FileChooser();
        Stage stage=(Stage) AddPlayerPageMainAnchorPane.getScene().getWindow();
        File file=open.showOpenDialog(stage);
        if(file!=null)
        {
            String img_path=file.getAbsolutePath();

            img_path=img_path.replace("\\","\\\\");
            
            Image image= new Image(file.toURI().toString(),120,120,false,true);
            AddPlayerPageImageView.setImage(image);
            AddPlayerPagePlayer.setImagePath(img_path);
            AddPlayerPageInsertPhotoLabel.setText("");
        }
        else
        {
            System.out.println("pic is missing");
        }
    }

    @FXML
    void OnAddPlayerBackButtonClick(ActionEvent event) throws SQLException, IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToPlayerList(event,AddPlayerCoach,AddPlayerTeamNumber);
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
    void OnAddPlayerSubmitButtonClick(ActionEvent event) throws SQLException, IOException {
        DBResources dbResources = new DBResources();
        String name = AddPlayerNameTextField.getText();
        AddPlayerPagePlayer.setPlayerTeamName(AddPlayerTeamName);
        AddPlayerPagePlayer.setSkills(AddPlayerPagePlayerSkill);
        AddPlayerPagePlayer.setName(name);
        AddPlayerPagePlayer.setEmailid(AddPlayerCoach.getEmailid());
        AddPlayerPagePlayer.setAge(MakeInt(AddPlayerPageAgeTextField.getText()));
        if(!AddPlayerPageRoleTextfield.getText().isEmpty())
        {
            AddPlayerPagePlayer.setRole(AddPlayerPageRoleTextfield.getText());
        }
        if(name.isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert All Information");
        }
        else if(AddPlayerPageImageView.getImage().isError())
        {
            AddPlayerPageCheckLabel.setText("Please Insert All Information");
        }
        else if(AddPlayerPageAgeTextField.getText().isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert All Information");
        }
        else if(AddPlayerPageRoleTextfield.getText().isEmpty())
        {
            AddPlayerPageCheckLabel.setText("Please Insert All Information");
        }
        else if(dbResources.insertPlayer(AddPlayerCoach,AddPlayerTeamNumber,AddPlayerPagePlayer))
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddPlayerPagePlayer = new Player();
        AddPlayerPageAgeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    AddPlayerPageAgeTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
