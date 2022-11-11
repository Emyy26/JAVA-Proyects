/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author emi
 */

public abstract class Conexion {
    //Conexion a la base de datos
    
    public static final String DRIVER = "com.mysql.jdbc.Driver"; 
    public static final String URL = "jdbc:mysql://localhost:3306/Veterinaria_Nuñez_Parcial3";
    public static final String USER = "usr_dbs";
    public static final String CLAVE = "usr_dbs";
    private Connection con = null; 

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    private static Connection Conection;
    private static ResultSet Resultado;
    public boolean conectar(){
        boolean ok = false;
        setCon(null);
        try {
            Class.forName(DRIVER);
            Conection = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            if(Conection!=null){
                setCon(Conection);
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            return ok;
        }
    }
    
    
    public abstract ArrayList getList(String query);
    public abstract boolean isCreate(Object obj);
    public abstract boolean isDelete(Object obj);
    public abstract boolean isModify(Object obj);
    public abstract boolean isUpdate(String update);
}
