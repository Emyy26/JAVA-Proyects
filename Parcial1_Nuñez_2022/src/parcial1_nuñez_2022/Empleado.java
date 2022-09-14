/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1_nuñez_2022;

/**
 *
 * @author emi
 */
public abstract class Empleado {
    private String nombre;
    private double salario;
    private double porcentaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Empleado(double porcentaje, double salario) {
        this.porcentaje = porcentaje;
        this.salario = salario;
    }
    public abstract double calcularAguinaldo();

}