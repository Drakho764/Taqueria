package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BebidasDAO {
    private int idbebida;
    private String nombeb;
    private String desc;
    private int cantidad;
    private float precio;


    public int getIdbebida() {
        return idbebida;
    }

    public void setIdbebida(int idbebida) {
        this.idbebida = idbebida;
    }

    public String getNombeb() {
        return nombeb;
    }

    public void setNombeb(String nombeb) {
        this.nombeb = nombeb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    private Connection con;
    public BebidasDAO(){ con = Conexion.con; }
    private BebidasDAO objM = null;
    public void insBebidas(){

        String query = "insert into bebida" +
                "(nomBebida,descripcion,precio,cantidad) " +
                "values('"+nombeb+"','"+desc+"',"+precio+","+cantidad+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updBebida(){
        String query = "update Bebida set nomBebida='"+nombeb+"'," +
                "descripcion='"+desc+"',precio="+precio+",cantidad="+cantidad+" where idBebida="+idbebida;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public ObservableList<BebidasDAO> selAllBebidas(){

        ObservableList<BebidasDAO> listMs = FXCollections.observableArrayList();
        String query = "SELECT * FROM Bebida ORDER BY nomBebida";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new BebidasDAO();
                objM.setIdbebida(res.getInt("idBebida"));
                objM.setNombeb(res.getString("nomBebida"));
                objM.setDesc(res.getString("descripcion"));
                objM.setPrecio(res.getFloat("precio"));
                objM.setCantidad(res.getInt("cantidad"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }
    public void delBebida(){
        String query = "delete from Bebida where idBebida="+idbebida;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }
    public void getBebidaById(){
        String query ="SELECT * FROM Bebida WHERE idBebida ="+idbebida;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
           if(res.next()){
               nombeb=res.getString("nomBebida");
               cantidad=res.getInt("cantida");
           }
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Float obtenerPrecio(int value) {
        int idBebida=value;
        float pre=0;
        String query = "select * from Bebida where idBebida="+idBebida;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                pre=res.getFloat("precio");
            }

        }catch (Exception e){}
        return pre;
    }
    public Integer obtenerCantidad(int value) {
        int idBebida=value;
        int cant=0;
        String query = "select * from Bebida where idBebida="+idBebida;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                cant=res.getInt("cantidad");
            }

        }catch (Exception e){}
        return cant;
    }
    @Override
    public String toString() {
        return nombeb+ " disponibles: " + cantidad ;
    }


}


