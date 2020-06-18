package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellCaja;
import sample.Componentes.ButtonCellOrden;
import sample.Modelos.CajaDAO;
import sample.Modelos.CuentasDAO;
import sample.Modelos.OrdenDAO;

public class CRUDVentas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<CajaDAO> tbvCaja;
    private Button btnVerGrafica;
    private CajaDAO objC;

    public CRUDVentas() {
        objC=new CajaDAO();
        CrearGUI();
        this.setTitle("CRUD Ventas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvCaja = new TableView<>();
        CrearTabla();
        btnVerGrafica = new Button("Ver Grafica");
        btnVerGrafica.setOnAction(event -> VerGrafica());
        btnVerGrafica.setMinSize(120, 60);
        btnVerGrafica.setMaxSize(148, 74);
        vbox.getChildren().addAll(tbvCaja, btnVerGrafica);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<CajaDAO,Integer> tbcIdCaja = new TableColumn<>("Caja");
        tbcIdCaja.setCellValueFactory(new PropertyValueFactory<>("idcaja"));
        TableColumn<CajaDAO,Float> tbcIdGan = new TableColumn<>("Ganancia");
        tbcIdGan.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn<CajaDAO,String> tbcFecha = new TableColumn<>("Fecha");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));


        TableColumn<CajaDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<CajaDAO, String>, TableCell<CajaDAO, String>>() {
                    @Override
                    public TableCell<CajaDAO, String> call(TableColumn<CajaDAO, String> param) {
                        return  new ButtonCellCaja(2);
                    }
                }
        );
        TableColumn<CajaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CajaDAO, String>, TableCell<CajaDAO, String>>() {
                    @Override
                    public TableCell<CajaDAO, String> call(TableColumn<CajaDAO, String> param) {

                        return  new ButtonCellCaja(1);
                    }
                }
        );
        tbvCaja.getColumns().addAll(tbcIdCaja,tbcIdGan,tbcFecha,tbcEditar,tbcBorrar);

            tbvCaja.setItems(objC.selAllCajas());

    }

    public void VerGrafica() {
        new Grafica(tbvCaja);
        this.close();
    }
}
