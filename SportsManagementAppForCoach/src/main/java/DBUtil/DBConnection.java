package DBUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

public class DBConnection {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String conn = "jdbc:mysql://localhost/login";
    //private static final String sqconn = "jdbc:sqlite:Coach.sqlite";
    private static String sqconn;

    static  Connection connection = null;

    public static Connection getConnection()  {
        //new java.io.File(".").getCanonicalPath()
        try {
            String userDirectory = Paths.get("").toAbsolutePath().toString();
            System.out.println(userDirectory);
            String sqconn = "jdbc:sqlite:" + userDirectory + "/Coach.sqlite";
            System.out.println(sqconn);

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(sqconn);
            if(isTableExits("LoginDB")) createALL();
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isTableExits(String name){
        String query = "SELECT name from sqlite_master where type = 'table' and name = '" + name + "'";
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            boolean yes = resultSet.next();
            resultSet.close();
            statement.close();
            return yes;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void createALL()
    {

        try {
            PreparedStatement statement = null;
            String command = "CREATE TABLE LoginDB(id integer not null primary key autoincrement, id int, Username varchar, Emailid varchar, Password varchar, Age INTEGER)";
            statement = connection.prepareStatement(command);
            statement.executeUpdate();

            command = "CREATE TABLE PlayerDetails(id integer not null primary key autoincrement, id int, Emailid varchar, TeamName varchar, SkillValue int,PlayerName varchar,Role varchar, SkillValueType int )";
            statement = connection.prepareStatement(command);
            statement.executeUpdate();

            command = "CREATE TABLE PlayerInfo(no integer not null primary key autoincrement, id int, PlayerName varchar, Age int, Height int, PicPath varchar, Emailid varchar, TeamName varchar, Role varchar )";
            statement = connection.prepareStatement(command);
            statement.executeUpdate();

            command = "CREATE TABLE SkillForTeam(no integer not null primary key autoincrement, id int, Emailid varchar, TeamName varchar, SkillName varchar, ValueType int)";
            statement = connection.prepareStatement(command);
            statement.executeUpdate();

            command = "CREATE TABLE TeamForCoach(no integer not null primary key autoincrement, id int, Emailid varchar, Teamname varchar)";
            statement = connection.prepareStatement(command);
            statement.executeUpdate();




        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
