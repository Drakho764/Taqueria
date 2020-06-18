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
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.PuestosDAO;


public class FrmEmpleado extends Stage {
    private EmpleadosDAO objE;
    private TableView<EmpleadosDAO> tbvEmpleados;
    private VBox vbox;
    private HBox hbox1,hbox2,hbox3,hbox4,hbox5,hbox6,hbox7,hbox8,hbox9;
    Label lblNombre=new Label("Nombre: ");
    Label lblEmail=new Label("Email: ");
    Label lblEdad=new Label("Edad: ");
    Label lblTelefono=new Label("Telefono: ");
    Label lblDomicilio=new Label("Domicilio: ");
    Label lblPuesto=new Label("Puesto: ");
    Label lblUsuario=new Label("Usuario: ");
    Label lblContraseña=new Label("Contraseña: ");
    Label lblTipo=new Label("Tipo de cuenta: ");
    private TextField txtnomE, txtemailE, txtedad, txtTel,txtDomi,txtUsuario,txtContraseña;
    private ComboBox<PuestosDAO> cbxPuesto;
    private ComboBox<String> cbxTipo;
    private Button btnGuardar;
    private Scene escena;

    public FrmEmpleado(TableView<EmpleadosDAO> tbvEmpleados, EmpleadosDAO obj){
       if(obj !=null)
           objE=obj;
       else
           objE=new EmpleadosDAO();

        this.tbvEmpleados = tbvEmpleados;
        CrearGUI();
        this.setTitle("Gestion de Empleados");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        lblEmail.setFont(Font.font("Cambria", 20));
        lblContraseña.setFont(Font.font("Cambria", 20));
        lblDomicilio.setFont(Font.font("Cambria", 20));
        lblEdad.setFont(Font.font("Cambria", 20));
        lblNombre.setFont(Font.font("Cambria", 20));
        lblPuesto.setFont(Font.font("Cambria", 20));
        lblTelefono.setFont(Font.font("Cambria", 20));
        lblTipo.setFont(Font.font("Cambria", 20));
        lblUsuario.setFont(Font.font("Cambria", 20));
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
        hbox5 = new HBox();
        hbox5.setAlignment(Pos.CENTER);
        hbox5.setPadding(new Insets(15, 15, 15, 12));
        hbox6 = new HBox();
        hbox6.setAlignment(Pos.CENTER);
        hbox6.setPadding(new Insets(15, 15, 15, 12));
        hbox7 = new HBox();
        hbox7.setAlignment(Pos.CENTER);
        hbox7.setPadding(new Insets(15, 15, 15, 12));
        hbox8 = new HBox();
        hbox8.setAlignment(Pos.CENTER);
        hbox8.setPadding(new Insets(15, 15, 15, 12));
        hbox9 = new HBox();
        hbox9.setAlignment(Pos.CENTER);
        hbox9.setPadding(new Insets(15, 15, 15, 12));
        txtnomE = new TextField();
        txtnomE.setText(objE.getNomEmpleado());
        txtnomE.setPromptText("Introduce el nombre del empleado");
        txtemailE = new TextField();
        txtemailE.setText(objE.getEmailEmpleado());
        txtemailE.setPromptText("Introduce el email");
        txtTel = new TextField();
        txtTel.setText(objE.getTelEmpleado()+"");
        txtTel.setPromptText("Introduce el telefono");
        txtedad = new TextField();
        txtedad.setText(objE.getEdad()+"");
        txtedad.setPromptText("Introduce la edad");
        txtDomi = new TextField();
        txtDomi.setText(objE.getDomicilio());
        txtDomi.setPromptText("Introduce el domcilio");
        txtUsuario = new TextField();
        txtUsuario.setText(objE.getUsuario());
        txtUsuario.setPromptText("Introduce Usuario");
        txtContraseña = new TextField();
        txtContraseña.setText(objE.getContraseña());
        txtContraseña.setPromptText("Introduce Contraseña");
        cbxPuesto = new ComboBox();
        cbxPuesto.setItems(new PuestosDAO().selAllPuestos());
        PuestosDAO objPs=new PuestosDAO();
        objPs.setIdPuesto(objE.getIdPuesto());
        objPs.getPuestoById();
        cbxPuesto.setValue(objPs);

        ObservableList<String> items2 = FXCollections.observableArrayList();
        items2.addAll("admin","mesero");
        cbxTipo = new ComboBox();
        cbxTipo.setItems(items2);
        EmpleadosDAO obj=new EmpleadosDAO();
        obj.setTipo(objE.getTipo());
        obj.getTipo();
        cbxTipo.setValue(String.valueOf(obj));
        btnGuardar = new Button("Guardar");
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        btnGuardar.setOnAction(event -> guardarDatos());

        hbox1.getChildren().addAll(lblNombre,txtnomE);
        hbox2.getChildren().addAll(lblEmail,txtemailE);
        hbox3.getChildren().addAll(lblTelefono,txtTel);
        hbox4.getChildren().addAll(lblEdad,txtedad);
        hbox5.getChildren().addAll(lblDomicilio,txtDomi);
        hbox6.getChildren().addAll(lblPuesto,cbxPuesto);
        hbox7.getChildren().addAll(lblUsuario,txtUsuario);
        hbox8.getChildren().addAll(lblContraseña,txtContraseña);
        hbox9.getChildren().addAll(lblTipo,cbxTipo);


        vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,hbox5,hbox6,hbox7,hbox8,hbox9,btnGuardar);
        escena = new Scene(vbox);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void guardarDatos() {

        objE.setNomEmpleado(txtnomE.getText());
        objE.setEmailEmpleado(txtemailE.getText());
        objE.setTelEmpleado(txtTel.getText());
        objE.setEdad(Integer.parseInt(txtedad.getText()));
        objE.setDomicilio(txtDomi.getText());
        objE.setUsuario(txtUsuario.getText());
        objE.setContraseña(txtContraseña.getText());
        objE.setTipo(String.valueOf(cbxTipo.getValue()));
        PuestosDAO objTemp = cbxPuesto.getValue();
        objE.setIdPuesto(objTemp.getIdPuesto());
        if(objE.getIdEmpleado()>=1)
            objE.updEmpleado();
        else
            objE.insEmpleado();

        tbvEmpleados.setItems(objE.selAllEmpleados());
        tbvEmpleados.refresh();

        this.close();
    }
}
