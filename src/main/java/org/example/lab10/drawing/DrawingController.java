package org.example.lab10.drawing;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Random;

public class DrawingController {

    @FXML
    private Pane drawingPane;

    private final Random random = new Random();

    @FXML
    private void drawCircle() {
        double x = random.nextDouble() * (drawingPane.getWidth() - 100) + 50;
        double y = random.nextDouble() * (drawingPane.getHeight() - 100) + 50;
        double radius = random.nextDouble() * 40 + 20;

        Circle circle = new Circle(x, y, radius);
        circle.setFill(randomColor());
        setupDragAndClick(circle);
        drawingPane.getChildren().add(circle);
    }

    @FXML
    private void drawRectangle() {
        double x = random.nextDouble() * (drawingPane.getWidth() - 120) + 60;
        double y = random.nextDouble() * (drawingPane.getHeight() - 80) + 40;
        double w = random.nextDouble() * 100 + 50;
        double h = random.nextDouble() * 80 + 40;

        Rectangle rect = new Rectangle(x - w / 2, y - h / 2, w, h);
        rect.setFill(randomColor());
        setupDragAndClick(rect);
        drawingPane.getChildren().add(rect);
    }

    @FXML
    private void drawTriangle() {
        double cx = random.nextDouble() * (drawingPane.getWidth() - 100) + 50;
        double cy = random.nextDouble() * (drawingPane.getHeight() - 100) + 50;
        double size = random.nextDouble() * 60 + 30;

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                cx, cy - size, // вершина
                cx - size, cy + size, // левый угол
                cx + size, cy + size // правый угол
        );
        triangle.setFill(randomColor());
        setupDragAndClick(triangle);
        drawingPane.getChildren().add(triangle);
    }

    private Color randomColor() {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    private void setupDragAndClick(Shape shape) {
        final double[] dragOffsetX = {0};
        final double[] dragOffsetY = {0};

        shape.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Поднять фигуру на передний план
                drawingPane.getChildren().remove(shape);
                drawingPane.getChildren().add(shape); // теперь последний — сверху

                // Сохранить смещение курсора от центра фигуры
                if (shape instanceof Circle c) {
                    dragOffsetX[0] = event.getX() - c.getCenterX();
                    dragOffsetY[0] = event.getY() - c.getCenterY();
                } else if (shape instanceof Rectangle r) {
                    dragOffsetX[0] = event.getX() - r.getX();
                    dragOffsetY[0] = event.getY() - r.getY();
                } else if (shape instanceof Polygon p) {
                    // Берём первую точку как опорную
                    dragOffsetX[0] = event.getX() - p.getPoints().get(0);
                    dragOffsetY[0] = event.getY() - p.getPoints().get(1);
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                shape.setFill(randomColor());
                event.consume();
            }
        });

        shape.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double newX = event.getX() - dragOffsetX[0];
                double newY = event.getY() - dragOffsetY[0];

                if (shape instanceof Circle c) {
                    c.setCenterX(newX);
                    c.setCenterY(newY);
                } else if (shape instanceof Rectangle r) {
                    r.setX(newX);
                    r.setY(newY);
                } else if (shape instanceof Polygon p) {
                    double dx = newX - p.getPoints().get(0);
                    double dy = newY - p.getPoints().get(1);
                    // Сдвигаем все точки
                    for (int i = 0; i < p.getPoints().size(); i += 2) {
                        p.getPoints().set(i, p.getPoints().get(i) + dx);
                        p.getPoints().set(i + 1, p.getPoints().get(i + 1) + dy);
                    }
                }
                event.consume();
            }
        });
    }
}