module com.example.sportsmanagementappforcoach {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sportsmanagementappforcoach to javafx.fxml;
    exports com.example.sportsmanagementappforcoach;
}