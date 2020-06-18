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
import sample.Modelos.MesasDAO;
import sample.Modelos.PlatillosDAO;


public class FrmPlatillo extends Stage {
    private PlatillosDAO objM;
    private TableView<PlatillosDAO> tbvPlat;
    private VBox vbox;
    private TextField txtnomP, txtdesc,txtprecio;
    private Button btnGuardar;
    private Scene escena;
    private ComboBox<String> cbxEstado;
    private HBox hbox1,hbox2,hbox3,hbox4;
    Label lblNombre=new Label("Nombre: ");
    Label lblDesc=new Label("Descripción: ");
    Label lblPrecio=new Label("Precio: ");
    Label lblEstado=new Label("Estado: ");

    public FrmPlatillo(TableView<PlatillosDAO> tbvPlat, PlatillosDAO obj){
       if(obj !=null)
           objM=obj;
       else
           objM=new PlatillosDAO();

        this.tbvPlat = tbvPlat;
        CrearGUI();
        this.setTitle("Gestion de Platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        lblNombre.setFont(Font.font("Cambria", 20));
        lblDesc.setFont(Font.font("Cambria", 20));
        lblPrecio.setFont(Font.font("Cambria", 20));
        lblEstado.setFont(Font.font("Cambria", 20));
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
        txtnomP = new TextField();
        txtnomP.setText(objM.getNomPlatillo());
        txtnomP.setPromptText("Nombre del platillo");
        txtdesc = new TextField();
        txtdesc.setText(objM.getDesc());
        txtdesc.setPromptText("Introduce Descripción");
        txtprecio = new TextField();
        txtprecio.setText(objM.getPrecio()+"");
        txtprecio.setPromptText("Introduce Descripción");
        ObservableList<String> items2 = FXCollections.observableArrayList();
        items2.addAll("Disponible","Agotado");
        cbxEstado = new ComboBox();
        cbxEstado.setItems(items2);
        PlatillosDAO objm=new PlatillosDAO();
        objm.setEstado(objM.getEstado());
        objm.getPlatilloById();
        cbxEstado.setValue(String.valueOf(objm.estado));


        btnGuardar = new Button("Guardar");
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        btnGuardar.setOnAction(event -> guardarDatos());

        hbox1.getChildren().addAll(lblNombre,txtnomP);
        hbox2.getChildren().addAll(lblDesc,txtdesc);
        hbox3.getChildren().addAll(lblPrecio,txtprecio);
        hbox4.getChildren().addAll(lblEstado,cbxEstado);
        vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,btnGuardar);
        escena = new Scene(vbox,400,470);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void guardarDatos() {

        objM.setNomPlatillo(txtnomP.getText());
        objM.setDesc(txtdesc.getText());
        objM.setPrecio(Float.parseFloat(txtprecio.getText()));
        objM.setEstado(cbxEstado.getValue());

        if(objM.getIdPlatillo()>=1)
            objM.updPlatillo();
        else
            objM.insPlatillos();

        tbvPlat.setItems(objM.selAllPlatillos());
        tbvPlat.refresh();

        this.close();
    }
}
