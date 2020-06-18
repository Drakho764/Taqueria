package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.Modelos.Conexion;
import sample.Vistas.*;


public class Main extends Application {
    Scene escena;
    HBox hBox;
    VBox vBox,vBAd,vBMe;
    Button btnAdmin,btnMesero;
    ImageView imageView = new ImageView("sample/Imagenes/Admin.png");
    ImageView imageView2 = new ImageView("sample/Imagenes/mesero.png");
    Label lblA=new Label("Iniciar \ncomo Administrador");
    Label lblM=new Label("Iniciar \ncomo Mesero");
    public Main() {
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        lblA.setFont(Font.font("Cambria", 20));
        lblM.setFont(Font.font("Cambria", 20));
        lblA.setTextAlignment(TextAlignment.CENTER);
        lblM.setTextAlignment(TextAlignment.CENTER);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        vBox =new VBox();
        vBAd =new VBox();
        vBMe =new VBox();
        hBox=new HBox();
        btnAdmin= new Button();
        btnAdmin.setGraphic(imageView);
        btnAdmin.setMinSize(148, 148);
        btnAdmin.setMaxSize(148, 148);
        btnAdmin.setOnAction(event-> OpcionMenu(1));

        btnMesero= new Button();
        btnMesero.setGraphic(imageView2);
        btnMesero.setMinSize(148, 148);
        btnMesero.setMaxSize(148, 148);
        btnMesero.setOnAction(event-> OpcionMenu(2));

        vBAd.getChildren().addAll(btnAdmin,lblA);
        vBMe.getChildren().addAll(btnMesero,lblM);
        vBAd.setAlignment(Pos.CENTER);
        vBMe.setAlignment(Pos.CENTER);
        vBAd.setPadding(new Insets(15, 15, 15, 12));
        vBMe.setPadding(new Insets(15, 15, 15, 12));
        hBox.getChildren().addAll(vBAd,vBMe);
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox);
        vBox.setAlignment(Pos.CENTER);
        escena = new Scene(vBox, 300.0D, 275.0D);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
        Conexion.CrearConexion(); //creamos conexion a bd
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Login Taqueria");
        primaryStage.setScene(this.escena);
        primaryStage.show();
    }

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new Login(1);
                break;
            case 2:
                new Login(2);
                break;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
