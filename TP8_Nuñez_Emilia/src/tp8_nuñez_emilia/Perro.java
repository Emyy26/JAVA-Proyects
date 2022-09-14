/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_nuñez_emilia;

import java.util.ArrayList;

/**
 *
 * @author emi
 */
public class Perro extends Animal{
    private String nombre;
    private ArrayList<Animal> perros;

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Animal> getPerros() {
        return perros;
    }

    public void setPerros(ArrayList<Animal> perros) {
        this.perros = perros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    }
   

