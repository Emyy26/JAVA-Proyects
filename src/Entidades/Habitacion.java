/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import javax.swing.ImageIcon;

/**
 *
 * @author nejo
 */
public class Habitacion {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer capacidad_pers;
    private String tarifa;
    private ImageIcon img;
    private String tv;
    private String wifi;
    private String aireAcondicionado;

    public Habitacion() {
        this.setId(0);
        this.setTitulo("");
        this.setDescripcion("");
        this.setCapacidad_pers(0);
        this.setTarifa("");
        this.setImg(null);
        this.setTv("");
        this.setWifi("");
        this.setAireAcondicionado("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidad_pers() {
        return capacidad_pers;
    }

    public void setCapacidad_pers(Integer capacidad_pers) {
        this.capacidad_pers = capacidad_pers;
    }


    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }


    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(String aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isValidar() {
        boolean isOk = false;

        if (getTitulo().trim().isEmpty()) {
            return isOk;
        }

        isOk = true;
        return isOk;
    }

    public Object[] getInfo() {
        Object[] oDato = {getId(),
            getTitulo(),
            getCapacidad_pers()};
        return oDato;
    }
}
