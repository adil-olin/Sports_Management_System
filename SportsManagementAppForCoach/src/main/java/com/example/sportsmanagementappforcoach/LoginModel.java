package com.example.sportsmanagementappforcoach;

import DBUtil.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;
    public LoginModel()
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

    public boolean isLogin(String emailid,String password) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * From LoginDB where Emailid = ? and Password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,emailid);
            pr.setString(2,password);
            rs = pr.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            return false;
        }
        finally {
            assert pr != null;
            pr.close();
            assert rs != null;
            rs.close();
        }
    }
}
