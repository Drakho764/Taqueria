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
import sample.Componentes.ButtonCell;
import sample.Modelos.EmpleadosDAO;

public class CRUDEmpleados extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<EmpleadosDAO> tbvEmpleados;
    private Button btnAgregar;
    private EmpleadosDAO objE;
    
    public CRUDEmpleados(){
        objE= new EmpleadosDAO();
        CrearGUI();
        this.setTitle("CRUD Empleado");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvEmpleados = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Empleado");
        btnAgregar.setOnAction(event -> AgregarEmpleado());
        btnAgregar.setMinSize(120, 60);
        btnAgregar.setMaxSize(148, 74);
        vbox.getChildren().addAll(tbvEmpleados,btnAgregar);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }
    private void CrearTabla() {
        TableColumn<EmpleadosDAO,Integer> tbcIdEmpleado = new TableColumn<>("ID");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        TableColumn<EmpleadosDAO,String> tbcnomEmpleado = new TableColumn<>("Nombre");
        tbcnomEmpleado.setCellValueFactory(new PropertyValueFactory<>("nomEmpleado"));

        TableColumn<EmpleadosDAO,String> tbcemailEmpleado = new TableColumn<>("Email");
        tbcemailEmpleado.setCellValueFactory(new PropertyValueFactory<>("emailEmpleado"));

        TableColumn<EmpleadosDAO,String> tbctelEmpleado = new TableColumn<>("Telefono");
        tbctelEmpleado.setCellValueFactory(new PropertyValueFactory<>("telEmpleado"));

        TableColumn<EmpleadosDAO,Integer> tbcedadEmpleado = new TableColumn<>("Edad");
        tbcedadEmpleado.setCellValueFactory(new PropertyValueFactory<>("edad"));
        TableColumn<EmpleadosDAO,String> tbcdomEmpleado = new TableColumn<>("Domicilio");
        tbcdomEmpleado.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        TableColumn<EmpleadosDAO,Integer> tbcidPuesto = new TableColumn<>("Puesto");
        tbcidPuesto.setCellValueFactory(new PropertyValueFactory<>("idPuesto"));
        TableColumn<EmpleadosDAO,Integer> tbcUs= new TableColumn<>("Usuario");
        tbcUs.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        TableColumn<EmpleadosDAO,Integer> tbccont= new TableColumn<>("Contraseña");
        tbccont.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        TableColumn<EmpleadosDAO,Integer> tbcTipo= new TableColumn<>("Tipo");
        tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<EmpleadosDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                        return new ButtonCell(2);
                    }
                }
        );
        TableColumn<EmpleadosDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                        return new ButtonCell(1);
                    }
                }
        );
        tbvEmpleados.getColumns().addAll(tbcIdEmpleado,tbcnomEmpleado,tbcemailEmpleado,tbctelEmpleado,tbcedadEmpleado,
                tbcdomEmpleado,tbcidPuesto,tbcUs,tbccont,tbcTipo,tbcEditar,tbcBorrar);
        tbvEmpleados.setItems(objE.selAllEmpleados());
    }
    private void AgregarEmpleado() {new FrmEmpleado(tbvEmpleados,null);    }
}
