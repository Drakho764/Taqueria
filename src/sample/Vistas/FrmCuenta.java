package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Modelos.CuentasDAO;
import sample.Modelos.MesasDAO;

import javax.swing.*;
import java.util.Calendar;

public class FrmCuenta extends Stage {
    private CuentasDAO objC;
    private TableView<CuentasDAO> tbvCuentas;
    private ComboBox<MesasDAO> cbxMesas;
    private ComboBox<String> cbxEstado;
    private TextField txtobser,txtHora;
    private Scene escena;
    private VBox vbox;
    private HBox hbox1,hbox2,hbox3,hbox4;
    private Button btnGuardar;
    int hora, minutos, segundos;
    Label lblMesa=new Label("Id Mesa ");
    Label lblEstado=new Label("Estado: ");
    Label lblObser=new Label("Observación: ");
    Label lblhoraE=new Label("Hora de Entrada: ");
    int num,idEmpleado,band=0;
    String tipoEm;
    Calendar calendario = Calendar.getInstance();
    public FrmCuenta(TableView<CuentasDAO> tbvCuentas, CuentasDAO obj,int num,int idEmpleado,String tipoEm){
        if(obj !=null){
            objC=obj;
        band=0;}
        else {
            objC = new CuentasDAO();
            band=1;
        }
        this.num=num;
        this.tipoEm=tipoEm;
        this.idEmpleado=idEmpleado;
        this.tbvCuentas = tbvCuentas;
        CrearGUI();
        this.setTitle("Gestión de Cuentas");
        this.setScene(escena);
        this.show();

    }

    private void CrearGUI() {
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 12));
        hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(15, 15, 15, 12));
        hbox2 = new HBox();
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setPadding(new Insets(15, 15, 15, 12));
        hbox3 = new HBox();
        hbox3.setAlignment(Pos.CENTER);
        hbox3.setPadding(new Insets(15, 15, 15, 12));
        hbox4 = new HBox();
        hbox4.setAlignment(Pos.CENTER);
        hbox4.setPadding(new Insets(15, 15, 15, 12));
        lblMesa.setFont(Font.font("Cambria", 20));
        lblObser.setFont(Font.font("Cambria", 20));
        lblEstado.setFont(Font.font("Cambria", 20));
        lblhoraE.setFont(Font.font("Cambria", 20));
        txtobser=new TextField();
        txtobser.setPromptText("Agrega una observación si es necesario");
        txtHora=new TextField();
        txtHora.setPromptText("Hora de llegada");
        if(band==0)
            txtHora.setText(objC.getHoraentrada());
        else{
            txtHora.setText(hora+":"+minutos+":"+segundos);}

        txtHora.setEditable(false);
        btnGuardar = new Button("Guardar");
        try {
            btnGuardar.setOnAction(event -> Guardar());
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"Ingresa los datos");
        }
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        cbxMesas = new ComboBox();
        cbxMesas.setItems(new MesasDAO().selAllMesas());
        MesasDAO objPs=new MesasDAO();
        objPs.setIdMesa(objC.getIdMesa());
        objPs.getMesaById();
        cbxMesas.setValue(objPs);

        ObservableList<String> items2 = FXCollections.observableArrayList();
        items2.addAll("Activo","Pendiente", "Pagado");
        cbxEstado = new ComboBox();
        cbxEstado.setItems(items2);
        CuentasDAO obT2 =new CuentasDAO();
        obT2.setEstado(objC.getEstado());
        cbxEstado.setValue(obT2.getEstado());
        hbox1.getChildren().addAll(lblMesa,cbxMesas);
        hbox2.getChildren().addAll(lblEstado,cbxEstado);
        hbox3.getChildren().addAll(lblhoraE,txtHora);
        hbox4.getChildren().addAll(lblObser,txtobser);
        vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,btnGuardar);
        escena = new Scene(vbox,620,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void Guardar() {
        MesasDAO objTemp = cbxMesas.getValue();
        objC.setIdMesa(objTemp.getIdMesa());
        objC.setEstado(String.valueOf(cbxEstado.getValue()));
        objC.setHoraentrada(txtHora.getText());
        objC.setObservacion(txtobser.getText());
        objC.setIdEmpleado(idEmpleado);
        if(objC.getIdCuenta()>=1)
            objC.updCuenta();
        else
            objC.insCuenta();


        switch(num){
            case 1:
                if(tipoEm=="admin") {
                    tbvCuentas.setItems(objC.selAllCuentas());
                    tbvCuentas.refresh();
                    this.close();
                }else if (tipoEm=="mesero"){
                    tbvCuentas.setItems(objC.selAllCuentasPorMesero(idEmpleado));
                    tbvCuentas.refresh();
                    this.close();
                }
                break;
            case 2:
                new FrmWhatCuenta(idEmpleado,1,tipoEm);
                System.out.println(idEmpleado);
                this.close();
                break;
        }

    }
}
