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
import sample.Componentes.ButtonCellPs;
import sample.Modelos.PuestosDAO;

public class CRUDPuestos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<PuestosDAO> tbvPuestos;
    private Button btnAgregar;
    private PuestosDAO objP;

    public CRUDPuestos(){
        objP= new PuestosDAO();
        CrearGUI();
        this.setTitle("CRUD Puestos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvPuestos = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Puesto");
        btnAgregar.setOnAction(event -> AgregarPuesto());
        vbox.getChildren().addAll(tbvPuestos,btnAgregar);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }
    private void CrearTabla() {
        TableColumn<PuestosDAO,Integer> tbcIdPuesto = new TableColumn<>("ID");
        tbcIdPuesto.setCellValueFactory(new PropertyValueFactory<>("idPuesto"));
        TableColumn<PuestosDAO,String> tbcnomPuesto = new TableColumn<>("Nombre");
        tbcnomPuesto.setCellValueFactory(new PropertyValueFactory<>("nomPuesto"));
        TableColumn<PuestosDAO,Float> tbcsueldo = new TableColumn<>("Sueldo");
        tbcsueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        TableColumn<PuestosDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<PuestosDAO, String>, TableCell<PuestosDAO, String>>() {
                    @Override
                    public TableCell<PuestosDAO, String> call(TableColumn<PuestosDAO, String> param) {
                        return new ButtonCellPs(2);
                    }
                }
        );
        TableColumn<PuestosDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PuestosDAO, String>, TableCell<PuestosDAO, String>>() {
                    @Override
                    public TableCell<PuestosDAO, String> call(TableColumn<PuestosDAO, String> param) {
                        return new ButtonCellPs(1);
                    }
                }
        );
        tbvPuestos.getColumns().addAll(tbcIdPuesto,tbcnomPuesto,tbcsueldo,tbcEditar,tbcBorrar);
        tbvPuestos.setItems(objP.selAllPuestos());
    }
    private void AgregarPuesto() {new FrmPuesto(tbvPuestos,null);    }
}
