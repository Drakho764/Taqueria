package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO {
    private int idorden;
    private int idplatillo;
    private int idbebida;
    private int idmesa;
    private int idempleado;
    private int idcuenta;
    private int cantPla;
    private int cantBeb;
    private String obs;
    private float precio;
    private String nomPla,nomBeb;

    public String getNomPla() {
        return nomPla;
    }

    public void setNomPla(String nomPla) {
        this.nomPla = nomPla;
    }

    public String getNomBeb() {
        return nomBeb;
    }

    public void setNomBeb(String nomBeb) {
        this.nomBeb = nomBeb;
    }

    public int getIdorden() {
        return idorden;
    }
    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }
    public int getIdplatillo() {
        return idplatillo;
    }
    public void setIdplatillo(int idplatillo) {
        this.idplatillo = idplatillo;
    }
    public int getIdbebida() {
        return idbebida;
    }
    public void setIdbebida(int idbebida) {
        this.idbebida = idbebida;
    }
    public int getIdmesa() {
        return idmesa;
    }
    public void setIdmesa(int idmesa) {
        this.idmesa = idmesa;
    }
    public int getIdempleado() {
        return idempleado;
    }
    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }
    public int getIdcuenta() {
        return idcuenta;
    }
    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }
    public int getCantPla() {
        return cantPla;
    }
    public void setCantPla(int cantPla) {
        this.cantPla = cantPla;
    }
    public int getCantBeb() {
        return cantBeb;
    }
    public void setCantBeb(int cantBeb) {
        this.cantBeb = cantBeb;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    private Connection con;
    public OrdenDAO(){ con = Conexion.con; }
    private OrdenDAO objM = null;
    public void insOrden(int cantb){

        String query = "insert into Orden" +
                "(idPlatillo,idBebida,idMesa,idEmpleado,observacion,totalParcial,idCuenta,cantPlatillo,cantBebida) " +
                "values("+idplatillo+","+idbebida+","+idmesa+","+idempleado+",'"+obs+"',"+precio+","+idcuenta+","+cantPla+","+cantBeb+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int canti=cantb-cantBeb;
        String query2 = "update Bebida set cantidad="+canti+" where idBebida="+idbebida;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query2);
        }catch (Exception e){e.printStackTrace();}
    }

    public ObservableList<OrdenDAO> selAllOrdenes() {
        ObservableList<OrdenDAO> listMs = FXCollections.observableArrayList();
        String query="select orden.idOrden,orden.idCuenta,orden.idMesa,orden.idEmpleado,orden.idPlatillo,platillo.nomPlatillo," +
                "orden.idBebida,Bebida.nomBebida,orden.observacion,orden.cantPlatillo,orden.cantBebida,orden.totalParcial " +
                "from orden,platillo,bebida where (orden.idPlatillo=platillo.idPlatillo) and (orden.idBebida=bebida.idBebida)";
        //String query = "SELECT * FROM Orden ";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new OrdenDAO();
                objM.setIdorden(res.getInt("orden.idOrden"));
                objM.setIdcuenta(res.getInt("orden.idCuenta"));
                objM.setIdmesa(res.getInt("orden.idMesa"));
                objM.setIdplatillo(res.getInt("orden.idPlatillo"));
                objM.setIdbebida(res.getInt("orden.idBebida"));
                objM.setIdempleado(res.getInt("orden.idEmpleado"));
                objM.setObs(res.getString("orden.observacion"));
                objM.setCantPla(res.getInt("orden.cantPlatillo"));
                objM.setCantBeb(res.getInt("orden.cantBebida"));
                objM.setPrecio(res.getFloat("orden.totalParcial"));
                objM.setNomPla(res.getString("platillo.nomPlatillo"));
                objM.setNomBeb(res.getString("bebida.nomBebida"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }

    public ObservableList<OrdenDAO> selAllOrdenesbyCuenta(int idCuenta) {
        ObservableList<OrdenDAO> listMs = FXCollections.observableArrayList();
        String query="select orden.idOrden,orden.idCuenta,orden.idMesa,orden.idEmpleado,orden.idPlatillo,platillo.nomPlatillo," +
                "orden.idBebida,Bebida.nomBebida,orden.observacion,orden.cantPlatillo,orden.cantBebida,orden.totalParcial " +
                "from orden,platillo,bebida where (orden.idPlatillo=platillo.idPlatillo) and (orden.idBebida=bebida.idBebida) and (idCuenta="+
                idCuenta+")";

        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new OrdenDAO();
                objM.setIdorden(res.getInt("orden.idOrden"));
                objM.setIdcuenta(res.getInt("orden.idCuenta"));
                objM.setIdmesa(res.getInt("orden.idMesa"));
                objM.setIdplatillo(res.getInt("orden.idPlatillo"));
                objM.setIdbebida(res.getInt("orden.idBebida"));
                objM.setIdempleado(res.getInt("orden.idEmpleado"));
                objM.setObs(res.getString("orden.observacion"));
                objM.setCantPla(res.getInt("orden.cantPlatillo"));
                objM.setCantBeb(res.getInt("orden.cantBebida"));
                objM.setPrecio(res.getFloat("orden.totalParcial"));
                objM.setNomPla(res.getString("platillo.nomPlatillo"));
                objM.setNomBeb(res.getString("bebida.nomBebida"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }

    public ObservableList<OrdenDAO> selAllOrdenesPorMesero(int idEmpleado) {
        ObservableList<OrdenDAO> listMs = FXCollections.observableArrayList();
        String query="select orden.idOrden,orden.idCuenta,orden.idMesa,orden.idEmpleado,orden.idPlatillo,platillo.nomPlatillo," +
                "orden.idBebida,Bebida.nomBebida,orden.observacion,orden.cantPlatillo,orden.cantBebida,orden.totalParcial " +
                "from orden,platillo,bebida where (orden.idPlatillo=platillo.idPlatillo) and (orden.idBebida=bebida.idBebida) and (idEmpleado=)"+idEmpleado;
       // String query = "SELECT * FROM Orden where idEmpleado="+idEmpleado;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new OrdenDAO();
                objM.setIdorden(res.getInt("orden.idOrden"));
                objM.setIdcuenta(res.getInt("orden.idCuenta"));
                objM.setIdmesa(res.getInt("orden.idMesa"));
                objM.setIdplatillo(res.getInt("orden.idPlatillo"));
                objM.setIdbebida(res.getInt("orden.idBebida"));
                objM.setIdempleado(res.getInt("orden.idEmpleado"));
                objM.setObs(res.getString("orden.observacion"));
                objM.setCantPla(res.getInt("orden.cantPlatillo"));
                objM.setCantBeb(res.getInt("orden.cantBebida"));
                objM.setPrecio(res.getFloat("orden.totalParcial"));
                objM.setNomPla(res.getString("platillo.nomPlatillo"));
                objM.setNomBeb(res.getString("bebida.nomBebida"));
                listMs.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMs;
    }
}


