module com.example.twiitergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.twiitergui to javafx.fxml;
    exports com.example.twiitergui;
}