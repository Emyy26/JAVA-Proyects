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
    private String provincia;
    private String ciudad;
    private String celular;
    private String email;
    private String nro_dni;
    private String cuil;
    private Date fecha_nacimiento;

    public Persona() {
        setId(0);
        setApellido("");
        setNombres("");
        setProvincia("");
        setCiudad("");
        setCelular("");
        setEmail("");
        setNro_dni("");
        setCuil("");
        setFecha_nacimiento(Calendar.getInstance().getTime());
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public abstract boolean isValidar();

    public abstract Object[] getInfo();
}
