/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1_nuñez_2022;

/**
 *
 * @author emi
 */
public class Servicio {
    private double tarifa = 5.00d;
    private int tiempo;

    
    public double calcularServicios() {
        double totalProductos = getTarifa() * getTiempo();
        return totalProductos;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
