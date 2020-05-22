package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {
    private int idEmpleado;
    private String nomEmpleado;
    private String emailEmpleado;
    private int telEmpleado;
    private int edad;
    private String domicilio;
    private int idPuesto;

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
    public int getTelEmpleado() {
        return telEmpleado;
    }
    public void setTelEmpleado(int telEmpleado) {
        this.telEmpleado = telEmpleado;
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
                "(nomEmpleado,email,telefono,edad,domicilio,idPuesto) " +
                "values('"+nomEmpleado+"','"+emailEmpleado+"',"+telEmpleado+","+edad+",'"+domicilio+"',"+idPuesto+")";
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
                "domicilio='"+domicilio+"',idPuesto="+idPuesto+" where " +
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
                objE.setTelEmpleado(res.getInt("telefono"));
                objE.setEdad(res.getInt("edad"));
                objE.setDomicilio(res.getString("domicilio"));
                objE.setIdPuesto(res.getInt("idPuesto"));
                listaE.add(objE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaE;
    }

    public void selByIdEmpleado(){}
}



