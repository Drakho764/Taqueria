package sample.Vistas;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Modelos.CuentasDAO;
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.MesasDAO;

import javax.swing.*;

public class FrmWhatCuenta extends Stage {
    private CuentasDAO objO;
    private ComboBox<CuentasDAO> cbxCuentas;
    private Button btnOrdenar;
    private Scene escena;
    private VBox vbox;
    private HBox hbox1;
    int idEmpleado;
    int numero;
    String tipoEm;
    Label lblU=new Label("Cuentas: ");
    public FrmWhatCuenta(int idEmpleado,int num,String tipoEm){
        objO=new CuentasDAO();
        numero=num;
        this.tipoEm=tipoEm;
        this.idEmpleado=idEmpleado;
        CrearGUI();

        this.setTitle("¿Cuál Cuenta?");
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
        lblU.setFont(Font.font("Cambria", 20));
        if(numero==1) {
            btnOrdenar = new Button("Hacer Orden");
        }else if(numero==2){
            btnOrdenar = new Button("Buscar");
        }else if(numero==3){
            btnOrdenar = new Button("Ir a Pago");
        }

        try {
            btnOrdenar.setOnAction(event -> Ordenar());
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"Ingresa los datos");
        }
        btnOrdenar.setMinSize(120, 60);
        btnOrdenar.setMaxSize(148, 74);
        System.out.println(numero);
        cbxCuentas = new ComboBox();
        if(numero==1) {
            if (tipoEm == "mesero"){
                System.out.println("Entró num 1 mesero id"+idEmpleado);
                cbxCuentas.setItems(new CuentasDAO().selAllCuentasActivasPorMesero(idEmpleado));
            }else if(tipoEm=="admin"){
                cbxCuentas.setItems(new CuentasDAO().selAllCuentasActivas());
            }
        } else if(numero==2){
            if(tipoEm=="mesero"){
                System.out.println("Entró num 2 mesero id"+idEmpleado);
                cbxCuentas.setItems(new CuentasDAO().selAllCuentasPorMesero(idEmpleado));
            }else if(tipoEm=="admin"){
                cbxCuentas.setItems(new CuentasDAO().selAllCuentas());
            }

        } else if(numero==3){
            if(tipoEm=="mesero"){
                System.out.println("Entró num 3 mesero id"+idEmpleado);
                cbxCuentas.setItems(new CuentasDAO().selAllCuentasActivasPorMesero(idEmpleado));
            }else if(tipoEm=="admin"){
                cbxCuentas.setItems(new CuentasDAO().selAllCuentasActivas());
            }

        }
        hbox1.getChildren().addAll(lblU,cbxCuentas);
        vbox.getChildren().addAll(hbox1,btnOrdenar);
        escena = new Scene(vbox,680,300);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");

    }

    private void Ordenar() {
        if(numero==1){
            CuentasDAO objT=cbxCuentas.getValue();
            new FrmOrden(objT,idEmpleado);
            this.close();
        }else if(numero==2){
            CuentasDAO objT=cbxCuentas.getValue();
            new CRUDOrden(objT,tipoEm,idEmpleado);
            this.close();
        }else if(numero==3){
            CuentasDAO objT=cbxCuentas.getValue();
            new CRUDPago(objT);
            this.close();
        }

    }
}
