package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlatillosDAO {
    private int idPlatillo;
    private String nomPlatillo;
    private String desc;
    private float precio;
   public String estado;

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNomPlatillo() {
        return nomPlatillo;
    }

    public void setNomPlatillo(String nomPlatillo) {
        this.nomPlatillo = nomPlatillo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private Connection con;
    public PlatillosDAO(){ con = Conexion.con; }
    private PlatillosDAO objM = null;
    public void insPlatillos(){

        String query = "insert into Platillo" +
                "(nomPlatillo,descripcion,precio,estado) " +
                "values('"+nomPlatillo+"','"+desc+"',"+precio+",'"+estado+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updPlatillo(){
        String query = "update Platillo set nomPlatillo='"+nomPlatillo+"'," +
                "descripcion='"+desc+"',precio="+precio+",estado='"+estado+"' where idPlatillo="+idPlatillo;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public ObservableList<PlatillosDAO> selAllPlatillos(){

        ObservableList<PlatillosDAO> listMs = FXCollections.observableArrayList();
        String query = "SELECT * FROM Platillo ORDER BY nomPlatillo";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new PlatillosDAO();
                objM.setIdPlatillo(res.getInt("idPlatillo"));
                objM.setNomPlatillo(res.getString("nomPlatillo"));
                objM.setDesc(res.getString("descripcion"));
                objM.setPrecio(res.getFloat("precio"));
                objM.setEstado(res.getString("estado"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }
    public void delPlatillo(){
        String query = "delete from Platillo where idPlatillo="+idPlatillo;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }
    public void getPlatilloById(){
        String query ="SELECT * FROM Platillo WHERE idPlatillo ="+idPlatillo;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
           if(res.next()){
               estado=res.getString("estado");
           }
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Float obtenerPrecio(int value) {
        int idPlatillo=value;
        float pre=0;
        String query = "select * from Platillo where idPlatillo="+idPlatillo;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                pre=res.getFloat("precio");
            }

        }catch (Exception e){}
        return pre;
    }
    public String obtenerEstadoo(int value) {
        int idPlatillo=value;
        String estado="";
        String query = "select estado from Platillo where idPlatillo="+idPlatillo;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                estado=res.getString("estado");
            }

        }catch (Exception e){}
        return estado;
    }
    @Override
    public String toString() {
        return nomPlatillo+" , "+estado;
    }


}


