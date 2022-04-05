package PROFILE;

import DBUtil.DBResources;

import java.sql.SQLException;
import java.util.ArrayList;

public class Team {
    String Name;
    ArrayList<Player>playerArrayList;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }
}
