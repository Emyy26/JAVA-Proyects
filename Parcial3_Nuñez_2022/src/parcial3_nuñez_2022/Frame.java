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
       _agregarClientesAlModelo();
       
       
        
    }
    
    // Metodos de interaccion con la tabla.
    public void _agregarClientesAlModelo() {
         _cleanTable();

        Clientes cnx = new Clientes();

        ArrayList<Cliente> detallesClientes = new ArrayList<>();

        if (cnx.conectar()) {
            detallesClientes = cnx.getList("SELECT * FROM Clientes");
        }

        listaClientes = new TreeMap<String, Cliente>();

        for (int i = 0; i < detallesClientes.size(); i++) {
            Cliente cl = detallesClientes.get(i);
            modelo.addRow(cl.getInfo());
            String key = cl.getId() + "";
            listaClientes.put(key, cl);
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
    private void _cleanTable() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }
    public void _filter(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter("(?i)"+texto.trim()));
        }
    }
    
    // Metodos de interaccion con los textfield
    public void _completarCampos(Cliente cli) {
        String id="";
        String apellido = "";
        String nombre = "";
        String domicilio = "";
        Date dateNac = null;
        Date dateAlta = null;
        String dni = "";
        String cuil = "";
        String cel1 = "";
        String cel2 = "";
        String email = "";
        String cant_masc = "1";
        
        if (cli != null) {
            id= cli.getId() + "";
            apellido = cli.getSurname().trim();
            nombre = cli.getName().trim();
            domicilio = cli.getAddress().trim();
            dateNac = cli.getDate_nac();
            dateAlta = cli.getDate_alta();
            dni = cli.getDni() + "";
            cuil = cli.getCuil().trim();
            cel1 = cli.getPhone1();
            cel2 = cli.getPhone2();
            email = cli.getEmail().trim();
            cant_masc = cli.getCant_masc() + "";
        }

        campoId.setText(id);
        surnameText.setText(apellido);
        nameText.setText(nombre);
        addressText.setText(domicilio);
        birth.setDate(dateNac);
        alta.setDate(dateAlta);
        dniText.setText(dni);
        cuilText.setText(cuil);
        phone1Text.setText(cel1);
        phone2Text.setText(cel2);
        emailText.setText(email);
        cantMascText.setText(cant_masc);
    }
    public void _seleccionarRegistroTabla() {
        // Pasa la informacion de la fila y completa los textfield.
        String key = "";
        Cliente cli = null;
        try {
            int indexTable = jTable1.getSelectedRow();
            int indexModelo = jTable1.convertRowIndexToModel(indexTable);

            key = modelo.getValueAt(indexModelo, 0).toString();
            if (listaClientes.containsKey(key)) {
                cli = listaClientes.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _completarCampos(cli);
        }
        
    }
    private void _limpiarCampos() {
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
    
    
    // Metodos de validacion de textfield.
    private boolean _validarCamposEnBlanco(String apellido, String nombre, String domicilio, Date fechaNac, Date fechaAlta, String doc, String cuil, String c1, String email, String cant) {
        
        boolean ok = true;
        
        if (apellido.isEmpty() || nombre.isEmpty() || domicilio.isEmpty() || fechaNac == null || fechaAlta == null || doc.isEmpty() || cuil.isEmpty() || c1.isEmpty() || email.isEmpty() || cant.isEmpty()) {
            ok = false;
            JOptionPane.showMessageDialog(null, "No se permiten campos en blanco", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }  
    private Object[] _validacionFinal() {
        boolean ok = false;
        Cliente cli = new Cliente();
        String apellido = surnameText.getText().trim().toUpperCase();
        String nombre = nameText.getText().trim().toUpperCase();
        String domicilio = addressText.getText().trim().toUpperCase();
        Date fechaNac = birth.getDate();
        Date fechaAlta = alta.getDate();
        String docEnString = dniText.getText().trim();
        String cuil = cuilText.getText().trim().toUpperCase();
        String cel1 = phone1Text.getText().trim();
        String cel2 = phone2Text.getText().trim();
        String email = emailText.getText().trim().toLowerCase();
        String cantMascEnString = cantMascText.getText().trim();
        
        if (_validarCamposEnBlanco(apellido, nombre, domicilio, fechaNac, fechaAlta, docEnString, cuil, cel1, email, cantMascEnString)) {
            int doc = Integer.parseInt(docEnString);
            int cantMasc = Integer.parseInt(cantMascEnString);
            
            cli.setSurname(apellido);
            cli.setName(nombre);
            cli.setAddress(domicilio);
            cli.setDate_nac(fechaNac);
            cli.setDate_alta(fechaAlta);
            cli.setDni(doc);
            cli.setCuil(cuil);
            cli.setPhone1(cel1);
            cli.setPhone2(cel2);
            cli.setEmail(email);
            cli.setCant_masc(cantMasc);
            
            try {
            if (campoId.getText().trim().isEmpty()) {
                cli.setId(0);
            } else {
                cli.setId(Integer.parseInt(campoId.getText()));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Se produjo un error", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if ((cli.isValidar())) {
                ok = true;
          
        }
          }
        }
        
        Object[] miObjeto = new Object[2];
        miObjeto[0] = ok;
        miObjeto[1] = cli;
        return miObjeto;
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
        emailText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        birth = new com.toedter.calendar.JDateChooser();
        alta = new com.toedter.calendar.JDateChooser();
        campoId = new javax.swing.JTextField();
        dniText = new javax.swing.JFormattedTextField();
        cuilText = new javax.swing.JFormattedTextField();
        phone1Text = new javax.swing.JFormattedTextField();
        phone2Text = new javax.swing.JTextField();
        cantMascText = new javax.swing.JTextField();
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
        nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextKeyTyped(evt);
            }
        });

        surnameText.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellidos"));
        surnameText.setPreferredSize(new java.awt.Dimension(12, 40));
        surnameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surnameTextActionPerformed(evt);
            }
        });
        surnameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                surnameTextKeyTyped(evt);
            }
        });

        addressText.setBorder(javax.swing.BorderFactory.createTitledBorder("Dirección"));
        addressText.setPreferredSize(new java.awt.Dimension(12, 40));

        emailText.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        emailText.setPreferredSize(new java.awt.Dimension(12, 40));

        jLabel1.setFont(new java.awt.Font("FreeSans", 3, 14)); // NOI18N
        jLabel1.setText("Datos Personales");

        birth.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Nacimiento"));
        birth.setPreferredSize(new java.awt.Dimension(12, 40));

        alta.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Alta"));
        alta.setPreferredSize(new java.awt.Dimension(12, 40));

        campoId.setEditable(false);
        campoId.setBorder(javax.swing.BorderFactory.createTitledBorder("Key"));
        campoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdActionPerformed(evt);
            }
        });

        dniText.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI"));
        try {
            dniText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dniText.setPreferredSize(new java.awt.Dimension(12, 40));

        cuilText.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuil"));
        try {
            cuilText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cuilText.setPreferredSize(new java.awt.Dimension(12, 40));

        phone1Text.setBorder(javax.swing.BorderFactory.createTitledBorder("Celular 1"));
        try {
            phone1Text.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        phone2Text.setBorder(javax.swing.BorderFactory.createTitledBorder("Celular 2"));
        phone2Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phone2TextKeyTyped(evt);
            }
        });

        cantMascText.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad de Mascotas"));
        cantMascText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantMascTextKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phone2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cuilText, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addComponent(birth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addressText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(emailText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(surnameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dniText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phone1Text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addGap(0, 75, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(cantMascText, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuilText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantMascText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
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
        filterText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterTextKeyReleased(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addComponent(jLabel2))
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
        //Agregar
        
        Object[] miNuevoObjeto = _validacionFinal();
        boolean ok = (boolean) miNuevoObjeto[0];
        if (ok) {
            Cliente clienteNuevo = (Cliente) miNuevoObjeto[1];
            Clientes cnx = new Clientes();
            if (cnx.conectar()) {
                ok = cnx.isCreate(clienteNuevo);
                _limpiarCampos();
            }
            if (ok) {
                modelo.addRow(clienteNuevo.getInfo());
                _agregarClientesAlModelo();
                _completarCampos(new Cliente());
            } else {
                JOptionPane.showMessageDialog(jTable1, "No se pudo cargar la informacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jTable1, "Los datos ingresados son erroneos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
       //Modificar
       Object[] miNuevoObjeto = _validacionFinal();
        boolean ok = (boolean) miNuevoObjeto[0];
        if (ok) {
            Cliente clienteNuevo = (Cliente) miNuevoObjeto[1];
            Clientes conexion = new Clientes();
            if (conexion.conectar()) {
                ok = conexion.isModify(clienteNuevo);
                _limpiarCampos();
                JOptionPane.showMessageDialog(jTable1, "Datos modificados", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            if (ok) {
                _agregarClientesAlModelo();
            } else {
                JOptionPane.showMessageDialog(jTable1, "No se pudo cargar la informacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jTable1, "Los datos ingresados son erroneos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //Borrar
        Object[] miNuevoObjeto = _validacionFinal();
        boolean ok = (boolean) miNuevoObjeto[0];
        if (ok) {
            Cliente clienteABorrar = (Cliente) miNuevoObjeto[1];
            String msj = "Desea Eliminar a " + clienteABorrar.getSurname().trim() + " " + clienteABorrar.getName()+ "?";
            if (JOptionPane.showConfirmDialog(deleteButton, msj, "ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Clientes cnx = new Clientes();
                if (cnx.conectar()) {
                    ok = cnx.isDelete(clienteABorrar);
                    _agregarClientesAlModelo();
                    _limpiarCampos();
                    JOptionPane.showMessageDialog(deleteButton, "Cliente eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(deleteButton, "No se pudo eliminar la informacion.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(deleteButton, "No se elimina al cliente.", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
     }
            
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        _agregarClientesAlModelo();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        _seleccionarRegistroTabla();
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void filterTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTextKeyReleased
        // TODO add your handling code here:
        _filter(filterText.getText().trim());
    }//GEN-LAST:event_filterTextKeyReleased
    
    private void nameTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextKeyTyped
        //Con este evento, no deja que el usuario ingrese números o caracteres especiales en el textfield.
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
        if (nameText.getText().trim().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_nameTextKeyTyped

    private void surnameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surnameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surnameTextActionPerformed

    private void surnameTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_surnameTextKeyTyped
        //Con este evento, no deja que el usuario ingrese números o caracteres espaciales en el textfield.
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
        if (surnameText.getText().trim().length() == 35) {
            evt.consume();
        }
    }//GEN-LAST:event_surnameTextKeyTyped

    private void campoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdActionPerformed

    private void phone2TextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone2TextKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (phone2Text.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_phone2TextKeyTyped

    private void cantMascTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantMascTextKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (cantMascText.getText().trim().length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_cantMascTextKeyTyped

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
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField cantMascText;
    private javax.swing.JFormattedTextField cuilText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JFormattedTextField dniText;
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
    private javax.swing.JFormattedTextField phone1Text;
    private javax.swing.JTextField phone2Text;
    private javax.swing.JTextField surnameText;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
