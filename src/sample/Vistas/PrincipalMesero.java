package sample.Vistas;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellCuentas;
import sample.Modelos.CuentasDAO;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class PrincipalMesero extends Stage {
    Scene escena;
    HBox hBox,hBox1,hBox2;
    VBox vBox,vB,vC,vE,vM,vO,vPl,vPe,vS,vCs,vTablas,vOr;
    Button btnBebida,btnCuenta,btnPlatillo,btnEmpleado,btnMesa,btnCuentas,btnOrden,btnPuesto,btnOrdenes,btnSalir;
    ImageView imageViewB = new ImageView("sample/Imagenes/bebida.png");

    ImageView imageViewOrd = new ImageView("sample/Imagenes/ordenes.png");
    ImageView imageViewC = new ImageView("sample/Imagenes/cuenta.png");
    ImageView imageViewPl = new ImageView("sample/Imagenes/taco.png");
    ImageView imageViewE = new ImageView("sample/Imagenes/empleado.png");
    ImageView imageViewM = new ImageView("sample/Imagenes/mesa.png");
    ImageView imageViewCs = new ImageView("sample/Imagenes/cuentas.png");
    ImageView imageViewO = new ImageView("sample/Imagenes/orden.png");
    ImageView imageViewP = new ImageView("sample/Imagenes/puestos.png");
    ImageView imageViewS = new ImageView("sample/Imagenes/salir.png");
    Label lblB=new Label("Bebidas");
    Label lblC=new Label("Pagar\nCuenta");
    Label lblE=new Label("Empleados");
    Label lblM=new Label("Mesas");
    Label lblO=new Label("Generar\nOrden");
    Label lblPl=new Label("Platillos");
    Label lblAc=new Label("Cuentas Activas");
    Label lblCs=new Label("Ver \n Cuentas");
    Label lblPen=new Label("Cuentas Pendientes de Pago");
    Label lblPe=new Label("Puestos");
    Label lblS=new Label("Cerrar\nSesión");
    Label lblOr=new Label("Ver \n Ordenes");

    TableView<CuentasDAO> tbvCuentasA;
    TableView<CuentasDAO> tbvCuentasP;
    CuentasDAO cue;
    int idEmpleado;
    String tipoEm;

    public PrincipalMesero(int idEm,String tipoEm){
        cue=new CuentasDAO();
        this.idEmpleado=idEm;
        System.out.println(idEmpleado);
        this.tipoEm=tipoEm;
        cue.buscarCaja();
        CrearGUI();
        this.setTitle("Mesero");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }
    public void CrearGUI(){
        imageViewB.setFitHeight(100);
        imageViewB.setFitWidth(100);
        imageViewC.setFitHeight(100);
        imageViewC.setFitWidth(100);
        imageViewE.setFitHeight(100);
        imageViewE.setFitWidth(100);
        imageViewP.setFitHeight(100);
        imageViewP.setFitWidth(100);
        imageViewPl.setFitHeight(100);
        imageViewPl.setFitWidth(100);
        imageViewO.setFitHeight(100);
        imageViewO.setFitWidth(100);
        imageViewM.setFitHeight(100);
        imageViewM.setFitWidth(100);
        imageViewS.setFitHeight(100);
        imageViewS.setFitWidth(100);
        imageViewCs.setFitHeight(100);
        imageViewCs.setFitWidth(100);
        imageViewOrd.setFitHeight(100);
        imageViewOrd.setFitWidth(100);


        lblB.setFont(Font.font("Cambria", 20));
        lblC.setFont(Font.font("Cambria", 20));
        lblE.setFont(Font.font("Cambria", 20));
        lblM.setFont(Font.font("Cambria", 20));
        lblO.setFont(Font.font("Cambria", 20));
        lblPl.setFont(Font.font("Cambria", 20));
        lblPe.setFont(Font.font("Cambria", 20));
        lblS.setFont(Font.font("Cambria", 20));
        lblAc.setFont(Font.font("Cambria", 20));
        lblPen.setFont(Font.font("Cambria", 20));
        lblCs.setFont(Font.font("Cambria", 20));
        lblOr.setFont(Font.font("Cambria", 20));

        vBox =new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15, 15, 15, 12));
        vOr =new VBox();
        vOr.setAlignment(Pos.CENTER);
        vOr.setPadding(new Insets(15, 15, 15, 12));
        vB =new VBox();
        vB.setAlignment(Pos.CENTER);
        vB.setPadding(new Insets(15, 15, 15, 12));
        vC=new VBox();
        vC.setAlignment(Pos.CENTER);
        vC.setPadding(new Insets(15, 15, 15, 12));
        vE=new VBox();
        vE.setAlignment(Pos.CENTER);
        vE.setPadding(new Insets(15, 15, 15, 12));
        vM=new VBox();
        vM.setAlignment(Pos.CENTER);
        vM.setPadding(new Insets(15, 15, 15, 12));
        vO=new VBox();
        vO.setAlignment(Pos.CENTER);
        vO.setPadding(new Insets(15, 15, 15, 12));
        vPl=new VBox();
        vPl.setAlignment(Pos.CENTER);
        vPl.setPadding(new Insets(15, 15, 15, 12));
        vPe=new VBox();
        vPe.setAlignment(Pos.CENTER);
        vPe.setPadding(new Insets(15, 15, 15, 12));
        vS=new VBox();
        vS.setAlignment(Pos.CENTER);
        vS.setPadding(new Insets(15, 15, 15, 12));
        vCs=new VBox();
        vCs.setAlignment(Pos.CENTER);
        vCs.setPadding(new Insets(15, 15, 15, 12));

        hBox1=new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(15, 15, 15, 12));
        hBox2=new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(15, 15, 15, 12));

        btnBebida= new Button();
        btnBebida.setGraphic(imageViewB);
        btnBebida.setMinSize(100, 100);
        btnBebida.setMaxSize(130, 130);
        btnBebida.setOnAction(event-> OpcionMenu(1));
        btnCuenta= new Button();
        btnCuenta.setGraphic(imageViewC);
        btnCuenta.setMinSize(100, 100);
        btnCuenta.setMaxSize(130, 130);
        btnCuenta.setOnAction(event-> OpcionMenu(2));
        btnPlatillo= new Button();
        btnPlatillo.setGraphic(imageViewPl);
        btnPlatillo.setMinSize(100, 100);
        btnPlatillo.setMaxSize(130, 130);
        btnPlatillo.setOnAction(event-> OpcionMenu(3));
        btnEmpleado= new Button();
        btnEmpleado.setGraphic(imageViewE);
        btnEmpleado.setMinSize(100, 100);
        btnEmpleado.setMaxSize(130, 130);
        btnEmpleado.setOnAction(event-> OpcionMenu(4));
        btnMesa= new Button();
        btnMesa.setGraphic(imageViewM);
        btnMesa.setMinSize(100, 100);
        btnMesa.setMaxSize(130, 130);
        btnMesa.setOnAction(event-> OpcionMenu(5));
        btnOrden= new Button();
        btnOrden.setGraphic(imageViewO);
        btnOrden.setMinSize(100, 100);
        btnOrden.setMaxSize(130, 130);
        btnOrden.setOnAction(event-> OpcionMenu(6));
        btnPuesto= new Button();
        btnPuesto.setGraphic(imageViewP);
        btnPuesto.setMinSize(100, 100);
        btnPuesto.setMaxSize(130, 130);
        btnPuesto.setOnAction(event-> OpcionMenu(7));
        btnSalir= new Button();
        btnSalir.setGraphic(imageViewS);
        btnSalir.setMinSize(100, 100);
        btnSalir.setMaxSize(130, 130);
        btnSalir.setOnAction(event-> OpcionMenu(8));
        btnCuentas= new Button();
        btnCuentas.setGraphic(imageViewCs);
        btnCuentas.setMinSize(100, 100);
        btnCuentas.setMaxSize(130, 130);
        btnCuentas.setOnAction(event-> OpcionMenu(9));
        btnOrdenes= new Button();
        btnOrdenes.setGraphic(imageViewOrd);
        btnOrdenes.setMinSize(100, 100);
        btnOrdenes.setMaxSize(130, 130);
        btnOrdenes.setOnAction(event-> OpcionMenu(10));



        vB.getChildren().addAll(btnBebida,lblB);
        vE.getChildren().addAll(btnEmpleado,lblE);
        vC.getChildren().addAll(btnCuenta,lblC);
        vPl.getChildren().addAll(btnPlatillo,lblPl);
        vO.getChildren().addAll(btnOrden,lblO);
        vPe.getChildren().addAll(btnPuesto,lblPe);
        vM.getChildren().addAll(btnMesa,lblM);
        vS.getChildren().addAll(btnSalir,lblS);
        vCs.getChildren().addAll(btnCuentas,lblCs);
        vOr.getChildren().addAll(btnOrdenes,lblOr);

        hBox1.getChildren().addAll(vB,vPl,vM,vE,vPe);
        hBox2.getChildren().addAll(vO,vOr,vCs,vC,vS);

        vBox.getChildren().addAll(hBox2);
        tbvCuentasA= new TableView<>();
        tbvCuentasP= new TableView<>();
        CrearTablaActiva();
        CrearTablaPendiente();
        vTablas =new VBox();
        vTablas.setAlignment(Pos.CENTER);
        vTablas.setPadding(new Insets(15, 15, 15, 12));
        vTablas.getChildren().addAll(lblAc,tbvCuentasA,lblPen,tbvCuentasP);
        hBox=new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15, 15, 15, 12));
        hBox.getChildren().addAll(vBox,vTablas);
        TimerTask timerTask = new TimerTask()
        {
            public void run()
            {
                tbvCuentasA.setItems(cue.obtenerCuentasActivasPorMesero(idEmpleado));
                tbvCuentasA.refresh();
                tbvCuentasP.setItems(cue.obtenerCuentasPendientesPorMesero(idEmpleado));
                tbvCuentasP.refresh();
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        escena = new Scene(hBox);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }
    private void CrearTablaActiva() {
        TableColumn<CuentasDAO,Integer> tbcIdCuenta = new TableColumn<>("Cuenta");
        tbcIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));
        TableColumn<CuentasDAO,Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<CuentasDAO,String> tbcHora = new TableColumn<>("Hora Entrada");
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("horaentrada"));
        TableColumn<CuentasDAO,String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<CuentasDAO,String> tbcPagar = new TableColumn<>("Pagar");
        tbcPagar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(1,idEmpleado,tipoEm);
                    }
                }
        );
        TableColumn<CuentasDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(3,idEmpleado,tipoEm);
                    }
                }
        );
        TableColumn<CuentasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(2,idEmpleado,tipoEm);
                    }
                }
        );

        tbvCuentasA.getColumns().addAll(tbcIdCuenta,tbcIdMesa,tbcHora,tbcEstado,tbcPagar);
        tbvCuentasA.setItems(cue.obtenerCuentasActivasPorMesero(idEmpleado));

    }
    private void CrearTablaPendiente() {
        TableColumn<CuentasDAO,Integer> tbcIdCuenta = new TableColumn<>("Cuenta");
        tbcIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));
        TableColumn<CuentasDAO,Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<CuentasDAO,String> tbcHora = new TableColumn<>("Hora Entrada");
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("horaentrada"));
        TableColumn<CuentasDAO,String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        TableColumn<CuentasDAO,Float> tbcTotal = new TableColumn<>("Total");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("totalCuenta"));
        TableColumn<CuentasDAO,String> tbcPagar = new TableColumn<>("¿Pagada?");
        tbcPagar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(4,idEmpleado,tipoEm);
                    }
                }
        );
        TableColumn<CuentasDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(5,idEmpleado,tipoEm);
                    }
                }
        );
        TableColumn<CuentasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CuentasDAO, String>, TableCell<CuentasDAO, String>>() {
                    @Override
                    public TableCell<CuentasDAO, String> call(TableColumn<CuentasDAO, String> param) {
                        return new ButtonCellCuentas(2,idEmpleado,tipoEm);
                    }
                }
        );
        tbvCuentasP.getColumns().addAll(tbcIdCuenta,tbcIdMesa,tbcHora,tbcEstado,tbcTotal,tbcPagar);
        tbvCuentasP.setItems(cue.obtenerCuentasPendientesPorMesero(idEmpleado));
    }


    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new CRUDBebidas();
                break;
            case 2:
                System.out.println("Pagar cuenta: " +idEmpleado);
                new FrmWhatCuenta(idEmpleado,3,tipoEm);
                break;
            case 3:
                new CRUDPlatillos();
                break;
            case 4:
                 new CRUDEmpleados();
                break;
            case 5:
                 new CRUDMesas();
                break;
            case 6:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cuenta");
                alert.setHeaderText("¿Dónde registrar la orden?");
                alert.setContentText("Cuenta Nueva o Cuenta Existente");
                ButtonType buttonTypeNva = new ButtonType("Cuenta Nueva");
                ButtonType buttonTypeExis = new ButtonType("Cuenta Existente");
                ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeNva,buttonTypeExis,buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeExis) {
                    new FrmWhatCuenta(idEmpleado,1,tipoEm);
                }else if(result.get() == buttonTypeNva){
                    new CRUDCuentas(idEmpleado,tipoEm).AgregarCuenta(2,idEmpleado);
                }
                break;
            case 7:
                 new CRUDPuestos();
                break;
            case 8:
                this.close();
                break;
            case 9:
                 new CRUDCuentas(idEmpleado,tipoEm);
                tbvCuentasA.setItems(cue.obtenerCuentasActivas());
                tbvCuentasA.refresh();
                tbvCuentasP.setItems(cue.obtenerCuentasPendientes());
                tbvCuentasP.refresh();
                break;
            case 10:
                new CRUDOrden(null,tipoEm,idEmpleado);
                break;
        }
    }
}
