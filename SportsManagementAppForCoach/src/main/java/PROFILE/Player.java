package PROFILE;

import java.util.ArrayList;

public class Player extends Human{

    protected ArrayList<PlayerSkilL>Skills;
    private int Height;
    private String playerTeamName;

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getPlayerTeamName() {
        return playerTeamName;
    }

    public void setPlayerTeamName(String playerTeamName) {
        this.playerTeamName = playerTeamName;
    }

    public Player()
    {
        super.setRole("PLAYER");
    }
    public Player(String role)
    {
        super.setRole(role);
    }
    public ArrayList<PlayerSkilL> getSkills() {
        return Skills;
    }

    public void setSkills(ArrayList<PlayerSkilL> skills) {
        Skills = skills;
    }
}
