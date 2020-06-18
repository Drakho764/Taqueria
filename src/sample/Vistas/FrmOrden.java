package sample.Vistas;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Main;
import sample.Modelos.BebidasDAO;
import sample.Modelos.CuentasDAO;
import sample.Modelos.OrdenDAO;
import sample.Modelos.PlatillosDAO;

import javax.swing.*;
import java.util.Optional;

public class FrmOrden extends Stage {
    OrdenDAO objOrden =new OrdenDAO();
    CuentasDAO obC;
    BebidasDAO objB;
    PlatillosDAO objPla;
    private HBox hbox1,hbox2,hbox3,hbox4,hbox5,hbox6;
    private VBox vbox1,vbox2,vbox3;
    private ComboBox<PlatillosDAO> cbxPlat;
    private ComboBox<BebidasDAO> cbxBeb;
    private TextField txtobser,txtTotParcial,txtCantBeb,txtCantPlat;
    int idEmpleado;
    Label lblCue=new Label("Cuenta: ");
    Label lblCue1=new Label();
    Label lblMesa=new Label("Mesa: ");
    Label lblMesa1=new Label();
    Label lblidEmpleado=new Label("Empleado No. ");
    Label lblidEmpleado1=new Label();
    Label lblPlat=new Label("Platillo :");
    Label lblCantPla=new Label("Cantidad:");
    Label lblBebida=new Label("Bebida : ");
    Label lblCantbeb=new Label("Cantidad: ");
    Label lblObser=new Label("Observación: ");
    Label lblTotP=new Label("Total Parcial: ");
    private Scene escena;
    private float precio1=0;
    Button btnGuardar;
    public FrmOrden(CuentasDAO objC,int idEmpleado){
        this.obC=objC;
        this.idEmpleado=idEmpleado;
        CrearGUI();
        this.setTitle("Orden");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        lblCue.setFont(Font.font("Cambria", 20));
        lblCue1.setFont(Font.font("Cambria", 20));
        lblMesa.setFont(Font.font("Cambria", 20));
        lblMesa1.setFont(Font.font("Cambria", 20));
        lblidEmpleado.setFont(Font.font("Cambria", 20));
        lblidEmpleado1.setFont(Font.font("Cambria", 20));
        lblPlat.setFont(Font.font("Cambria", 20));
        lblCantPla.setFont(Font.font("Cambria", 20));
        lblBebida.setFont(Font.font("Cambria", 20));
        lblCantbeb.setFont(Font.font("Cambria", 20));
        lblObser.setFont(Font.font("Cambria", 20));
        lblTotP.setFont(Font.font("Cambria", 20));
        vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(15, 15, 15, 12));
        vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(15, 15, 15, 12));
        vbox3 = new VBox();
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(15, 15, 15, 12));
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
        lblCue1.setText(String.valueOf(obC.getIdCuenta()));
        lblMesa1.setText(String.valueOf(obC.getIdMesa()));
        lblidEmpleado1.setText(idEmpleado+"");

        cbxPlat = new ComboBox();
        cbxPlat.setItems(new PlatillosDAO().selAllPlatillos());
        cbxBeb = new ComboBox();
        cbxBeb.setItems(new BebidasDAO().selAllBebidas());
        txtobser=new TextField();
        txtobser.setPromptText("Agrega una observación si es necesario");
        txtCantPlat=new TextField();
        txtCantPlat.setPromptText("¿Cuantos platillos son?");
        txtCantPlat.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                PlatillosDAO obPlat =cbxPlat.getValue();
                Float precio=obPlat.obtenerPrecio(obPlat.getIdPlatillo());
                System.out.println("Precioooo "+precio);
                String estado= obPlat.obtenerEstadoo(obPlat.getIdPlatillo());
                System.out.println("ESTADOOOO "+estado);
                if(estado=="Agotado"){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Platillo Agotado");
                    alert.setHeaderText("No hay suficientes Platillos");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.CANCEL){
                        alert.close();
                    }else{
                        alert.close();

                    }
                }else{
                    Float precioT=precio*Float.parseFloat(txtCantPlat.getText());
                    precio1+=precioT;
                    System.out.println(precioT);
                }

            }});
        txtTotParcial=new TextField();
        txtCantBeb=new TextField();
        txtCantBeb.setPromptText("¿Cuantas Bebidas son?");
        txtCantBeb.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                BebidasDAO obBeb =cbxBeb.getValue();
                Float precio=obBeb.obtenerPrecio(obBeb.getIdbebida());
                int cant= obBeb.obtenerCantidad(obBeb.getIdbebida());
                if(cant<Integer.parseInt(txtCantBeb.getText())){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cantidad insuficiente de Bebidas");
                    alert.setHeaderText("No suficientes Bebidas");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.CANCEL){
                        alert.close();
                    }else{
                        alert.close();
                    }
                }else{
                    Float precioT=precio*Float.parseFloat(txtCantBeb.getText());
                    precio1+=precioT;
                    System.out.println(precioT);
                }
                Float pree=precio1;
                txtTotParcial.setText(pree+"");
                txtTotParcial.setEditable(false);
            }});
        btnGuardar = new Button("Confirmar Orden");
        try {
            btnGuardar.setOnAction(event -> Guardar());
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"Ingresa datos correctos");
        }
        btnGuardar.setMinSize(120, 60);
        btnGuardar.setMaxSize(148, 74);

        hbox1.getChildren().addAll(lblCue,lblCue1,lblMesa,lblMesa1);
        vbox1.getChildren().addAll(lblPlat,cbxPlat,lblBebida,cbxBeb);
        vbox2.getChildren().addAll(lblCantPla,txtCantPlat,lblCantbeb,txtCantBeb);
        hbox2.getChildren().addAll(vbox1,vbox2);
        hbox3.getChildren().addAll(lblObser,txtobser);
        hbox4.getChildren().addAll(lblTotP,txtTotParcial);
        hbox5.getChildren().addAll(lblidEmpleado,lblidEmpleado1);
        vbox3.getChildren().addAll(hbox1,hbox5,hbox2,hbox3,hbox4,btnGuardar);

        escena = new Scene(vbox3,620,500);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void Guardar() {
        BebidasDAO obBeb =cbxBeb.getValue();
        int cant= obBeb.obtenerCantidad(obBeb.getIdbebida());
        objOrden.setIdcuenta(Integer.parseInt(lblCue1.getText()));
        PlatillosDAO obPlat =cbxPlat.getValue();
        objOrden.setIdplatillo(obPlat.getIdPlatillo());
        objOrden.setIdbebida(obBeb.getIdbebida());
        objOrden.setIdempleado(Integer.parseInt(lblidEmpleado1.getText()));
        objOrden.setIdmesa(Integer.parseInt(lblMesa1.getText()));
        objOrden.setCantBeb(Integer.parseInt(txtCantBeb.getText()));
        objOrden.setCantPla(Integer.parseInt(txtCantPlat.getText()));
        objOrden.setObs(txtobser.getText());
        objOrden.setPrecio(Float.parseFloat(txtTotParcial.getText()));
        objOrden.insOrden(cant);
        this.close();
    }
}
