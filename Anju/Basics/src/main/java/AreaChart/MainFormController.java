package AreaChart;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MainFormController {

    @FXML
    private AreaChart<Number, Number> areaChart;

    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public MainFormController() {
        xAxis = new NumberAxis(1, 7, 1);
        yAxis = new NumberAxis(10, 500, 20);
    }

    @FXML
    public void initialize() {
        areaChart.setTitle("Student Performance");

        XYChart.Series<Number, Number> javaStudent = new XYChart.Series<>();
        javaStudent.setName("Java");

        javaStudent.getData().add(new XYChart.Data<>(1, 20));
        javaStudent.getData().add(new XYChart.Data<>(2, 45));
        javaStudent.getData().add(new XYChart.Data<>(3, 67));
        javaStudent.getData().add(new XYChart.Data<>(4, 89));
        javaStudent.getData().add(new XYChart.Data<>(5, 45));
        javaStudent.getData().add(new XYChart.Data<>(6, 120));
        javaStudent.getData().add(new XYChart.Data<>(7, 95));

        areaChart.getData().addAll(javaStudent);
    }
}
