/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1_nuñez_2022;

/**
 *
 * @author emi
 */
public class Administrativo extends Empleado{
    private String oficio = "Administrativo";

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    
    public Administrativo(double porcentaje, double salario) {
        super(porcentaje, salario);
    }
    
    @Override
    public double calcularAguinaldo() {
        double aguinaldo = (getSalario() * getPorcentaje()) / 100;
        return aguinaldo;
    }
}
