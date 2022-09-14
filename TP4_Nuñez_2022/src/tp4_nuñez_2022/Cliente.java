/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp4_nuñez_2022;

/**
 *
 * @author emi
 */
public class Cliente {
    private String nombre;
    private int edad;
    private String sexo;
    private boolean isEmbarazada;
    private int nroOrden;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean getIsEmbarazada() {
        return isEmbarazada;
    }

    public void setIsEmbarazada(boolean isEmbarazada) {
        this.isEmbarazada = isEmbarazada;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

}