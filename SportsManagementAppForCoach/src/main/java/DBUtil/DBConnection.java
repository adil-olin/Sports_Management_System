package DBUtil;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String conn = "jdbc:mysql://localhost/login";
    private static final String sqconn = "jdbc:sqlite:Coach.sqlite";
    //private static final String sqconn = "jdbc:sqlite:"+ Paths.get("").toAbsolutePath().toString()+"/SportsManagementAppForCoach/Coach.sqlite";

    public static Connection getConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(sqconn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
