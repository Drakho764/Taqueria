package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.Conexion;
import sample.Vistas.CRUDEmpleados;
import sample.Vistas.CRUDMesas;
import sample.Vistas.CRUDPuestos;
import sample.Vistas.FrmEmpleado;

public class Main extends Application {
    MenuBar mnbTaqueria;
    Menu menConsultar;
    Menu menSalir;
    MenuItem mitCEmpleado,mitCMesa,mitCPuesto;
    MenuItem mitBye;
    Scene escena;
    BorderPane brpPrincipal;
    HBox hBox;
    VBox vBox;
    Button btnOrden,btnPagar;
    public Main() {
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        brpPrincipal = new BorderPane();
        mnbTaqueria = new MenuBar();
        brpPrincipal.setTop(mnbTaqueria);
        menConsultar = new Menu("Consultas");
        menSalir = new Menu("Salir");
        mitCEmpleado = new MenuItem("Empleados");
        mitCEmpleado.setOnAction(event -> OpcionMenu(1));
        mitCMesa=new MenuItem("Mesas");
        mitCMesa.setOnAction(event-> OpcionMenu(2));
        mitCPuesto=new MenuItem("Puestos");
        mitCPuesto.setOnAction(event-> OpcionMenu(3));
        mitBye = new MenuItem("Bye");
        mitBye.setOnAction((event) -> OpcionMenu(4));

        vBox =new VBox();
        hBox=new HBox();
        btnOrden=new Button("Generar Nueva Orden");
        btnPagar=new Button("Pagar una orden");
        //btnOrden.addEventHandler(MouseEvent.MOUSE_CLICKED,);
       // btnPagar.addEventHandler(MouseEvent.MOUSE_CLICKED,);

        hBox.getChildren().addAll(btnOrden,btnPagar);


        menConsultar.getItems().addAll(mitCEmpleado,mitCMesa,mitCPuesto);
        menSalir.getItems().add(mitBye);
        mnbTaqueria.getMenus().addAll(menConsultar, menSalir);
        vBox.getChildren().addAll(mnbTaqueria,hBox);
        escena = new Scene(vBox, 300.0D, 275.0D);
        Conexion.CrearConexion(); //creamos conexion a bd
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Taqueria Chida");
        primaryStage.setScene(this.escena);
        primaryStage.show();
    }

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new CRUDEmpleados();
                break;
            case 2:
                new CRUDMesas();
                break;
            case 3:
                new CRUDPuestos();
                break;
            case 4:
               // new PistaAtletismo();
                break;
            case 20:
                System.exit(0);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
