package com.example.sportsmanagementappforcoach;

import DBUtil.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;

public class SignUpModel {
    Connection connection;
    public SignUpModel()
    {
        this.connection = DBConnection.getConnection();

        if(this.connection == null)
        {
            System.exit(1);
        }
    }
    public boolean isDataBaseConnected()
    {
        return this.connection!=null;
    }
    public boolean Signup(ActionEvent event, String username, String emailid, String password , int age) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM LoginDB where Emailid = ?";
        try
        {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,emailid);
            rs = pr.executeQuery();
            if(rs.next())
            {
                return false;
            }
            else
            {
                String sqlInsert = "INSERT INTO LoginDB (Username, Emailid, Password, Age) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = this.connection.prepareStatement(sqlInsert);
                stmt.setString(1,username);
                stmt.setString(2,emailid);
                stmt.setString(3,password);
                stmt.setInt(4,age);
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
