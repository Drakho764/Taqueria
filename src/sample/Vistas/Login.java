package sample.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Main;
import sample.Modelos.EmpleadosDAO;

import javax.swing.*;

public class Login extends Stage {
    private EmpleadosDAO objE;
    private TextField txtnom ;
    PasswordField txtcontraseña;
    private Button btnIniciar,btnCancelar;
    private Scene escena;
    private VBox vbox;
    private HBox hbox1,hBox2;
    Label lblU=new Label("Usuario: ");
    Label lblC=new Label("Contraseña: ");
    int numero;
    public Login(int num){
        objE=new EmpleadosDAO();
        this.numero=num;
        CrearGUI();
        this.setTitle("Iniciar Sesión");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        lblC.setFont(Font.font("Cambria", 20));
        lblU.setFont(Font.font("Cambria", 20));
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 12));
        hbox1 = new HBox();
        hBox2 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(15, 15, 15, 12));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(15, 15, 15, 12));
        txtnom=new TextField();
        txtnom.setPromptText("Nombre de usuario");
        txtcontraseña = new PasswordField();
        txtcontraseña.setPromptText("Contraseña");

        btnIniciar = new Button("Iniciar Sesión");
        try {
            btnIniciar.setOnAction(event -> Iniciar());
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"Ingresa los datos");
        }
        btnIniciar.setMinSize(120, 60);
        btnIniciar.setMaxSize(148, 74);

        hbox1.getChildren().addAll(lblU,txtnom);
        hBox2.getChildren().addAll(lblC,txtcontraseña);
        vbox.getChildren().addAll(hbox1,hBox2,btnIniciar);
        escena = new Scene(vbox);
        escena.getStylesheets().add("sample/Estilos/estiloLogin.css");
    }

    private void Iniciar() {
        objE.setUsuario(txtnom.getText());
        objE.setContraseña(txtcontraseña.getText());
        switch (numero){
            case 1:
                objE.IniciarAdmin("admin",objE,this);
                break;
            case 2:
                objE.IniciarMesero("mesero",objE,this);
                break;
        }
    }

}
