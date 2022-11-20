/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Miselaneos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nejo
 */
public class Utilidades {

    public String getFechaMySQL(Date fecha) {
        String patron = "yyyy/MM/dd";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        String fechaMySQL = "0001/01/01";
        try {
            fechaMySQL = formato.format(fecha);
        } catch (Exception ex) {
        } finally {
            return fechaMySQL;
        }
    }

    public String getFechaDiaMesAnio(Date fecha) {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        String fechaMySQL = "01/01/0001";
        try {
            fechaMySQL = formato.format(fecha);
        } catch (Exception ex) {
        } finally {
            return fechaMySQL;
        }
    }
}
