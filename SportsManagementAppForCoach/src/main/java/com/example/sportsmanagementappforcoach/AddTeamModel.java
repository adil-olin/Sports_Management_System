package com.example.sportsmanagementappforcoach;

import COACH.Coach;
import DBUtil.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddTeamModel{
    Connection connection;
    public AddTeamModel()
    {
        this.connection = DBConnection.getConnection();

        if(this.connection == null)
        {
            System.exit(1);
        }
    }
    public Boolean isDataBaseConnected()
    {
        return this.connection!=null;
    }

    public Boolean AddTeamModelAddTeam(String teamname , Coach coach) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM TeamForCoach where Emailid = ? and TeamName = ?";
        try
        {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,coach.getEmailId());
            pr.setString(2,teamname);
            rs = pr.executeQuery();
            if(rs.next())
            {
                return false;
            }
            else
            {
                String sqlInsert = "INSERT INTO TeamForCoach (Emailid, TeamName) VALUES (?, ?)";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setString(1,coach.getEmailId());
                stmt.setString(2,teamname);
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

}
