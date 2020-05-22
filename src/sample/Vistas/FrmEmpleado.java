package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.PuestosDAO;


public class FrmEmpleado extends Stage {
    private EmpleadosDAO objE;
    private TableView<EmpleadosDAO> tbvEmpleados;
    private VBox vbox;
    private TextField txtnomE, txtemailE, txtedad, txtTel,txtDomi;
    private ComboBox<PuestosDAO> cbxPuesto;
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
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
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

        cbxPuesto = new ComboBox();
        cbxPuesto.setItems(new PuestosDAO().selAllPuestos());
        PuestosDAO objPs=new PuestosDAO();
        objPs.setIdPuesto(objE.getIdPuesto());
        objPs.getPuestoById();
        cbxPuesto.setValue(objPs);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarDatos());
        vbox.getChildren().addAll(txtnomE,txtemailE,txtTel,txtedad,txtDomi,cbxPuesto,btnGuardar);
        escena = new Scene(vbox,250,250);
    }

    private void guardarDatos() {

        objE.setNomEmpleado(txtnomE.getText());
        objE.setEmailEmpleado(txtemailE.getText());
        objE.setTelEmpleado(Integer.valueOf(txtTel.getText()));
        objE.setEdad(Integer.parseInt(txtedad.getText()));
        objE.setDomicilio(txtDomi.getText());

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
