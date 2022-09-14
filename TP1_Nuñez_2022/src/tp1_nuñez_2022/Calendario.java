/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp1_nuñez_2022;

/**
 *
 * @author emi
 */
public class Calendario {
    public boolean diaSemana(String dia){
        String[] arr;
        arr = new String[7];
        arr[0] = "lunes";
        arr[1] = "martes"; 
        arr[2] = "miercoles";
        arr[3] = "jueves";
        arr[4] = "viernes";
        arr[5] = "sabado";
        arr[6] = "domingo";
       
        boolean ok = false;
        
        for (int i = 0; i < arr.length; i++){
          if(dia == arr[i]){
              ok = true;
              break;
          }
        }
        return ok;
    }
}
