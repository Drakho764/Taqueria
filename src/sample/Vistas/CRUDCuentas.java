package sample.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellCuentas;
import sample.Componentes.ButtonCellMs;
import sample.Modelos.CuentasDAO;
import sample.Modelos.MesasDAO;

public class CRUDCuentas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<CuentasDAO> tbvCuentas;
    private Button btnAgregar;
    private CuentasDAO objC;
    int idEmpleado2;
    String tipoEm;

    public CRUDCuentas(int idEmpleado2,String tipoEm) {
        objC = new CuentasDAO();
        this.idEmpleado2=idEmpleado2;
        this.tipoEm=tipoEm;
        CrearGUI();
        this.setTitle("CRUD Cuentas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvCuentas = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Cuenta");
        btnAgregar.setOnAction(event -> AgregarCuenta(1,idEmpleado2));
        btnAgregar.setMinSize(120, 60);
        btnAgregar.setMaxSize(148, 74);
        vbox.getChildren().addAll(tbvCuentas, btnAgregar);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<CuentasDAO,Integer> tbcIdCuenta = new TableColumn<>("Cuenta");
        tbcIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));
        TableColumn<CuentasDAO,Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<CuentasDAO,String> tbcHora = new TableColumn<>("Hora Entrada");
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("horaentrada"));
        TableColumn<CuentasDAO,String> tbcHoraS = new TableColumn<>("Hora Salida");
        tbcHoraS.setCellValueFactory(new PropertyValueFactory<>("horasalida"));
        TableColumn<CuentasDAO,String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        TableColumn<CuentasDAO,String> tbcObser = new TableColumn<>("Observación");
        tbcObser.setCellValueFactory(new PropertyValueFactory<>("observacion"));
        TableColumn<CuentasDAO,Float> tbcTotalCuenta = new TableColumn<>("Total $");
        tbcTotalCuenta.setCellValueFactory(new PropertyValueFactory<>("totalCuenta"));
        TableColumn<CuentasDAO,Integer> tbcIdEmpleado = new TableColumn<>("Empleado");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));

        TableColumn<CuentasDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(6,idEmpleado2,tipoEm);
                    }
                }
        );
        TableColumn<CuentasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(2,idEmpleado2,tipoEm);
                    }
                }
        );
        tbvCuentas.getColumns().addAll(tbcIdCuenta,tbcIdMesa,tbcHora,tbcHoraS,tbcEstado,tbcObser,tbcTotalCuenta,tbcIdEmpleado,tbcEditar,tbcBorrar);
        if(tipoEm=="admin"){
            tbvCuentas.setItems(objC.selAllCuentas());
        }else if(tipoEm=="mesero"){
            tbvCuentas.setItems(objC.selAllCuentasPorMesero(idEmpleado2));
        }



    }

    public void AgregarCuenta(int nu,int idEmpleado) {
        new FrmCuenta(tbvCuentas, null,nu,idEmpleado,tipoEm);
    }
}
