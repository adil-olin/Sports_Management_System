package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBResources;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayerList {
    Coach PlayerListcoach;
    DBResources PlayerListDBResources = new DBResources();
    String PlayerListTeamName;

    void PlayerListSetData(String emailid,String teamName) throws SQLException {
        PlayerListcoach = PlayerListDBResources.getCoachData(emailid);
    }

}
