package AreaChart;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;

public class MainFormController {
    public AreaChart<Number,Number> areaChart;

    NumberAxis xAxis = new NumberAxis(1,7,1);
    NumberAxis yAxis = new NumberAxis(10,500,20);

    public void initialize() {}
}
