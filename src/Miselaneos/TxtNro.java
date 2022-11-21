/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author nejo
 */
public class TxtNro extends JTextField {

    public TxtNro() {
        super(new Integer(0));
        setText("0");

        //Agregar Oyente para que solo ingrese Numeros
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && !Character.isISOControl(e.getKeyChar())) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public Integer toEntero() {
        Integer varEntero = 0;
        try {
            varEntero = Integer.parseInt(getText().trim());
        } catch (NumberFormatException ex) {
            varEntero = 0;
        } finally {
            return varEntero;
        }
    }
}
