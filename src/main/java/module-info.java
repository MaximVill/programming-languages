module programming.languages {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.example.lab10 to javafx.fxml;
    exports org.example.lab10;
}