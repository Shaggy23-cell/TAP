package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    private String usuario="root";
    private String password="";
    private String bd="JuegoDeMesa";
    private String servidor="localhost";
    public Connection conexion;

    public Conexion(){
        try{
        conexion= DriverManager.getConnection("jdbc:mysql://"+servidor+":3306/"+bd+"?useUnicode=true&useJDBCCompliantTimeZoneShift=useLegacyDatetimeCode&serverTimeZone=UTC",usuario,password);
            System.out.println("CONECTADO EXISTOSAMENTE");
        }catch(Exception e){
            System.out.println("No se pudo conectar al servidor"+e.getMessage());

        }
    }// Llave constructor
    public ResultSet consultar(String sql){
        ResultSet resultado=null;
        try{
            Statement st=conexion.createStatement();
            resultado= st.executeQuery(sql);
        }catch ( Exception e){
            System.out.println("ERROR en la consulta"+ e.getMessage());

        }
        return  resultado;
    }
}


