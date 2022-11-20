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
public class lbl extends JLabel {

    private Imagenes img;

    public lbl() {
        img = new Imagenes();
        setText("Label");
        setIcon(null);
    }
}
