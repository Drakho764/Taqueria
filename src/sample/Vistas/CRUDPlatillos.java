package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellMs;
import sample.Componentes.ButtonCellPl;
import sample.Modelos.BebidasDAO;
import sample.Modelos.MesasDAO;
import sample.Modelos.PlatillosDAO;

public class CRUDPlatillos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<PlatillosDAO> tbvPlat;
    private Button btnAgregar;
    private PlatillosDAO objM;

    public CRUDPlatillos() {
        objM = new PlatillosDAO();
        CrearGUI();
        this.setTitle("CRUD Platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvPlat = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Platillo");
        btnAgregar.setMinSize(120, 60);
        btnAgregar.setMaxSize(148, 74);
        btnAgregar.setOnAction(event -> AgregarMesa());
        vbox.getChildren().addAll(tbvPlat, btnAgregar);
        escena = new Scene(vbox, 620, 300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<PlatillosDAO, Integer> tbcIdMesa = new TableColumn<>("ID");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));
        TableColumn<PlatillosDAO, String> tbcnom = new TableColumn<>("Nombre");
        tbcnom.setCellValueFactory(new PropertyValueFactory<>("nomPlatillo"));
        TableColumn<PlatillosDAO, String> tbcudesc = new TableColumn<>("Descripcion");
        tbcudesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        TableColumn<PlatillosDAO, Float> tbcprecio = new TableColumn<>("Precio");
        tbcprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<PlatillosDAO, String> tbcestado = new TableColumn<>("Estado");
        tbcestado.setCellValueFactory(new PropertyValueFactory<>("estado"));


        TableColumn<PlatillosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCellPl(2);
                    }
                }
        );
        TableColumn<PlatillosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCellPl(1);
                    }
                }
        );
        tbvPlat.getColumns().addAll(tbcIdMesa, tbcnom, tbcudesc,tbcprecio,tbcestado, tbcEditar, tbcBorrar);
        tbvPlat.setItems(objM.selAllPlatillos());
    }

    private void AgregarMesa() {
        new FrmPlatillo(tbvPlat, null);
    }
}
