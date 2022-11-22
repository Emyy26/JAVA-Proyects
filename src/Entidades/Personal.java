/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Miselaneos.Persona;
import java.util.Date;

/**
 *
 * @author nejito
 */
public class Personal extends Persona {
    private String observaciones;
    private Date fecha_alta;
    private String tipoPersonal;
    
    public Personal(){
        setId(0);
        setApellido("");
        setNombres("");
        setCelular("");
        setEmail("");
        setNro_dni("");
        setCuil("");
        setTipoPersonal("");
        setSexo("");
        setFecha_alta(null);
        setObservaciones("");
        
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }
    
    

    @Override
    public boolean isValidar() {
        boolean isOk = false;
        if(getApellido().trim().isEmpty()){
            return isOk;
        }
        
        if(getNombres().trim().isEmpty()){
            return isOk;
        }
        
        if(getNro_dni().trim().isEmpty()){
            return isOk;
        }
        
        isOk = true;
        return isOk;
    }

    @Override
    public Object[] getInfo() {
        Object[] oDato = {getId(),
            getApellido(),
            getNombres(),
            getNro_dni(),
            getCelular(),
            getEmail(),
            getCuil(),
            getSexo(),
            getTipoPersonal(),
            getFecha_alta()};
        return oDato;
    }
    
}
