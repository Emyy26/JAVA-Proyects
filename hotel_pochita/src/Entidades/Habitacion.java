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
    private boolean estado;
    private String url_img;
    private Double tarifa;
    private ImageIcon img;

    public Habitacion() {
        this.setId(0);
        this.setTitulo("");
        this.setDescripcion("");
        this.setCapacidad_pers(0);
        this.setEstado(true); //Activo-Inactivo
        this.setUrl_img("");
        this.setTarifa(0.0d);
        this.setImg(null);
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
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
