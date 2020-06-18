package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesasDAO {
    private int idMesa;
    private int cantPer;
    private String ubic;
    public String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    public int getCantPer() {
        return cantPer;
    }
    public void setCantPer(int cantPer) {
        this.cantPer = cantPer;
    }
    public String getUbic() {
        return ubic;
    }
    public void setUbic(String ubic) {
        this.ubic = ubic;
    }

    private Connection con;
    public MesasDAO(){ con = Conexion.con; }
    private MesasDAO objM = null;
    public void insMesas(){

        String query = "insert into Mesa " +
                "(cantPersonas,ubicacion,estado) " +
                "values("+cantPer+",'"+ubic+"','"+estado+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updMesas(){
        String query = "update Mesa set cantPersonas="+cantPer+"" +
                ",ubicacion='"+ubic+"',estado='"+estado+"' where " + "idMesa="+idMesa;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public ObservableList<MesasDAO> selAllMesas(){

        ObservableList<MesasDAO> listMs = FXCollections.observableArrayList();
        String query = "SELECT * FROM Mesa ORDER BY cantPersonas";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new MesasDAO();
                objM.setIdMesa(res.getInt("idMesa"));
                objM.setCantPer(res.getInt("cantPersonas"));
                objM.setUbic(res.getString("ubicacion"));
                objM.setEstado(res.getString("estado"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }
    public void delMesa(){
        String query = "delete from Mesa where idMesa="+idMesa;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }
    public void getMesaById(){
        String query ="SELECT * FROM Mesa WHERE idMesa ="+idMesa;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
           if(res.next()){
               estado=res.getString("estado");
               ubic=res.getString("ubicacion");

           }
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ubic+" Estado: "+estado+" No: "+idMesa;
    }


}


