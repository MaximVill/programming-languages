module programming.languages {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.lab10.calculator to javafx.fxml;
    opens org.example.lab10.drawing to javafx.fxml;

    exports org.example.lab10;
}