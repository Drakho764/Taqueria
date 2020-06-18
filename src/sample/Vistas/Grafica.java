package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.CajaDAO;

public class Grafica extends Stage {
    TableView<CajaDAO> tbvCaj;
    private Scene escena;
    public Grafica(TableView<CajaDAO> tbvCaja){
        this.tbvCaj=tbvCaja;
        CrearGUI();
        this.setTitle("Ventas Diarias");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Días");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Ventas $");

        BarChart chart = new BarChart(yAxis,xAxis);
        chart.setTitle("Gráfica de ventas Diarias");
        chart.setData(getDataHSeries());

        VBox vbox = new VBox();
        vbox.getChildren().add(chart);

        escena = new Scene(vbox, 640, 427);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private ObservableList<XYChart.Series<String, Number>> getDataHSeries() {
        XYChart.Series<String,Number> autos = new XYChart.Series<>();
        autos.setName("Total de Caja $");
        for (int i = 0; i < tbvCaj.getItems().size(); i++){
            autos.getData().add(
                    new XYChart.Data<>(tbvCaj.getItems().get(i).getFecha(),tbvCaj.getItems().get(i).getCantidad() ));
        }
        ObservableList<XYChart.Series<String,Number>> data = FXCollections.observableArrayList();
        data.addAll(autos);
        return data;
    }
}
