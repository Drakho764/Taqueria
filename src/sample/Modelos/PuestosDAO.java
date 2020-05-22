package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PuestosDAO {
    private int idPuesto;
    private String nomPuesto;
    private float sueldo;

    public int getIdPuesto() {
        return idPuesto;
    }
    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }
    public String getNomPuesto() {
        return nomPuesto;
    }
    public void setNomPuesto(String nomPuesto) {
        this.nomPuesto = nomPuesto;
    }
    public float getSueldo() {
        return sueldo;
    }
    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }
    private Connection con;
    public PuestosDAO(){ con = Conexion.con; }
    private PuestosDAO objP = null;
    public void insPuesto(){

        String query = "insert into Puesto " +
                "(nomPuesto,Sueldo) " +
                "values('"+nomPuesto+"',"+sueldo+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updPuesto(){
        String query = "update Puesto set nomPuesto='"+nomPuesto+"'" +
                ",Sueldo="+sueldo+" where " + "idPuesto="+idPuesto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public ObservableList<PuestosDAO> selAllPuestos(){

        ObservableList<PuestosDAO> listPs = FXCollections.observableArrayList();
        String query = "SELECT * FROM Puesto ORDER BY nomPuesto";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new PuestosDAO();
                objP.setIdPuesto(res.getInt("idPuesto"));
                objP.setNomPuesto(res.getString("nomPuesto"));
                objP.setSueldo(res.getFloat("sueldo"));
                listPs.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listPs;
    }
    public void delPuesto(){
        String query = "delete from Puesto where idPuesto="+idPuesto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }
    public void getPuestoById(){
        String query ="SELECT * FROM Puesto WHERE idPuesto ="+idPuesto;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
           if(res.next()){
               nomPuesto=res.getString("nomPuesto");
               sueldo=res.getFloat("sueldo");
           }
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return nomPuesto+ " (" + sueldo + ")";
    }


}


