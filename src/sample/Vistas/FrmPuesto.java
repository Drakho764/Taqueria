package sample.Vistas;

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


public class FrmPuesto extends Stage {
    private PuestosDAO objP;
    private TableView<PuestosDAO> tbvPuestos;
    private VBox vbox;
    private HBox hbox1,hbox2;
    private TextField txtnomP, txtSueldo;
    Label lblPuesto=new Label("Puesto: ");
    Label lblSueldo=new Label("Sueldo: ");
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
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 12));
        hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(15, 15, 15, 12));
        hbox2 = new HBox();
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setPadding(new Insets(15, 15, 15, 12));
        lblPuesto.setFont(Font.font("Cambria", 20));
        lblSueldo.setFont(Font.font("Cambria", 20));
        txtnomP = new TextField();
        txtnomP.setText(objP.getNomPuesto());
        txtnomP.setPromptText("Introduce el nombre del Puesto");
        txtSueldo = new TextField();
        txtSueldo.setText(String.valueOf(objP.getSueldo()));
        txtSueldo.setPromptText("Introduce el Sueldo");

        btnGuardar = new Button("Guardar");
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        btnGuardar.setOnAction(event -> guardarDatos());
        hbox1.getChildren().addAll(lblPuesto,txtnomP);
        hbox2.getChildren().addAll(lblSueldo,txtSueldo);

        vbox.getChildren().addAll(hbox1,hbox2,btnGuardar);
        escena = new Scene(vbox,300,250);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
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
