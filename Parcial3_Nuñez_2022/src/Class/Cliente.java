/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author emi
 */
public class Cliente {
    private int id;
    private String surname;
    private String name;
    private String address;
    private Date date_nac;
    private Date date_alta;
    private Integer dni;
    private String cuil;
    private String phone1;
    private String phone2;
    private String email;
    private int cant_masc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_nac() {
        return date_nac;
    }

    public void setDate_nac(Date date_nac) {
        this.date_nac = date_nac;
    }

    public Date getDate_alta() {
        return date_alta;
    }

    public void setDate_alta(Date date_alta) {
        this.date_alta = date_alta;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCant_masc() {
        return cant_masc;
    }

    public void setCant_masc(int cant_masc) {
        this.cant_masc = cant_masc;
    }

    
     public Object[] getInfo() {
        Object[] oDato = {getId(),
            getSurname(),
            getName(),
            getDni()};
        return oDato;
    }

    public boolean isValidar(){
        boolean ok = false;
        
        String patronLyN = "^[a-zA-Z0-9 ]*$";
        String patronNum = "^[0-9]*$";
        String patronCE = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        
        if (!(this.getAddress().matches(patronLyN))) {
            JOptionPane.showMessageDialog(null, "Solo se aceptan letras y numeros para el domicilio", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!(this.getCuil().matches(patronNum)) || !(this.getPhone1().matches(patronNum)) ||  !(this.getPhone2().matches(patronNum))){
            JOptionPane.showMessageDialog(null, "Solo se aceptan numeros para el cuil y el numero de telefono", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (String.valueOf(this.getDni()).length() < 8 || String.valueOf(this.getDni()).length() > 8) {
            JOptionPane.showMessageDialog(null, "Longitud de DNI incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ((this.getCuil().length() < 11) || (this.getCuil().length() > 11)) {
            JOptionPane.showMessageDialog(null, "Longitud de CUIL incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!(this.getEmail().matches(patronCE))) {
            JOptionPane.showMessageDialog(null, "Email incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        
        } else if (this.getCant_masc() < 1) {
            JOptionPane.showMessageDialog(null, "Cantidad incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ok = true;
        }
        
        return ok;
    }
}
