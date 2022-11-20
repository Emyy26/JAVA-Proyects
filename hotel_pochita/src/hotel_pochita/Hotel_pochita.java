/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel_pochita;

import Formularios.Principal.FrmPrincipal;
import Formularios.Principal.Inicio;
import Formularios.Principal.Inicio1;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nejo
 */
public class Hotel_pochita {

    static FrmPrincipal l = new FrmPrincipal();

    public FrmPrincipal getL() {
        return l;
    }

    public static void main(String[] args) throws InterruptedException {
        Inicio i = new Inicio();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
        Thread.sleep(5000);
        i.dispose();
        l.setVisible(true);
    }

}
