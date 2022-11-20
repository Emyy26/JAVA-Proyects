/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author nejo
 */
public abstract class ConexionMySQL {
    public static final String DRIVER = "com.mysql.jdbc.Driver"; 
    public static final String URL = "jdbc:mysql://localhost:3306/hotel";
    public static final String USER = "root";
    public static final String CLAVE = "Nejo9428";
    public static final String TEMPDIR = System.getProperty("java.io.tmpdir");
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String PATH_TMP = TEMPDIR + SEPARATOR;

    private Connection con = null; //Clase provista por MySQL para manejar la conexion, contiene
    
    public Utilidades util;
    public ConexionMySQL(){
        util = new Utilidades();
    }
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public boolean conectar(){
        boolean ok = false;
        setCon(null);
        try {
            Class.forName(DRIVER);//Cargar el Driver de MySQL para establecer la conexion
            Connection conexion = (Connection) DriverManager.getConnection(URL, USER, CLAVE); //Conectar por medio de DriverManager
            if(conexion!=null){
                setCon(conexion);
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            return ok;
        }
    }
    
    public abstract ArrayList getList(String query);
    public abstract boolean isIngresar(Object obj);
    public abstract boolean isEliminar(Object obj);
    public abstract boolean isModificar(Object obj);
    public abstract boolean isActualizar(String update);
}
