module com.example.instagramemulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.instagramemulator to javafx.fxml;
    exports com.example.instagramemulator;
}