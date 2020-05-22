package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String host = "localhost";
    private static final String user = "topicos20";
    private static final String pwd = "1234";
    private static final String db  = "taqueriachida";
    public static Connection con;


    public static void CrearConexion(){
       try {
           Class.forName("org.mariadb.jdbc.Driver");
           //Estamos abriendo el socket hacia el SGBD
          con= DriverManager.getConnection("jdbc:mariadb://"+host+":3306/"+db,user,pwd);
           System.out.println("Se conectó");
       }catch(ClassNotFoundException e){
           e.printStackTrace();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
}
