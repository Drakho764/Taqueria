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
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.MesasDAO;

public class CRUDMesas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<MesasDAO> tbvMesas;
    private Button btnAgregar;
    private MesasDAO objM;

    public CRUDMesas() {
        objM = new MesasDAO();
        CrearGUI();
        this.setTitle("CRUD Mesas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvMesas = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Mesa");
        btnAgregar.setMinSize(120, 60);
        btnAgregar.setMaxSize(148, 74);
        btnAgregar.setOnAction(event -> AgregarMesa());
        vbox.getChildren().addAll(tbvMesas, btnAgregar);
        escena = new Scene(vbox, 620, 300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<MesasDAO, Integer> tbcIdMesa = new TableColumn<>("ID");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<MesasDAO, Integer> tbccantPer = new TableColumn<>("No Personas");
        tbccantPer.setCellValueFactory(new PropertyValueFactory<>("cantPer"));
        TableColumn<MesasDAO, String> tbcubicacion = new TableColumn<>("Ubicacion");
        tbcubicacion.setCellValueFactory(new PropertyValueFactory<>("ubic"));
        TableColumn<MesasDAO, String> tbcestado = new TableColumn<>("Estado");
        tbcestado.setCellValueFactory(new PropertyValueFactory<>("estado"));


        TableColumn<MesasDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<MesasDAO, String>, TableCell<MesasDAO, String>>() {
                    @Override
                    public TableCell<MesasDAO, String> call(TableColumn<MesasDAO, String> param) {
                        return new ButtonCellMs(2);
                    }
                }
        );
        TableColumn<MesasDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<MesasDAO, String>, TableCell<MesasDAO, String>>() {
                    @Override
                    public TableCell<MesasDAO, String> call(TableColumn<MesasDAO, String> param) {
                        return new ButtonCellMs(1);
                    }
                }
        );
        tbvMesas.getColumns().addAll(tbcIdMesa, tbccantPer, tbcubicacion,tbcestado, tbcEditar, tbcBorrar);
        tbvMesas.setItems(objM.selAllMesas());
    }

    private void AgregarMesa() {
        new FrmMesa(tbvMesas, null);
    }
}
