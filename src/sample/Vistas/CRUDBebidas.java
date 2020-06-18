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
import sample.Componentes.ButtonCellBeb;
import sample.Componentes.ButtonCellMs;
import sample.Modelos.BebidasDAO;
import sample.Modelos.MesasDAO;

public class CRUDBebidas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<BebidasDAO> tbvBebidas;
    private Button btnAgregar;
    private BebidasDAO objM;

    public CRUDBebidas() {
        objM = new BebidasDAO();
        CrearGUI();
        this.setTitle("CRUD Bebidas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvBebidas = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar bebida");
        btnAgregar.setMinSize(120, 60);
        btnAgregar.setMaxSize(148, 74);
        btnAgregar.setOnAction(event -> AgregarBebida());
        vbox.getChildren().addAll(tbvBebidas, btnAgregar);
        escena = new Scene(vbox, 620, 300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<BebidasDAO, Integer> tbcIdBebida = new TableColumn<>("ID");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("idbebida"));
        TableColumn<BebidasDAO, String> tbcnomb = new TableColumn<>("Nombre");
        tbcnomb.setCellValueFactory(new PropertyValueFactory<>("nombeb"));
        TableColumn<BebidasDAO, String> tbcdesc = new TableColumn<>("Descripcion");
        tbcdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        TableColumn<BebidasDAO, Float> tbcprecio = new TableColumn<>("Precio");
        tbcprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<BebidasDAO, Integer> tbccant = new TableColumn<>("Cantidad");
        tbccant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<BebidasDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCellBeb(2);
                    }
                }
        );
        TableColumn<BebidasDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCellBeb(1);
                    }
                }
        );
        tbvBebidas.getColumns().addAll(tbcIdBebida, tbcnomb, tbcdesc,tbcprecio,tbccant, tbcEditar, tbcBorrar);
        tbvBebidas.setItems(objM.selAllBebidas());
    }

    private void AgregarBebida() {
        new FrmBebida(tbvBebidas, null);
    }
}
