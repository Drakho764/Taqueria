package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.Componentes.ButtonCellCuentas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class CuentasDAO {
    private int idCuenta;
    private String estado;
    private int idMesa;
    private String observacion;
    private String horaentrada;
    private String horasalida;
    private int idCaja;
    private float  cantidad;
    private String fecha;
    private float totalCuenta;
    private int idEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public float getTotalCuenta() {
        return totalCuenta;
    }

    public void setTotalCuenta(float totalCuenta) {
        this.totalCuenta = totalCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
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
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getHoraentrada() {
        return horaentrada;
    }
    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }
    public String getHorasalida() {
        return horasalida;
    }
    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }
    public int getIdCaja() {
        return idCaja;
    }
    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
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
    public CuentasDAO(){ con = Conexion.con; }
    public void insCuenta(){

        String query = "insert into Cuenta " +
                "(estado,observacion,idMesa,horaentrada,horasalida,totalCuenta,idEmpleado) " +
                "values('"+estado+"','"+observacion+"',"+idMesa+",'"+horaentrada+"','00:00:00',0.0,"+idEmpleado+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query2 = "update Mesa set estado='ocupada' where " + "idMesa="+idMesa;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query2);
        }catch (Exception e){e.printStackTrace();}
    }
    public void updCuenta(){
        System.out.println(idMesa);
        String query = "update Cuenta set estado='"+estado+"'" +
                ",observacion='"+observacion+"',idMesa="+idMesa+",horaentrada='"+horaentrada+"'" +
                " where idCuenta="+idCuenta;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public void buscarCaja(){
        CuentasDAO objC=new CuentasDAO();
        Date fech=new Date();
        SimpleDateFormat objDate=new SimpleDateFormat("yyyy-MM-dd");
        String fecha=objDate.format(fech);
        String query = "SELECT * FROM Caja WHERE fecha='"+fecha+"'";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()!=true) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sin Caja");
                alert.setHeaderText("No hay una caja para el dia de hoy");
                alert.setContentText("¿Deseas agregar una caja?");
                ButtonType buttonTypeCaja = new ButtonType("Agregar Caja");
                alert.getButtonTypes().setAll(buttonTypeCaja);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeCaja) {
                    String query1 = "insert into Caja(cantidad,fecha) " +
                            "values(0,'"+fecha+"')";
                        stmt.executeQuery(query1);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bienvenido");
                alert.setHeaderText("Sea Bienvenido a su sistema");
                alert.setContentText("Welcome");
                ButtonType buttonTypeCaja = new ButtonType("Gracias");
                alert.getButtonTypes().setAll(buttonTypeCaja);
                Optional<ButtonType> result = alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private CuentasDAO objC = null;
    public ObservableList<CuentasDAO> selAllCuentas(){
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta order by idCuenta";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                objC.setHorasalida(res.getString("horasalida"));
                objC.setObservacion(res.getString("observacion"));
                objC.setTotalCuenta(res.getFloat("totalCuenta"));
                objC.setIdEmpleado(res.getInt("idEmpleado"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }
    public ObservableList<CuentasDAO> obtenerCuentasActivas(){
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Activo'";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }
    public ObservableList<CuentasDAO> obtenerCuentasPendientes(){
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Pendiente'";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC=new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                objC.setTotalCuenta(res.getFloat("totalCuenta"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }
    public void delCuenta(){
        String query = "delete from Cuenta where idCuenta="+idCuenta;
        try{
            Statement stmt = Conexion.con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
        String query2 = "update Mesa set estado='libre' WHERE idMesa="+idMesa;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query2);
        }catch (Exception e){e.printStackTrace();}

    }
    public ObservableList<CuentasDAO> selAllCuentasActivas(){
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Activo'";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }
    @Override
    public String toString() {
        return "Cuenta: "+idCuenta+" Mesa: "+idMesa+" Estado: "+estado;
    }

    public float getTotalCaja(String fec) {
        float tot=0;
        String query = "Select cantidad from Caja where fecha='"+fec+"'";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
            tot=res.getFloat("cantidad");
            }
        }catch (Exception e){}
        return tot;
    }

    public void ActualizarCaja(float totalCajaNuevo, String fec,int idMess) {
        String query3 = "update Caja set cantidad="+totalCajaNuevo+" WHERE fecha='"+fec+"'";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query3);
        }catch (Exception e){e.printStackTrace();}

        String query4 = "update Mesa set estado='libre' WHERE idMesa="+idMess;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query4);
        }catch (Exception e){e.printStackTrace();}
    }

    public void ActualizarCuentaAPendiente(int idCuenta) {
        String query = "update Cuenta set estado='Pendiente' where idCuenta="+idCuenta;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }
    public void ActualizarCuentaAPagada(int idCuenta) {
        String query = "update Cuenta set estado='Pagada' where idCuenta="+idCuenta;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}
    }

    public void ActualizarTotalCuenta(int idCuenta, float totalT,String horasal) {
        String query = "update Cuenta set totalCuenta="+totalT+" where idCuenta="+idCuenta;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){e.printStackTrace();}

        String query2 = "update Cuenta set horasalida='"+horasal+"' where idCuenta="+idCuenta;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query2);
        }catch (Exception e){e.printStackTrace();}
    }

    public ObservableList<CuentasDAO> selAllCuentasActivasPorMesero(int idEmplead) {
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Activo' and idEmpleado="+idEmplead;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                listCA.add(objC);
                System.out.println(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }

    public ObservableList<CuentasDAO> selAllCuentasPorMesero(int idEmplead) {
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta where idEmpleado="+idEmplead;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                objC.setHorasalida(res.getString("horasalida"));
                objC.setObservacion(res.getString("observacion"));
                objC.setTotalCuenta(res.getFloat("totalCuenta"));
                objC.setIdEmpleado(res.getInt("idEmpleado"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }

    public ObservableList<CuentasDAO> obtenerCuentasPendientesPorMesero(int idEmplead) {
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Pendiente' and idEmpleado="+idEmplead;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC=new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                objC.setTotalCuenta(res.getFloat("totalCuenta"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }

    public ObservableList<CuentasDAO> obtenerCuentasActivasPorMesero(int idEmplead) {
        ObservableList<CuentasDAO> listCA = FXCollections.observableArrayList();
        String query = "SELECT * FROM Cuenta WHERE estado='Activo' and idEmpleado="+idEmplead;
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CuentasDAO();
                objC.setIdCuenta(res.getInt("idCuenta"));
                objC.setIdMesa(res.getInt("idMesa"));
                objC.setEstado(res.getString("estado"));
                objC.setHoraentrada(res.getString("horaentrada"));
                listCA.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCA;
    }
}
