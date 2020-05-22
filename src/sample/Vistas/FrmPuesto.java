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


public class FrmPuesto extends Stage {
    private PuestosDAO objP;
    private TableView<PuestosDAO> tbvPuestos;
    private VBox vbox;
    private TextField txtnomP, txtSueldo;
    private Button btnGuardar;
    private Scene escena;

    public FrmPuesto(TableView<PuestosDAO> tbvPuestos, PuestosDAO obj){
       if(obj !=null)
           objP=obj;
       else
           objP=new PuestosDAO();

        this.tbvPuestos = tbvPuestos;
        CrearGUI();
        this.setTitle("Gestion de Puestos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        txtnomP = new TextField();
        txtnomP.setText(objP.getNomPuesto());
        txtnomP.setPromptText("Introduce el nombre del Puesto");
        txtSueldo = new TextField();
        txtSueldo.setText(String.valueOf(objP.getSueldo()));
        txtSueldo.setPromptText("Introduce el Sueldo");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarDatos());
        vbox.getChildren().addAll(txtnomP,txtSueldo,btnGuardar);
        escena = new Scene(vbox,250,250);
    }

    private void guardarDatos() {

        objP.setNomPuesto(txtnomP.getText());
        objP.setSueldo(Float.parseFloat(txtSueldo.getText()));

        if(objP.getIdPuesto()>=1)
            objP.updPuesto();
        else
            objP.insPuesto();

        tbvPuestos.setItems(objP.selAllPuestos());
        tbvPuestos.refresh();

        this.close();
    }
}
