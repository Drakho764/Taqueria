package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CajaDAO {
    private int idcaja;
    private float cantidad;
    private String fecha;

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private Connection con;
    public CajaDAO(){ con = Conexion.con; }
    private CajaDAO objM = null;


    public ObservableList<CajaDAO> selAllCajas() {
        ObservableList<CajaDAO> listMs = FXCollections.observableArrayList();
        String query = "SELECT * FROM Caja ";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new CajaDAO();
                objM.setIdcaja(res.getInt("idCaja"));
                objM.setCantidad(res.getFloat("cantidad"));
                objM.setFecha(res.getString("fecha"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }


}


