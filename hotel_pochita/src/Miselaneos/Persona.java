/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nejo
 */
public abstract class Persona {

    private Integer id;
    private String apellido;
    private String nombres;
    private String ubicacion; //Provincia, localidad, y direccion
    private String celular;
    private String email;
    private String nro_dni;
    private Date fecha_alta;

    public Persona() {
        setId(0);
        setApellido("");
        setNombres("");
        setUbicacion("");
        setCelular("");
        setEmail("");
        setNro_dni("");
        setFecha_alta(Calendar.getInstance().getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNro_dni() {
        return nro_dni;
    }

    public void setNro_dni(String nro_dni) {
        this.nro_dni = nro_dni;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public abstract boolean isValidar();

    public abstract Object[] getInfo();
}
