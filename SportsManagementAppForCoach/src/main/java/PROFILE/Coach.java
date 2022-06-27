package PROFILE;

import DBUtil.DBResources;

import java.sql.SQLException;
import java.util.ArrayList;

public class Coach extends Human {

    private int Id;

    ArrayList<Team>teamArrayList;

    public Coach (String emailid) throws SQLException {
        super.setRole("COACH");
        super.setEmailid(emailid);
        setCoachName();
    }

    public void setCoachName() throws SQLException {
        DBResources dbResources = new DBResources();
        super.setName(dbResources.getUserNameForCoach(this.getEmailid()));
    }

    public void setTeamArrayList() throws SQLException {
        DBResources dbResources = new DBResources();
        teamArrayList = dbResources.getTeamLists(this.getEmailid());
    }
    public void setId(int id) {
        Id = id;
    }

    public int getId()
    {
        return Id;
    }
    public int getAge() {
        return super.getAge();
    }

    public void setAge(int age) {
        super.setAge(age);
    }

    public String getImagePath() {
        return super.getImagePath();
    }

    public void setImagePath(String imagePath) {
        super.setImagePath(imagePath);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }
    public String getEmailid() {
        return super.getEmailid();
    }

    public void setEmailid(String emailid) {
        super.setEmailid(emailid);
    }

    public ArrayList<Team> getTeamArrayList() {
        return teamArrayList;
    }

    public void setTeamArrayList(ArrayList<Team> teamArrayList) {
        this.teamArrayList = teamArrayList;
    }
}
