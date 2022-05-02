package DBUtil;

import PROFILE.Coach;
import PROFILE.Player;
import PROFILE.PlayerSkilL;
import PROFILE.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBResources {

    Connection connection;
    public DBResources()
    {
        this.connection = DBConnection.getConnection();

        if(this.connection == null)
        {
            System.exit(1);
        }
        else{
            System.out.println("Connected");
        }
    }
    public ResultSet getDATA(String x,String y,String val) {
        PreparedStatement pr;
        ResultSet rs;
        String sql = "SELECT "+x+" From LoginDB where "+y+" = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,val);
            rs = pr.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
    public ResultSet getDATA(String x,String y,int val) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT "+x+" From LoginDB where "+y+" = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1,val);
            rs = pr.executeQuery();
            return rs;

        } catch (SQLException e) {
            return rs;
        }
    }
    public Coach getCoachData(String emailid) throws SQLException {

        ResultSet resultSet;
        resultSet = getDATA("*" , "Emailid" , emailid);
        Coach coach = new Coach(emailid);
        coach.setId(resultSet.getInt("Id"));
        coach.setName(resultSet.getString("Username"));
        coach.setEmailid(resultSet.getString("Emailid"));
        resultSet.close();
        return coach;
    }

    public ArrayList<Team> getTeamLists(Coach coach) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * From TeamForCoach where Emailid = ?";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        pr.setString(1,coach.getEmailid());
        rs = pr.executeQuery();
        ArrayList<Team> arr = new ArrayList<Team>();
        while (rs.next())
        {
            Team tempteam = new Team();
            tempteam.setName(rs.getString("TeamName"));
            arr.add(tempteam);
        }
        rs.close();
        for (int i=0;i<arr.size();i++)
        {
            arr.get(i).setPlayerArrayList(this.getPlayerLists(coach.getEmailid(),arr.get(i).getName()));
        }
        return  arr;
    }
    public ArrayList<Team> getTeamLists(String emailid) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * From TeamForCoach where Emailid = ?";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        pr.setString(1,emailid);
        rs = pr.executeQuery();
        ArrayList<Team> arr = new ArrayList<Team>();
        while (rs.next())
        {
            Team tempteam = new Team();
            tempteam.setName(rs.getString("TeamName"));
            arr.add(tempteam);
        }
        rs.close();
        for (int i=0;i<arr.size();i++)
        {
            arr.get(i).setPlayerArrayList(this.getPlayerLists(emailid,arr.get(i).getName()));
        }
        return  arr;
    }
    public ArrayList<PlayerSkilL>getSkillList(String emailid,String teamname, String playername) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * From PlayerDetails where Emailid = " + "\"" +emailid + "\"" +" and TeamName = "+"\""+teamname+"\""+" and PlayerName = "+"\""+playername+"\"";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        rs = pr.executeQuery();
        ArrayList<PlayerSkilL>temparr = new ArrayList<PlayerSkilL>();
        while (rs.next())
        {
            PlayerSkilL playerSkilL = new PlayerSkilL();
            playerSkilL.setSkillValueType(rs.getInt("SkillValueType"));
            playerSkilL.setSkillName(rs.getString("SkillName"));
            playerSkilL.setValue(rs.getInt("SkillValue"));
            temparr.add(playerSkilL);
        }
        rs.close();
        return temparr;
    }
    public ArrayList<Player>getPlayerLists(String emailid,String teamname) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * From PlayerInfo where Emailid = ? and TeamName = ?";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        pr.setString(1,emailid);
        pr.setString(2,teamname);
        rs = pr.executeQuery();
        ArrayList<Player> arr = new ArrayList<Player>();
        while (rs.next())
        {
            Player tempplayer = new Player(rs.getString("Role"));
            tempplayer.setName(rs.getString("PlayerName"));
            tempplayer.setAge(rs.getInt("Age"));
            tempplayer.setHeight(rs.getInt("Height"));
            tempplayer.setImagePath(rs.getString("PicPath"));
            tempplayer.setEmailid(rs.getString("Emailid"));
            tempplayer.setPlayerTeamName(teamname);
            System.out.println(tempplayer.getName());
            arr.add(tempplayer);
        }
        rs.close();
        for(int i=0;i<arr.size();i++)
        {
            ArrayList<PlayerSkilL>tempskill = new ArrayList<PlayerSkilL>();
            tempskill = this.getSkillList(emailid,teamname,arr.get(i).getName());
            arr.get(i).setSkills(tempskill);
        }
        return  arr;
    }
    public Boolean insertPlayer(Coach coach , int teamnumber, String playername) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PlayerInfo where Emailid = ? and TeamName = ? and PlayerName = ?";
        try
        {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,coach.getEmailid());
            pr.setString(2,coach.getTeamArrayList().get(teamnumber).getName());
            pr.setString(3,playername);
            rs = pr.executeQuery();
            if(rs.next())
            {
                return false;
            }
            else
            {
                String sqlInsert = "INSERT INTO PlayerInfo (Emailid , TeamName , PlayerName) VALUES (?, ?, ?)";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setString(1,coach.getEmailid());
                stmt.setString(2,coach.getTeamArrayList().get(teamnumber).getName());
                stmt.setString(3,playername);
                stmt.executeUpdate();
                stmt.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }
        return false;
    }

    public String getUserNameForCoach(String mailid) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * From LoginDB where Emailid = ?";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        pr.setString(1,mailid);
        rs = pr.executeQuery();
        String name = rs.getString("Username");
        rs.close();
        return  name;
    }
    public ArrayList<PlayerSkilL> getPlayerSkillListdb(Coach coach,int idx) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * From SkillsForTeam where Emailid = ? and TeamName = ?";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        pr.setString(1,coach.getEmailid());
        pr.setString(2,coach.getTeamArrayList().get(idx).getName());
        rs = pr.executeQuery();
        ArrayList<PlayerSkilL> arr = new ArrayList<PlayerSkilL>();
        while (rs.next())
        {
            PlayerSkilL tempplayerskill = new PlayerSkilL();
            tempplayerskill.setSkillName(rs.getString("SkillName"));
            tempplayerskill.setSkillValueType(rs.getInt("ValueType"));
            tempplayerskill.setSkillValueType(rs.getInt("ValueType"));
            arr.add(tempplayerskill);
        }
        rs.close();
        return  arr;
    }
    public void InsertSkillList(Coach coach,String teamname,ArrayList<PlayerSkilL>SkillNames) throws SQLException {
        try
        {
            for(int i=0;i<SkillNames.size();i++)
            {
                String sqlInsert = "INSERT INTO SkillsForTeam (Emailid , TeamName , SkillName , ValueType) VALUES (?, ?, ? ,?)";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setString(1,coach.getEmailid());
                stmt.setString(2,teamname);
                stmt.setString(3,SkillNames.get(i).getSkillName());
                stmt.setInt(4,SkillNames.get(i).getSkillValueType());
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void InsertPlayerSkill(Player player) throws SQLException {
        try
        {
            for(int i=0;i<player.getSkills().size();i++)
            {
                String sqlInsert = "INSERT INTO PlayerDetails (Emailid , TeamName , SkillName , SkillValue , PlayerName , Role , SkillValueType) VALUES (?, ?, ? ,?, ?, ? , ?)";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setString(1,player.getEmailid());
                stmt.setString(2,player.getPlayerTeamName());
                stmt.setString(3,player.getSkills().get(i).getSkillName());
                stmt.setInt(4,player.getSkills().get(i).getValue());
                stmt.setString(5,player.getName());
                stmt.setString(6,player.getRole());
                stmt.setInt(7,player.getSkills().get(i).getSkillValueType());
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdatePlayerSkill(Player player) throws SQLException {
        try
        {
            for(int i=0;i<player.getSkills().size();i++)
            {
                String sqlInsert = "UPDATE PlayerDetails SET SkillValue = ? WHERE PlayerName = ? AND  Emailid = ? AND TeamName = ? AND SkillName = ? AND Role = ? AND SkillValueType=?";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setInt(1,player.getSkills().get(i).getValue());
                stmt.setString(2,player.getName());
                stmt.setString(3,player.getEmailid());
                stmt.setString(4,player.getPlayerTeamName());
                stmt.setString(5,player.getSkills().get(i).getSkillName());
                stmt.setString(6,player.getRole());
                stmt.setInt(7,player.getSkills().get(i).getSkillValueType());
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
