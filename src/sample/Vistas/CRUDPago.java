package sample.Vistas;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellOrden;
import sample.Modelos.CuentasDAO;
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.OrdenDAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class CRUDPago extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<OrdenDAO> tbvOrdenes;
    private Button btnRecibo;
    private OrdenDAO objC;
    private CuentasDAO objCuentas;
    int hora, minutos, segundos;
    Calendar calendario = Calendar.getInstance();
    public CRUDPago(CuentasDAO objCuentas) {
        objC = new OrdenDAO();
        this.objCuentas=objCuentas;
        CrearGUI();
        this.setTitle("Hacer Recibo");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvOrdenes = new TableView<>();
        CrearTabla();
        btnRecibo = new Button("Generar Recibo");
        btnRecibo.setOnAction(event -> GenerarRecibo());
        btnRecibo.setMinSize(120, 60);
        btnRecibo.setMaxSize(148, 74);
        vbox.getChildren().addAll(tbvOrdenes, btnRecibo);
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

        tbvOrdenes.getColumns().addAll(tbcIdOrden,tbcIdCuenta,tbcIdMesa,tbcIdPlatillo,tbcNomPlatillo,tbcCantPla
                ,tbcIdBebida,tbcNomBebida,tbcCantBeb,tbcObser,tbcTotalPar,tbcIdEmpleado);
            tbvOrdenes.setItems(objC.selAllOrdenesbyCuenta(objCuentas.getIdCuenta()));
    }

    public void GenerarRecibo() {
        float totalT=0;
        String contenido="";
        for (int i = 0; i < tbvOrdenes.getItems().size(); i++){
                    totalT+= tbvOrdenes.getItems().get(i).getPrecio();
                    String cont="    "+tbvOrdenes.getItems().get(i).getIdorden()+"          "+tbvOrdenes.getItems().get(i).getNomPla()+"         "+
                            tbvOrdenes.getItems().get(i).getCantPla()+"            "+tbvOrdenes.getItems().get(i).getNomBeb()+"            "+
                            tbvOrdenes.getItems().get(i).getCantBeb()+"            "+tbvOrdenes.getItems().get(i).getPrecio()+"\n";
                    contenido+= cont;
        }
        EmpleadosDAO em=new EmpleadosDAO();
        String nombreem=em.getNombrebyId(tbvOrdenes.getItems().get(0).getIdempleado());
        try {
            OutputStream file = new FileOutputStream(new File("C:\\Users\\franc\\OneDrive\\Documentos\\TAP\\Proyectooo\\Recibos\\Recibo.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Taqueria Téllez\n Recibo\n" +
                    "##############################################################################\n" +
                    "Orden    |    Platillo    |    Cantidad    |    Bebida    |    Cantidad    |    Total Parcial"));
            document.add(new Paragraph(contenido));
            document.add(new Paragraph("Total de la Cuenta: $"+totalT+"\n"+
                    "Lo atendió "+nombreem));
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String file = new String("C:\\Users\\franc\\OneDrive\\Documentos\\TAP\\Proyectooo\\Recibos\\Recibo.pdf");
        try{
            Runtime.getRuntime().exec("cmd /c start "+file);
        }catch(IOException e){
            e.printStackTrace();
        }

        objCuentas.ActualizarCuentaAPendiente(objCuentas.getIdCuenta());
        objCuentas.ActualizarTotalCuenta(objCuentas.getIdCuenta(),totalT,"00:00:00");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("¿Pagada?");
        alert.setHeaderText("¿La cuenta ya fue pagada?");
        alert.setContentText("Selecciona si se pagó o no");
        ButtonType buttonTypePag = new ButtonType("Pagada");
        ButtonType buttonTypeNoPag = new ButtonType("Aún No");
        alert.getButtonTypes().setAll(buttonTypePag,buttonTypeNoPag);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypePag) {
            Date fech=new Date();
            SimpleDateFormat objDate=new SimpleDateFormat("yyyy-MM-dd");
            String fecha=objDate.format(fech);
            float totalCajaAnt=objCuentas.getTotalCaja(fecha);
            float totalCajaNuevo=totalCajaAnt+totalT;
            objCuentas.ActualizarCaja(totalCajaNuevo,fecha,objCuentas.getIdMesa());
            objCuentas.ActualizarCuentaAPagada(objCuentas.getIdCuenta());
            int hor,min,seg;
            Calendar calendar = Calendar.getInstance();
            hor =calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
            seg = calendar.get(Calendar.SECOND);
            String horasal= hor+":"+min+":"+seg;
            objCuentas.ActualizarTotalCuenta(objCuentas.getIdCuenta(),totalT,horasal);
        }else if(result.get() == buttonTypeNoPag){

            PagoDespues();


        }

    }

    private void PagoDespues() {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Pagar");
        alert2.setHeaderText("Proceso si ya se pagó");
        alert2.setContentText("Para cambiar el estado a pagada, selecciona\nel botón pagada en la tabla de \ncuentas pendientes");
        ButtonType buttonTypeOK = new ButtonType("Ok");
        alert2.getButtonTypes().setAll(buttonTypeOK);
        Optional<ButtonType> result2 = alert2.showAndWait();
        if (result2.get() == buttonTypeOK) {
            alert2.close();
        }
    }
}
