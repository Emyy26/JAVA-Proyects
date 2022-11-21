/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Miselaneos.Persona;

/**
 *
 * @author nejo
 */
public class Huesped extends Persona {

    private String vehiculo;//marca, modelo, patente

    public Huesped(){
        super();
        setVehiculo("");
    }
    
    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
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
            getEmail()};
        return oDato;
    }
    
}
