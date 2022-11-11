/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package parcial3_nuñez_2022;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Class.Cliente;
import Class.Conexion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import Entidades.Clientes;

/**
 *
 * @author emi
 */
public class Frame extends javax.swing.JFrame {

    DefaultTableModel modelo = null;
    TableRowSorter<TableModel> elQueOrdena = null;
    private TreeMap<String, Cliente> listaClientes = null;
    /**
     * Creates new form Frame
     */
    public Frame() {
       initComponents();
       _createTable();
       _addCliente();
       
       
        
    }
    public void _addCliente() {
        /*_cleanTable();
        Clientes cnx = new Clientes();

        ArrayList<Cliente> list = new ArrayList<Cliente>();

        if (cnx.conectar()) {
            list = cnx.getList("SELECT * FROM Clientes");
        }

        listaClientes = new TreeMap<String, Cliente>();

        for (int index = 0; index < list.size(); index++) {
            Cliente p = list.get(index);
            modelo.addRow(p.getInfo());

            String key = p.getId() + "";
            listaClientes.put(key, p);
        }*/
         _cleanTable();

        Clientes conexion = new Clientes();

        ArrayList<Cliente> atributosDelCliente = new ArrayList<>();

        if (conexion.conectar()) {
            atributosDelCliente = conexion.getList("SELECT * FROM Clientes");
        }

        listaClientes = new TreeMap<String, Cliente>();

        // Recorrer los atributos del cliente y enviarlos a cada fila
        // de la tabla del frame.
        // Ademas asocia esos atributos a cada cliente mediante
        // una estructura clave valor (clave = id).
        for (int i = 0; i < atributosDelCliente.size(); i++) {
            Cliente cl = atributosDelCliente.get(i);
            modelo.addRow(cl.getInfo());
            String key = cl.getId() + "";
            listaClientes.put(key, cl);
            }
        
        
    }
    public void _showClientes(Cliente cli) {
        String surname = "";
        String name = "";
        String address = "";
        Date dateNac = null;
        Date dateAlta = null;
        String dni = "";
        String cuil = "";
        String phone1 = "";
        String phone2 = "";
        String email = "";
        String cant_masc = "1";
        
        if (cli != null) {
            surname = cli.getSurname().trim();
            name = cli.getName().trim();
            address = cli.getAddress().trim();
            dateNac = cli.getDate_nac();
            dateAlta = cli.getDate_alta();
            dni = cli.getDni() + "";
            cuil = cli.getCuil().trim();
            phone1 = cli.getPhone1().toString();
            phone2 = cli.getPhone2().toString();
            email = cli.getEmail().trim();
            cant_masc = cli.getCant_masc() + "";
        }

        surnameText.setText(surname);
        nameText.setText(name);
        addressText.setText(address);
        birth.setDate(dateNac);
        
        alta.setDate(dateAlta);
        dniText.setText(dni);
        cuilText.setText(cuil);
        phone1Text.setText(phone1);
        phone2Text.setText(phone2);
        emailText.setText(email);
        cantMascText.setText(cant_masc);
    }
    private void _cleanTable() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }
    public void _select() {
        String key = "";
        Cliente p = null;
        try {
            int indexTable = jTable1.getSelectedRow();
            int indexModelo = jTable1.convertRowIndexToModel(indexTable);

            key = modelo.getValueAt(indexModelo, 0).toString();
            if (listaClientes.containsKey(key)) {
                p = listaClientes.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _showClientes(p);
        }
    }
    public void _createTable() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");
        elQueOrdena = new TableRowSorter<TableModel>(modelo);

        jTable1.setModel(modelo);
        jTable1.setRowSorter(elQueOrdena);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(115);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(115);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(115);

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable1.updateUI();
        
    }

    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z\\s]*$");
    }

    public static boolean isEmail(String e) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(e);
        return e != null && mather.find();
    }
    private void Clean() {
        surnameText.setText("");
        nameText.setText("");
        addressText.setText("");
        dniText.setText("");
        birth.setDate(null);
        alta.setDate(null);
        phone1Text.setText("");
        phone2Text.setText("");
        cuilText.setText("");
        cantMascText.setText("");
        emailText.setText("");
    }
    public void _filter(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter(texto.trim()));
        }
    }
    
    private Object[] isValidModify() {
        boolean ok = false;
        Cliente g = new Cliente();

        Object[] oResult = new Object[2];
        oResult[0] = ok;

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        String name = nameText.getText().trim();
        String surname = surnameText.getText().trim();
        String dni = dniText.getText().trim();
        String address = addressText.getText().trim();
        String movil1 = phone1Text.getText().trim();
        String movil2 = phone2Text.getText().trim();
        String email = emailText.getText().trim();
        String cuil = cuilText.getText().trim();
        String cant_masc = cantMascText.getText().trim();
        Date date1 = (birth.getDate());
        Date date2 = (alta.getDate());
        Integer correctDni = 0;
        Integer correctCantMasc = 0;
        
        g.setAddress(address);
        
        //Se valida que una fecha este colocada en el calendario
        if (date1 == null) {
            JOptionPane.showMessageDialog(modifyButton, "¡Seleccione una fecha en el calendario!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        else{
            g.setDate_nac(date1);
        }

        //Se valida que una fecha este colocada en el calendario
        if (date2 == null) {
            JOptionPane.showMessageDialog(modifyButton, "¡Seleccione una fecha en el calendario!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }else{
            g.setDate_alta(date2);
        }

        if (isAlpha(name)) {
            //hace que el nombre tenga el formato de la primer letra en mayuscula y el resto en minuscula.
            name = (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            g.setName(name);
            ok = true;

        } else {
            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el nombre solo se permiten letras!", "Atencion", JOptionPane.WARNING_MESSAGE);
            nameText.setText("");
            nameText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        if (isAlpha(surname)) {
            surname = (surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase());
            g.setSurname(surname);
            ok = true;

        } else {
            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el apellido solo se permiten letras!", "Atencion", JOptionPane.WARNING_MESSAGE);
            surnameText.setText("");
            surnameText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        try {
            dni.chars().allMatch(Character::isDigit);
            //se transforma el dato de string a int.
            correctDni = Integer.parseInt(dniText.getText().trim());
            g.setDni(correctDni);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el documento solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            dniText.setText("");
            dniText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        try {
            cuil.chars().allMatch(Character::isDigit);
            g.setCuil(cuil);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el cuil solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            cuilText.setText("");
            cuilText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        try {
            cant_masc.chars().allMatch(Character::isDigit);
            //se transforma el dato de string a int.
            correctCantMasc = Integer.parseInt(cantMascText.getText().trim());
            g.setCant_masc(correctCantMasc);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(modifyButton, "Para ingresar la cantidad de mascotas solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            dniText.setText("");
            dniText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        try {
            movil1.chars().allMatch(Character::isDigit);
            g.setPhone1(movil1);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el telefono solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            phone1Text.setText("");
            phone1Text.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        try {
            movil2.chars().allMatch(Character::isDigit);
            g.setPhone2(movil2);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(modifyButton, "Para ingresar el telefono solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            phone2Text.setText("");
            phone2Text.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        if (isEmail(email)) {
            email = email;
            g.setEmail(email);
            ok = true;
        } else {
            JOptionPane.showMessageDialog(modifyButton, "Email incorrecto, ingrese un email válido!", "Atencion", JOptionPane.WARNING_MESSAGE);
            emailText.setText("");
            emailText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        Object[] oReturn = new Object[2];
        oReturn[0] = ok;
        oReturn[1] = g;
        return oReturn;

    }
    
    private Object[] isValidAdd() {
        boolean ok = false;
        Cliente g = new Cliente();

        Object[] oResult = new Object[2];
        oResult[0] = ok;

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        String name = nameText.getText().trim();
        String surname = surnameText.getText().trim();
        String dni = dniText.getText().trim();
        String address = addressText.getText().trim();
        String movil1 = phone1Text.getText().trim();
        String movil2 = phone2Text.getText().trim();
        String email = emailText.getText().trim();
        String cuil = cuilText.getText().trim();
        String cant_masc = cantMascText.getText().trim();
        Date date1 = birth.getDate();
        Date date2 = alta.getDate();
        Integer correctDni = 0;
        Integer correctCantMasc = 0;

        g.setAddress(address);
       
        //Se valida que una fecha este colocada en el calendario
        if (date1 == null) {
            JOptionPane.showMessageDialog(addButton, "¡Seleccione una fecha en el calendario 1!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        else{
            g.setDate_nac(date1);
        }

        //Se valida que una fecha este colocada en el calendario
        if (date2 == null) {
            JOptionPane.showMessageDialog(addButton, "¡Seleccione una fecha en el calendario 2!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }else{
            g.setDate_alta(date2);
        }

        if (isAlpha(name)) {
            //hace que el nombre tenga el formato de la primer letra en mayuscula y el resto en minuscula.
            name = (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            g.setName(name);
            ok = true;

        } else {
            JOptionPane.showMessageDialog(addButton, "Para ingresar el nombre solo se permiten letras!", "Atencion", JOptionPane.WARNING_MESSAGE);
            nameText.setText("");
            nameText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        if (isAlpha(surname)) {
            surname = (surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase());
            g.setSurname(surname);
            ok = true;

        } else {
            JOptionPane.showMessageDialog(addButton, "Para ingresar el apellido solo se permiten letras!", "Atencion", JOptionPane.WARNING_MESSAGE);
            surnameText.setText("");
            surnameText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
       
        try {
            dni.chars().allMatch(Character::isDigit);
            //se transforma el dato de string a int.
            correctDni = Integer.parseInt(dniText.getText().trim());
            g.setDni(correctDni);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(addButton, "Para ingresar el documento solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            dniText.setText("");
            dniText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        try {
            cuil.chars().allMatch(Character::isDigit);
            g.setCuil(cuil);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(addButton, "Para ingresar el cuil solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            cuilText.setText("");
            cuilText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        try {
            cant_masc.chars().allMatch(Character::isDigit);
            //se transforma el dato de string a int.
            correctCantMasc = Integer.parseInt(cantMascText.getText().trim());
            g.setCant_masc(correctCantMasc);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(addButton, "Para ingresar la cantidad de mascotas solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            dniText.setText("");
            dniText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        
        if (isEmail(email)) {
            email = email;
            g.setEmail(email);
            ok = true;
        } else {
            JOptionPane.showMessageDialog(addButton, "Email incorrecto, ingrese un email válido!", "Atencion", JOptionPane.WARNING_MESSAGE);
            emailText.setText("");
            emailText.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }
        try {
            movil1.chars().allMatch(Character::isDigit);
            g.setPhone1(movil1);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(addButton, "Para ingresar el telefono solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            phone1Text.setText("");
            phone1Text.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        try {
            movil2.chars().allMatch(Character::isDigit);
            g.setPhone2(movil2);
            ok = true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(addButton, "Para ingresar el telefono solo se permiten números!", "Atencion", JOptionPane.WARNING_MESSAGE);
            phone2Text.setText("");
            phone2Text.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        Object[] oReturn = new Object[2];
        oReturn[0] = ok;
        oReturn[1] = g;
        return oReturn;

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nameText = new javax.swing.JTextField();
        surnameText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        dniText = new javax.swing.JTextField();
        cuilText = new javax.swing.JTextField();
        phone1Text = new javax.swing.JTextField();
        phone2Text = new javax.swing.JTextField();
        emailText = new javax.swing.JTextField();
        cantMascText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        birth = new com.toedter.calendar.JDateChooser();
        alta = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        modifyButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        filterText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        nameText.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombres"));
        nameText.setPreferredSize(new java.awt.Dimension(12, 40));
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        surnameText.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellidos"));
        surnameText.setPreferredSize(new java.awt.Dimension(12, 40));

        addressText.setBorder(javax.swing.BorderFactory.createTitledBorder("Direccion"));
        addressText.setPreferredSize(new java.awt.Dimension(12, 40));

        dniText.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI"));
        dniText.setPreferredSize(new java.awt.Dimension(12, 40));

        cuilText.setBorder(javax.swing.BorderFactory.createTitledBorder("CUIL"));
        cuilText.setPreferredSize(new java.awt.Dimension(12, 40));

        phone1Text.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Celular 1")));
        phone1Text.setPreferredSize(new java.awt.Dimension(12, 40));

        phone2Text.setBorder(javax.swing.BorderFactory.createTitledBorder("Celular 2"));
        phone2Text.setPreferredSize(new java.awt.Dimension(12, 40));

        emailText.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        emailText.setPreferredSize(new java.awt.Dimension(12, 40));

        cantMascText.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad de Mascotas"));
        cantMascText.setPreferredSize(new java.awt.Dimension(12, 40));

        jLabel1.setFont(new java.awt.Font("FreeSans", 3, 14)); // NOI18N
        jLabel1.setText("Datos Personales");

        birth.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Nacimiento"));

        alta.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Alta"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(cantMascText, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel1)))
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(birth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phone2Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuilText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dniText, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(surnameText, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(phone1Text, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(emailText, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(alta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dniText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(birth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuilText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phone1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(cantMascText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "DNI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 76, Short.MAX_VALUE))
        );

        modifyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar.png"))); // NOI18N
        modifyButton.setText("Modificar");
        modifyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modifyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrar.png"))); // NOI18N
        deleteButton.setText("Borrar");
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar.png"))); // NOI18N
        addButton.setText("Agregar");
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setPreferredSize(new java.awt.Dimension(95, 85));
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actualizar.png"))); // NOI18N
        updateButton.setText("Actualizar");
        updateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateButton.setPreferredSize(new java.awt.Dimension(95, 85));
        updateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        jLabel2.setFont(new java.awt.Font("FreeSans", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("Carga de Datos de Clientes");

        filterText.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));
        filterText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filtro.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(308, 308, 308))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Object[] oReturn = isValidAdd();
        boolean ok = (boolean) oReturn[0];
        if (ok) {
            Cliente pNuevo = (Cliente) oReturn[1];
            Clientes cnx = new Clientes();
            if (cnx.conectar()) {
                ok = cnx.isCreate(pNuevo);
                Clean();
            }
            if (ok) {
                modelo.addRow(pNuevo.getInfo());
                _addCliente();
                _showClientes(new Cliente());
            } else {
                JOptionPane.showMessageDialog(jPanel1, "No se pudo cargar los datos!", "Error!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jPanel1, "Verifique los datos ingresados!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para modificar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = isValidModify();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Cliente cModificar = (Cliente) oReturn[1];
                Clientes cnx = new Clientes();
                    if (cnx.conectar()) {
                        ok = cnx.isModify(cModificar);
                        Clean();
                        JOptionPane.showMessageDialog(null, "Registro modificado con exito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _addCliente();
                          
                    } else {
                        JOptionPane.showMessageDialog(jPanel1, "No se pudo actualizar!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
             else {
                JOptionPane.showMessageDialog(jPanel1, "Controle los datos!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para eliminar un registro, seleccionelo", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = isValidAdd();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Cliente cEliminar = (Cliente) oReturn[1];
                String msj = "¿Esta seguro de eliminar el registro por completo? ";
                if (JOptionPane.showConfirmDialog(jPanel1, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    Clientes cnx = new Clientes();
                    if (cnx.conectar()) {
                        ok = cnx.isDelete(cEliminar);
                        _addCliente();
                        JOptionPane.showMessageDialog(jPanel1, "Registro eliminado con exito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(jPanel1, "No se elimino al usuario", "Error", JOptionPane.WARNING_MESSAGE);
                      }  
            } else {
                JOptionPane.showMessageDialog(jPanel1, "Controle los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }
         }
        }
        System.out.println("Hola mundo");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
         _addCliente();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void filterTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextActionPerformed
        _filter(filterText.getText().trim());
    }//GEN-LAST:event_filterTextActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        _select();
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressText;
    private com.toedter.calendar.JDateChooser alta;
    private com.toedter.calendar.JDateChooser birth;
    private javax.swing.JTextField cantMascText;
    private javax.swing.JTextField cuilText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField dniText;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField filterText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton modifyButton;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField phone1Text;
    private javax.swing.JTextField phone2Text;
    private javax.swing.JTextField surnameText;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
