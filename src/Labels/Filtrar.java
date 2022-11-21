/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Labels;

import Miselaneos.Imagenes;
import javax.swing.JLabel;

/**
 *
 * @author nejo
 */
public class Filtrar extends JLabel {

    private Imagenes img;

    public Filtrar() {
        img = new Imagenes();
        setText("Filtrar:");
        setIcon(img.getFiltro(16, 16));
    }
}
