module com.example.sportsmanagementappforcoach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sportsmanagementappforcoach to javafx.fxml;
    exports com.example.sportsmanagementappforcoach;
}