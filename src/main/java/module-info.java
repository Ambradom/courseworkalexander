module org.example.courseworkalexander {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.courseworkalexander to javafx.fxml;
    exports org.example.courseworkalexander;
}