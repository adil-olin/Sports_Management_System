package DBUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String conn = "jdbc:mysql://localhost/login";
    //private static final String sqconn = "jdbc:sqlite:Coach.sqlite";
    private static String sqconn;

    public static Connection getConnection()  {

        try {
            String sqconn = "jdbc:sqlite:" + new java.io.File(".").getCanonicalPath() + "/Coach.sqlite";
            System.out.println(sqconn);

            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(sqconn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
