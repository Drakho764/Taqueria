package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.MesasDAO;
import sample.Modelos.PuestosDAO;


public class FrmMesa extends Stage {
    private MesasDAO objM;
    private TableView<MesasDAO> tbvMesas;
    private VBox vbox;
    private TextField txtcantPer, txtubic;
    private Button btnGuardar;
    private Scene escena;

    public FrmMesa(TableView<MesasDAO> tbvMesas,   MesasDAO obj){
       if(obj !=null)
           objM=obj;
       else
           objM=new MesasDAO();

        this.tbvMesas = tbvMesas;
        CrearGUI();
        this.setTitle("Gestion de Mesas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        txtcantPer = new TextField();
        txtcantPer.setText(String.valueOf(objM.getCantPer()));
        txtcantPer.setPromptText("Introduce cuantas personas caben");
        txtubic = new TextField();
        txtubic.setText(objM.getUbic());
        txtubic.setPromptText("Introduce ubicacion");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarDatos());
        vbox.getChildren().addAll(txtcantPer,txtubic,btnGuardar);
        escena = new Scene(vbox,250,250);
    }

    private void guardarDatos() {

        objM.setCantPer(Integer.parseInt(txtcantPer.getText()));
        objM.setUbic(txtubic.getText());

        if(objM.getIdMesa()>=1)
            objM.updMesas();
        else
            objM.insMesas();

        tbvMesas.setItems(objM.selAllMesas());
        tbvMesas.refresh();

        this.close();
    }
}
