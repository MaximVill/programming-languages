package org.example.lab10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        Button calcBtn = new Button("Калькулятор");
        Button drawBtn = new Button("Рисование фигур");

        calcBtn.setOnAction(e -> launchFXML(stage, "calculator.fxml", "Калькулятор"));
        drawBtn.setOnAction(e -> launchFXML(stage, "drawing.fxml", "Рисование"));

        VBox root = new VBox(20, calcBtn, drawBtn);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Лабораторная №10");
        stage.setScene(scene);
        stage.show();
    }

    private void launchFXML(Stage stage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource(fxmlPath)
            );
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.sizeToScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}