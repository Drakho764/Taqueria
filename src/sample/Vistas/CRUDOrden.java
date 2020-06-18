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
import sample.Componentes.ButtonCellCuentas;
import sample.Componentes.ButtonCellOrden;
import sample.Modelos.CuentasDAO;
import sample.Modelos.OrdenDAO;

public class CRUDOrden extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<OrdenDAO> tbvOrdenes;
    private Button btnVerPorCuenta;
    private OrdenDAO objC;
    private CuentasDAO objCuentas;
    String tipoEm;
    int idEmpleado;

    public CRUDOrden(CuentasDAO objCuentas,String tipoEm,int idEmpleado) {
        objC = new OrdenDAO();
        this.objCuentas=objCuentas;
        this.tipoEm=tipoEm;
        this.idEmpleado=idEmpleado;
        CrearGUI();
        this.setTitle("CRUD Ordenes");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvOrdenes = new TableView<>();
        CrearTabla();
        btnVerPorCuenta = new Button("Ver por cuenta");
        btnVerPorCuenta.setOnAction(event -> VerPorCuenta());
        btnVerPorCuenta.setMinSize(120, 60);
        btnVerPorCuenta.setMaxSize(148, 74);
        vbox.getChildren().addAll(tbvOrdenes, btnVerPorCuenta);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void CrearTabla() {
        TableColumn<OrdenDAO,Integer> tbcIdOrden = new TableColumn<>("Orden");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("idorden"));
        TableColumn<OrdenDAO,Integer> tbcIdCuenta = new TableColumn<>("Cuenta");
        tbcIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idcuenta"));
        TableColumn<OrdenDAO,Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idmesa"));
        TableColumn<OrdenDAO,Integer> tbcIdPlatillo = new TableColumn<>("Platillo");
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("idplatillo"));
        TableColumn<OrdenDAO,String> tbcNomPlatillo = new TableColumn<>("Nombre");
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nomPla"));
        TableColumn<OrdenDAO,Integer> tbcCantPla = new TableColumn<>("Cantidad");
        tbcCantPla.setCellValueFactory(new PropertyValueFactory<>("cantPla"));
        TableColumn<OrdenDAO,Integer> tbcIdBebida = new TableColumn<>("Bebida");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("idbebida"));
        TableColumn<OrdenDAO,Integer> tbcNomBebida = new TableColumn<>("Nombre");
        tbcNomBebida.setCellValueFactory(new PropertyValueFactory<>("nomBeb"));
        TableColumn<OrdenDAO,Integer> tbcCantBeb = new TableColumn<>("Cantidad");
        tbcCantBeb.setCellValueFactory(new PropertyValueFactory<>("cantBeb"));
        TableColumn<OrdenDAO,String> tbcObser = new TableColumn<>("Observación");
        tbcObser.setCellValueFactory(new PropertyValueFactory<>("obs"));
        TableColumn<OrdenDAO,Float> tbcTotalPar = new TableColumn<>("Total Parcial");
        tbcTotalPar.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<OrdenDAO,Integer> tbcIdEmpleado = new TableColumn<>("Empleado");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("idempleado"));

        TableColumn<OrdenDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> param) {
                        return  new ButtonCellOrden(2);
                    }
                }
        );
        TableColumn<OrdenDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> param) {

                        return  new ButtonCellOrden(1);
                    }
                }
        );
        tbvOrdenes.getColumns().addAll(tbcIdOrden,tbcIdCuenta,tbcIdMesa,tbcIdPlatillo,tbcNomPlatillo,tbcCantPla
                ,tbcIdBebida,tbcNomBebida,tbcCantBeb,tbcObser,tbcTotalPar,tbcIdEmpleado,tbcEditar,tbcBorrar);
        if(objCuentas==null&&tipoEm=="mesero") {
            tbvOrdenes.setItems(objC.selAllOrdenesPorMesero(idEmpleado));
        }else if(objCuentas==null&&tipoEm=="admin") {
            tbvOrdenes.setItems(objC.selAllOrdenes());
        }
        else {
            tbvOrdenes.setItems(objC.selAllOrdenesbyCuenta(objCuentas.getIdCuenta()));
        }
    }

    public void VerPorCuenta() {
        new FrmWhatCuenta(idEmpleado, 2,tipoEm);
        this.close();
    }
}
