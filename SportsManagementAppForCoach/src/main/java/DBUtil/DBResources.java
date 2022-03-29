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
    public ResultSet getDATA(String x,String y,String val) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
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
