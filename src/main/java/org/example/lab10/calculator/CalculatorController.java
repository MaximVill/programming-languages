package org.example.lab10.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CalculatorController {

    @FXML
    private TextField displayField;

    private double currentValue = 0;
    private double previousValue = 0;
    private String currentOperator = "";
    private boolean startNewInput = true;

    // Обработчики кнопок
    @FXML
    private void handleNumberButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();

        if (startNewInput) {
            displayField.setText(number);
            startNewInput = false;
        } else {
            displayField.setText(displayField.getText() + number);
        }
    }

    @FXML
    private void handleOperatorButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String operator = button.getText();

        if (!currentOperator.isEmpty()) {
            calculate();
        }

        previousValue = Double.parseDouble(displayField.getText());
        currentOperator = operator;
        startNewInput = true;
    }

    @FXML
    private void handleEqualsButton(ActionEvent event) {
        calculate();
        currentOperator = "";
        startNewInput = true;
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        displayField.setText("0");
        currentValue = 0;
        previousValue = 0;
        currentOperator = "";
        startNewInput = true;
    }

    @FXML
    private void handleDecimalButton(ActionEvent event) {
        if (!displayField.getText().contains(".")) {
            displayField.setText(displayField.getText() + ".");
        }
    }

    @FXML
    private void handleSignButton(ActionEvent event) {
        double value = Double.parseDouble(displayField.getText());
        displayField.setText(String.valueOf(-value));
    }

    private void calculate() {
        double currentValue = Double.parseDouble(displayField.getText());

        switch (currentOperator) {
            case "+":
                displayField.setText(String.valueOf(previousValue + currentValue));
                break;
            case "-":
                displayField.setText(String.valueOf(previousValue - currentValue));
                break;
            case "*":
                displayField.setText(String.valueOf(previousValue * currentValue));
                break;
            case "/":
                if (currentValue == 0) {
                    displayField.setText("Error: Division by zero");
                    startNewInput = true;
                    return;
                }
                displayField.setText(String.valueOf(previousValue / currentValue));
                break;
        }
    }

    // Обработка ввода с клавиатуры
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.isDigitKey()) {
            handleNumberButton(new ActionEvent());
        } else if (code == KeyCode.ADD || code == KeyCode.PLUS) {
            handleOperatorButton(new ActionEvent());
        } else if (code == KeyCode.SUBTRACT || code == KeyCode.MINUS) {
            handleOperatorButton(new ActionEvent());
        } else if (code == KeyCode.MULTIPLY || code == KeyCode.ASTERISK) {
            handleOperatorButton(new ActionEvent());
        } else if (code == KeyCode.DIVIDE || code == KeyCode.SLASH) {
            handleOperatorButton(new ActionEvent());
        } else if (code == KeyCode.ENTER || code == KeyCode.EQUALS) {
            handleEqualsButton(new ActionEvent());
        } else if (code == KeyCode.C) {
            handleClearButton(new ActionEvent());
        } else if (code == KeyCode.PERIOD || code == KeyCode.DECIMAL) {
            handleDecimalButton(new ActionEvent());
        } else if (code == KeyCode.SPACE) {
            handleSignButton(new ActionEvent());
        }
    }
}