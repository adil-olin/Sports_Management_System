package DBUtil;

import COACH.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void InsertIntoTable(String mailid,String tablename) throws SQLException {
        String sql = "INSERT INTO "+tablename+" ( Emailid ) VALUES ( '"+mailid+"' )";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        System.out.println(sql);
        stmt.executeUpdate();

        stmt.close();

    }
    public void UpdateTable(String tablename,String mailid,String columnname,String val) throws SQLException {
        String sql = "UPDATE "+tablename+" SET "+columnname+" = ? , " + "WHERE Emailid = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1,val);
        stmt.setString(2,mailid);
        stmt.executeUpdate();
        stmt.close();
    }
    public void UpdateTable(String tablename,String mailid,String columnname,int val) throws SQLException {
        String sql = "UPDATE "+tablename+" SET "+columnname+" = ? , " + "WHERE Emailid = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setInt(1,val);
        stmt.setString(2,mailid);
        stmt.executeUpdate();
        stmt.close();
    }
    public void AddTeamForCoach(String teamname,Coach coach) throws SQLException {
        String sqlInsert = "INSERT INTO TeamForCoach (Emailid , TeamName) VALUES (?, ?)";
        PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
        stmt.setString(1,coach.getEmailId());
        stmt.setString(2,teamname);
        stmt.executeUpdate();


    }
    public ResultSet getDATA(String x,String y,String val) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT "+x+" From LoginDB where "+y+" = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,val);
            rs = pr.executeQuery();
            ResultSet ans = rs;
            return ans;
        } catch (SQLException e) {
            return null;
        }
        finally {
//            rs.close();
//            pr.close();
        }
    }
    public ResultSet getDATA(String x,String y,int val) throws SQLException {
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

        ResultSet resultSet = null;
        resultSet = getDATA("*" , "Emailid" , emailid);
        Coach coach = new Coach();
        coach.setId(resultSet.getInt("Id"));
        coach.setName(resultSet.getString("Username"));
        coach.setEmailId(resultSet.getString("Emailid"));
        resultSet.close();
        return coach;
    }
    public Coach getCoachData(int id) throws SQLException {
        ResultSet rs = getDATA("*" , "Emailid" , id);
        Coach coach = new Coach();
        coach.setId(rs.getInt("Id"));
        coach.setName(rs.getString("Username"));
        coach.setEmailId(rs.getString("Emailid"));
        return coach;
    }
}
