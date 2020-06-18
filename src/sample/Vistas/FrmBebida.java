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
import sample.Modelos.BebidasDAO;
import sample.Modelos.MesasDAO;


public class FrmBebida extends Stage {
    private BebidasDAO objM;
    private TableView<BebidasDAO> tbvBebidas;
    private VBox vbox;
    private TextField txtnom, txtdesc,txtPrecio,txtCant;
    private Button btnGuardar;
    private Scene escena;
    private HBox hbox1,hbox2,hbox3,hbox4;
    Label lblNombre=new Label("Nombre: ");
    Label lblDesc=new Label("Descripcion: ");
    Label lblPrecio=new Label("Precio: ");
    Label lblCant=new Label("Cantidad: ");

    public FrmBebida(TableView<BebidasDAO> tbvBebidas, BebidasDAO obj){
       if(obj !=null)
           objM=obj;
       else
           objM=new BebidasDAO();

        this.tbvBebidas = tbvBebidas;
        CrearGUI();
        this.setTitle("Gestion de Bebidas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        lblNombre.setFont(Font.font("Cambria", 20));
        lblDesc.setFont(Font.font("Cambria", 20));
        lblPrecio.setFont(Font.font("Cambria", 20));
        lblCant.setFont(Font.font("Cambria", 20));
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
        txtnom = new TextField();
        txtnom.setText(String.valueOf(objM.getNombeb()));
        txtnom.setPromptText("Nombre de la bebida");
        txtdesc = new TextField();
        txtdesc.setText(objM.getDesc());
        txtdesc.setPromptText("Introduce Descripcion");
        txtPrecio = new TextField();
        txtPrecio.setText(objM.getPrecio()+"");
        txtPrecio.setPromptText("Introduce Precio");
        txtCant = new TextField();
        txtCant.setText(objM.getCantidad()+"");
        txtCant.setPromptText("Introduce Cantidad");

        btnGuardar = new Button("Guardar");
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);
        btnGuardar.setOnAction(event -> guardarDatos());

        hbox1.getChildren().addAll(lblNombre,txtnom);
        hbox2.getChildren().addAll(lblDesc,txtdesc);
        hbox3.getChildren().addAll(lblPrecio,txtPrecio);
        hbox4.getChildren().addAll(lblCant,txtCant);
        vbox.getChildren().addAll(hbox1,hbox2,hbox4,hbox3,btnGuardar);
        escena = new Scene(vbox,400,470);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void guardarDatos() {

       objM.setNombeb(txtnom.getText());
       objM.setDesc(txtdesc.getText());
       objM.setPrecio(Float.parseFloat(txtPrecio.getText()));
       objM.setCantidad(Integer.parseInt(txtCant.getText()));

        if(objM.getIdbebida()>=1)
            objM.updBebida();
        else
            objM.insBebidas();

        tbvBebidas.setItems(objM.selAllBebidas());
        tbvBebidas.refresh();

        this.close();
    }
}
