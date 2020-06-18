package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.Main;
import sample.Vistas.Login;
import sample.Vistas.PrincipalAdmin;
import sample.Vistas.PrincipalMesero;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class EmpleadosDAO {
    private int idEmpleado;
    private String nomEmpleado;
    private String emailEmpleado;
    private String telEmpleado;
    private int edad;
    private String domicilio;
    private int idPuesto;
    private String usuario;
    private String contraseña;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelEmpleado() {
        return telEmpleado;
    }

    public void setTelEmpleado(String telEmpleado) {
        this.telEmpleado = telEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getNomEmpleado() {
        return nomEmpleado;
    }
    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }
    public String getEmailEmpleado() {
        return emailEmpleado;
    }
    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public int getIdPuesto() {
        return idPuesto;
    }
    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    private Connection con;
    public EmpleadosDAO(){ con = Conexion.con; }
    public void insEmpleado(){

        String query = "insert into Empleado " +
                "(nomEmpleado,email,telefono,edad,domicilio,idPuesto,usuario,contraseña,tipo) " +
                "values('"+nomEmpleado+"','"+emailEmpleado+"',"+telEmpleado+","+edad+",'"+domicilio+"',"+idPuesto+"" +
                ",'"+usuario+"','"+contraseña+"','"+tipo+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updEmpleado(){
        String query = "update Empleado set nomEmpleado='"+nomEmpleado+"'" +
                ",email='"+emailEmpleado+"',telefono="+telEmpleado+",edad="+edad+"," +
                "domicilio='"+domicilio+"',idPuesto="+idPuesto+",usuario='"+usuario+"', contraseña='"+
                contraseña+"', tipo='"+tipo+"' where " +
                "idEmpleado="+idEmpleado;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }

    public void delEmpleado(){
        String query = "delete from Empleado where idEmpleado="+idEmpleado;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    public ObservableList<EmpleadosDAO> selAllEmpleados(){

        ObservableList<EmpleadosDAO> listaE = FXCollections.observableArrayList();
        EmpleadosDAO objE = null;
        String query = "select * from Empleado order by nomEmpleado";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objE = new EmpleadosDAO();
                objE.setIdEmpleado(res.getInt("idEmpleado"));
                objE.setNomEmpleado(res.getString("nomEmpleado"));
                objE.setEmailEmpleado(res.getString("email"));
                objE.setTelEmpleado(res.getString("telefono"));
                objE.setEdad(res.getInt("edad"));
                objE.setDomicilio(res.getString("domicilio"));
                objE.setIdPuesto(res.getInt("idPuesto"));
                objE.setUsuario(res.getString("usuario"));
                objE.setContraseña(res.getString("contraseña"));
                objE.setTipo(res.getString("tipo"));
                listaE.add(objE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaE;
    }
    public String getNombrebyId(int idEm){
        String nom="";
            String query ="SELECT nomEmpleado FROM Empleado WHERE (idEmpleado ="+idEm+");";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                nom=res.getString("nomEmpleado");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return nom;
    }
   @Override
   public String toString() {
       return tipo;
   }
    public void IniciarAdmin(String tipo, EmpleadosDAO obj, Login escena){
        EmpleadosDAO objE= obj;
        String us="", cont="",tip="";
        String query = "select idEmpleado,usuario, contraseña, tipo from Empleado where usuario='"+usuario+"'";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                us=res.getString("usuario");
                cont=res.getString("contraseña");
                tip=res.getString("tipo");
                idEmpleado=res.getInt("idEmpleado");
            }
            System.out.println(us +" "+cont+" "+tip);
            System.out.println(tipo);
            System.out.println(objE.getUsuario()+" "+objE.getContraseña());
            if((us.equals(objE.getUsuario())) && (cont.equals(objE.getContraseña()))&&(tip.equals(tipo))){
                new PrincipalAdmin(idEmpleado,"admin");
                escena.close();

            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Datos Incorrectos");
                alert.setHeaderText("Usuario o contraseña incorrecto 1 ");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Datos Incorrectos");
            alert.setHeaderText("Usuario o contraseña incorrecto 2 ");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    public void IniciarMesero(String tipo, EmpleadosDAO obj, Login escena){
        EmpleadosDAO objE= obj;
        String us="", cont="",tip="";
        int idEm=0;
        String query = "select idEmpleado,usuario, contraseña, tipo from Empleado where usuario='"+usuario+"'";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                us=res.getString("usuario");
                cont=res.getString("contraseña");
                tip=res.getString("tipo");
                idEm=res.getInt("idEmpleado");
            }
            System.out.println(us +" "+cont+" "+tip);
            System.out.println(idEm);
            if((us.equals(objE.getUsuario())) && (cont.equals(objE.getContraseña()))&&(tip.equals(tipo))){
                new PrincipalMesero(idEm,"mesero");
                escena.close();

            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Datos Incorrectos");
                alert.setHeaderText("Usuario o contraseña incorrecto 1 ");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Datos Incorrectos");
            alert.setHeaderText("Usuario o contraseña incorrecto 2 ");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }



}



