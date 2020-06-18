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
import sample.Modelos.MesasDAO;
import sample.Modelos.PuestosDAO;


public class FrmMesa extends Stage {
    private MesasDAO objM;
    private TableView<MesasDAO> tbvMesas;
    private VBox vbox;
    private TextField txtcantPer, txtubic;
    private Button btnGuardar;
    private Scene escena;
    private ComboBox<String> cbxTipo;
    private HBox hbox1,hbox2,hbox3;
    Label lblPersonas=new Label("Cant. Personas: ");
    Label lblUbi=new Label("Ubicacion: ");
    Label lblestado=new Label("Estado: ");

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
        lblPersonas.setFont(Font.font("Cambria", 20));
        lblUbi.setFont(Font.font("Cambria", 20));
        lblestado.setFont(Font.font("Cambria", 20));
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
        txtcantPer = new TextField();
        txtcantPer.setText(String.valueOf(objM.getCantPer()));
        txtcantPer.setPromptText("Introduce cuantas personas caben");
        txtubic = new TextField();
        txtubic.setText(objM.getUbic());
        txtubic.setPromptText("Introduce ubicacion");

        ObservableList<String> items2 = FXCollections.observableArrayList();
        items2.addAll("ocupada","libre");
        cbxTipo = new ComboBox();
        cbxTipo.setItems(items2);
        MesasDAO objm=new MesasDAO();
        objm.setEstado(objM.getEstado());
        objm.getEstado();
        cbxTipo.setValue(objm.estado);


        btnGuardar = new Button("Guardar");
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        btnGuardar.setOnAction(event -> guardarDatos());

        hbox1.getChildren().addAll(lblPersonas,txtcantPer);
        hbox2.getChildren().addAll(lblUbi,txtubic);
        hbox3.getChildren().addAll(lblestado,cbxTipo);
        vbox.getChildren().addAll(hbox1,hbox2,hbox3,btnGuardar);
        escena = new Scene(vbox,250,250);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void guardarDatos() {

        objM.setCantPer(Integer.parseInt(txtcantPer.getText()));
        objM.setUbic(txtubic.getText());
        objM.setEstado(cbxTipo.getValue());

        if(objM.getIdMesa()>=1)
            objM.updMesas();
        else
            objM.insMesas();

        tbvMesas.setItems(objM.selAllMesas());
        tbvMesas.refresh();

        this.close();
    }
}
