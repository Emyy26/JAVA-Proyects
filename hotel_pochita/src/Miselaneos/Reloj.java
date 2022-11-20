/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author nejo
 */
public class Reloj extends JLabel implements Runnable {
    private Imagenes img;
    public Reloj(){
        img = new Imagenes();
        setText("");
        setIcon(img.getReloj(16, 16));
        
        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        while(true){
            try{
                Calendar calAhora = Calendar.getInstance();
                String patron = "H:mm:ss";
                SimpleDateFormat formato = new SimpleDateFormat(patron);
                String horaMinSeg = formato.format(calAhora.getTime());
                setText("Hora: " + horaMinSeg);
                Thread.sleep(1000); //1 segundo
            }catch(InterruptedException ex){
                setText("");
            }    
        }
    }
}
