/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author nejo
 */

//La clase se encargara de redimensionar las imagenes para los iconos del proyecto
public class Imagenes {
    private int ancho = 16;
    private int alto = 16;

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    //Metodo encargado de redimensionar la imagen 
    // Recibe una clase ImageIcon con la imagen
    
    private ImageIcon escalar(ImageIcon ico){
        Image img0 = ico.getImage();
        Image img1 = img0.getScaledInstance(getAlto(), getAncho(), Image.SCALE_SMOOTH);
        
        ImageIcon iconReturn = new ImageIcon(img1);
        return iconReturn;
    }
    
    public ImageIcon getActualizar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/update.png"));
        return escalar(ico);
    }
    
    public ImageIcon getAutor(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/author.png"));
        return escalar(ico);
    }
    
    public ImageIcon getBorrar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/delete.png"));
        return escalar(ico);
    }
    
    public ImageIcon getBuscar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/search.png"));
        return escalar(ico);
    }
    
    public ImageIcon getCajaDinero(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/money.png"));
        return escalar(ico);
    }
    
    public ImageIcon getCancelar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/cancel.png"));
        return escalar(ico);
    }
    
    public ImageIcon getConexion(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/connection.png"));
        return escalar(ico);
    }
    
    public ImageIcon getFiltro(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/filter.png"));
        return escalar(ico);
    }
    
    public ImageIcon getDelFiltro(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/del_filtro.png"));
        return escalar(ico);
    }
    
    public ImageIcon getFicha(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/user.png"));
        return escalar(ico);
    }
    
    public ImageIcon getNuevo(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/new.png"));
        return escalar(ico);
    }
    
    public ImageIcon getModificar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/modify.png"));
        return escalar(ico);
    }
     
    public ImageIcon getVolver(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/back.png"));
        return escalar(ico);
    }
    
    public ImageIcon getReloj(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/clock.png"));
        return escalar(ico);
    }
    
    public ImageIcon getSeleccionar(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/select.png"));
        return escalar(ico);
    }
    
    public ImageIcon getServicios(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/services.png"));
        return escalar(ico);
    }
    
    public ImageIcon getMenu(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/menu.png"));
        return escalar(ico);
    }
    
    public ImageIcon getSysLogo(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/hotel.png"));
        return escalar(ico);
    }
    
    public ImageIcon getSalir(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/exit.png"));
        return escalar(ico);
    }
    
    public ImageIcon getHuesped(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/guest.png"));
        return escalar(ico);
    }
    
    public ImageIcon getHabitacion(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/room.png"));
        return escalar(ico);
    }
    
    public ImageIcon getPersonal(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/staff.png"));
        return escalar(ico);
    }
    
    public ImageIcon getReservas(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/bookings.png"));
        return escalar(ico);
    }
    
    public ImageIcon getHome(int ancho, int alto){
        setAncho(ancho);
        setAlto(alto);
        ImageIcon ico = new ImageIcon(getClass().getResource("/Imagenes/homecolor.png"));
        return escalar(ico);
    }
}
