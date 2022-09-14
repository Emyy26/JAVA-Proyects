/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1_nuñez_2022;

import java.util.ArrayList;

/**
 *
 * @author emi
 */
public class Habitacion {
    private int nro;
    private ArrayList<Insumo> insumos;
    private ArrayList<Servicio> servicios;
    private double totalServicios;
    private double totalProductos;

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public double getTotalServicios() {
        return totalServicios;
    }

    public void setTotalServicios(double totalServicios) {
        this.totalServicios = totalServicios;
    }

    public double getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(double totalProductos) {
        this.totalProductos = totalProductos;
    }

    

    public Habitacion(){
        this.setNro(0);
        this.setInsumos(new ArrayList<Insumo>());
        this.setServicios(new ArrayList<Servicio>());
    }
    
    /**
     * Metodo que recorre los productos de la habitacion
     * y obtiene la suma del importe de cada producto
     * consumido en la habitacion, por ultimo retorna
     * el importe total de productos ocupados en la habitacion
     * @return 
     */
    public double getTotalDeProductos(){
        double total = 0.d;
        if(getInsumos()==null
                || getInsumos().size()==0){
            return total;
        }
        for(int index=0;index<getInsumos().size();index++){
            Insumo i = getInsumos().get(index);
            total = total + (i.getCantidad() * i.getPrecio());
        }
        return total;
    }
}
