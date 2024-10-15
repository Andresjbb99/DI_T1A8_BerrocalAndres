package ieslosmontecillos.di_t1a8_berrocalandres;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    private TextField tfInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    
    @Override
    public void start(Stage primaryStage) {
        // CreO el panel de entrada de datos con etiquetas y campos de texto
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        // Etiquetas y campos de entrada
        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(tfInterestRate, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(tfMonthlyPayment, 1, 3);
        // No editable
        tfMonthlyPayment.setEditable(false); 
        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(tfTotalPayment, 1, 4);
        // No editable
        tfTotalPayment.setEditable(false); 

        // Botón para calcular
        Button btnCalculate = new Button("Calculate");
        gridPane.add(btnCalculate, 1, 5);
        GridPane.setHalignment(btnCalculate, javafx.geometry.HPos.RIGHT);

        // Acción del botón usando una expresión Lambda
        btnCalculate.setOnAction(e -> calculateLoanPayment());

        // Creo la escena y la muestro
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateLoanPayment() {
        // Obtengo los valores de entrada
        double interestRate = Double.parseDouble(tfInterestRate.getText());
        int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
        double loanAmount = Double.parseDouble(tfLoanAmount.getText());

        // Calculo la cuota mensual
        double monthlyInterestRate = interestRate / 1200;
        double numberOfMonths = numberOfYears * 12;
        double monthlyPayment = loanAmount * monthlyInterestRate / 
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));
        double totalPayment = monthlyPayment * numberOfMonths;

        // Muestro los resultados en los campos de texto
        tfMonthlyPayment.setText(String.format("%.2f", monthlyPayment));
        tfTotalPayment.setText(String.format("%.2f", totalPayment));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

