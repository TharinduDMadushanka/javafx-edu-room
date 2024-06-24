package Chart.pieChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class MainFormController {
    public PieChart pieChart;

    public void initialize() {

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        data.addAll(
          new PieChart.Data("Java",60),
          new PieChart.Data("Python",20),
          new PieChart.Data("Go",40)
        );

        pieChart.setData(data);
    }
}
