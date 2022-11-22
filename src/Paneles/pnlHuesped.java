/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles;

import Datos.Huespedes;
import Entidades.Habitacion;
import Entidades.Huesped;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class pnlHuesped extends javax.swing.JPanel {

   private DefaultListModel<String> mHuespedLista = null;
    private DefaultListModel<String> mHuespedListTmp = null;
    private TreeMap<String, Huesped> listaHuesped = null;
    private TableRowSorter<TableModel> elQueOrdena = null;
    
    
    /**
     * Creates new form pnlHabitacion
     */
    public pnlHuesped() {
        initComponents();
        _init();
    }
     public static boolean esCorreo(String a) {
        return a != null && a.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
     
     private void _init() {
        mHuespedLista = new DefaultListModel<>();
        mHuespedListTmp = new DefaultListModel<>();
        listaHuesped = new TreeMap<String, Huesped>();

        Huespedes cnx = new Huespedes();
        ArrayList<Huesped> listaHues = new ArrayList<Huesped>();
        if (cnx.conectar()) {
            listaHues = cnx.getList("SELECT * FROM huespedes ORDER BY apellido");
        }
        for (int index = 0; index < listaHues.size(); index++) {
            Huesped h = listaHues.get(index);
            String cKey = h.getApellido() + ", " + h.getNombres() + ", " + h.getNro_dni();
            mHuespedLista.addElement(cKey);
            listaHuesped.put(cKey, h);
        }
        listaHuespedes.setModel(mHuespedLista);
    }
     
    private void _limpiar() {
        apellidoTxt.setText("");
        nombresTxt.setText("");
        dniTxt.setText("");
        cuilText.setText("");
        celularTxt.setText("");
        emailTxt.setText("");
        provinciaTxt.setSelectedIndex(0);
        ciudadTxt.setText("");
        fechaNacimiento.setCalendar(null);
        fechaAlta.setCalendar(null);
        observacionesTxt.setText("");
    }
    
     private void _filtar1(String texto) {
        if (texto.isBlank()) {
            listaHuespedes.setModel(mHuespedLista);
        } else {
            mHuespedListTmp.clear();
            for (int index = 0; index < mHuespedLista.size(); index++) {
                String infoList = mHuespedLista.getElementAt(index).toString().trim();
                if (infoList.startsWith(texto)) {
                    mHuespedListTmp.addElement(infoList);
                }
            }
            listaHuespedes.setModel(mHuespedListTmp);
        }
    }
     
    private void _seleccionar() {
        String cKey = listaHuespedes.getSelectedValue();

        idTxt.setText("");
        apellidoTxt.setText("");
        dniTxt.setText("");
        cuilText.setText("");
        celularTxt.setText("");
        emailTxt.setText("");
        fechaNacimiento.setDate(null);
        fechaAlta.setDate(null);
        ciudadTxt.setText("");
        observacionesTxt.setText("");

        if (listaHuesped.containsKey(cKey)) {
            Huesped h = listaHuesped.get(cKey);
            idTxt.setText(h.getId() + "");
            apellidoTxt.setText(h.getApellido().trim());
            nombresTxt.setText(h.getNombres().trim());
            dniTxt.setText(h.getNro_dni().toString());
            cuilText.setText(h.getCuil().trim());
            celularTxt.setText(h.getCelular().toString());
            emailTxt.setText(h.getEmail());
            provinciaTxt.setSelectedItem(h.getProvincia());
            ciudadTxt.setText(h.getCiudad());
            fechaNacimiento.setDate(h.getFecha_nacimiento());
            fechaAlta.setDate(h.getFecha_alta());
            observacionesTxt.setText(h.getObservaciones());
        }
    }
    
    // Metodos de validacion de textfield.
    private boolean _validarCamposEnBlanco(String nombres, String apellido, String dni, String cuil,
            String celular, String email, Date fechaNac, Date fechaAlta1, String ciudad) {

        boolean ok = true;

        if (nombres.isEmpty() || apellido.isEmpty() || dni.isEmpty() || cuil.isEmpty() || celular.isEmpty()
                || email.isEmpty() || fechaNac == null || fechaAlta1 == null || ciudad.isEmpty()) {
            ok = false;
            JOptionPane.showMessageDialog(null, "No se permiten campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }
    
    //Validacion de huesped que se agrega
    private Object[] _validar() {
        boolean ok = false;
        Huesped h = new Huesped();

        Object[] oResult = new Object[2];
        oResult[0] = ok;

        String apellido = apellidoTxt.getText().trim();
        String nombres = nombresTxt.getText().trim();
        String dni = dniTxt.getText().trim();
        String cuil = cuilText.getText().trim();
        String celular = celularTxt.getText().trim();
        String email = emailTxt.getText();
        String ciudad = ciudadTxt.getText();
        Date fechaNac = fechaNacimiento.getDate();
        Date fechaAlta1 = fechaAlta.getDate();

        if (_validarCamposEnBlanco(apellido, nombres, dni, cuil, celular, email, fechaNac, fechaAlta1, ciudad)) {

            apellido = (apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase());
            nombres = (nombres.substring(0, 1).toUpperCase() + nombres.substring(1).toLowerCase());
            ciudad = (ciudad.substring(0, 1).toUpperCase() + ciudad.substring(1).toLowerCase());
            h.setApellido(apellido);
            h.setNombres(nombres);
            h.setNro_dni(dniTxt.getText().trim());
            h.setCuil(cuilText.getText().trim());
            h.setCelular(celularTxt.getText().trim());
            h.setCiudad(ciudad);
            h.setProvincia(provinciaTxt.getSelectedItem().toString());
            h.setFecha_nacimiento(fechaNac);
            h.setFecha_alta(fechaAlta1);
            h.setObservaciones(observacionesTxt.getText().trim());

            //Valida que el correo ingresado sea uno valido
            if (esCorreo(email)) {
                h.setEmail(email);
                ok = true;
            } else { //Si no, arroja un mensaje, deja el campo vacio y recupera el foco
                JOptionPane.showMessageDialog(null, "¡El CORREO no es valido!", "¡Atención!", JOptionPane.WARNING_MESSAGE);
                emailTxt.setText("");
                emailTxt.requestFocus();
                ok = false;
                oResult[0] = ok;
                return oResult;
            }

            try {
                if (idTxt.getText().trim().isEmpty()) {
                    h.setId(0);
                } else {
                    h.setId(Integer.parseInt(idTxt.getText()));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se produjo un error", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                if ((h.isValidar())) {
                    ok = true;

                }
            }
        }
        Object[] miObjeto = new Object[2];
        miObjeto[0] = ok;
        miObjeto[1] = h;
        return miObjeto;
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistros = new javax.swing.JPanel();
        pnlFiltrar = new javax.swing.JPanel();
        filtrar = new javax.swing.JTextField();
        pnlLista = new javax.swing.JPanel();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaHuespedes = new javax.swing.JList<>();
        pnlDatos = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        btnAgregar = new Miselaneos.Btn();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        btnEditar = new Miselaneos.Btn();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        btnBorrar = new Miselaneos.Btn();
        pnlTitulo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        idTxt = new javax.swing.JLabel();
        apellidoTxt = new javax.swing.JTextField();
        nombresTxt = new javax.swing.JTextField();
        dniTxt = new javax.swing.JTextField();
        celularTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        provinciaTxt = new javax.swing.JComboBox<>();
        ciudadTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesTxt = new javax.swing.JTextArea();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        fechaAlta = new com.toedter.calendar.JDateChooser();
        cuilText = new javax.swing.JFormattedTextField();

        setPreferredSize(new java.awt.Dimension(1024, 431));
        setLayout(new java.awt.BorderLayout());

        pnlRegistros.setMaximumSize(new java.awt.Dimension(700, 491));
        pnlRegistros.setPreferredSize(new java.awt.Dimension(350, 491));
        pnlRegistros.setLayout(new java.awt.BorderLayout());

        pnlFiltrar.setLayout(new java.awt.BorderLayout());

        filtrar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtrar"));
        filtrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarKeyReleased(evt);
            }
        });
        pnlFiltrar.add(filtrar, java.awt.BorderLayout.CENTER);

        pnlRegistros.add(pnlFiltrar, java.awt.BorderLayout.PAGE_START);

        pnlLista.setLayout(new java.awt.BorderLayout());

        pnlTabla.setLayout(new java.awt.BorderLayout());

        listaHuespedes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaHuespedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaHuespedesMouseClicked(evt);
            }
        });
        listaHuespedes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listaHuespedesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(listaHuespedes);

        pnlTabla.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlLista.add(pnlTabla, java.awt.BorderLayout.CENTER);

        pnlRegistros.add(pnlLista, java.awt.BorderLayout.CENTER);

        add(pnlRegistros, java.awt.BorderLayout.CENTER);

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setMaximumSize(new java.awt.Dimension(408, 491));
        pnlDatos.setPreferredSize(new java.awt.Dimension(850, 491));
        pnlDatos.setLayout(new java.awt.BorderLayout());

        pnlBotones.setBackground(new java.awt.Color(255, 153, 51));
        pnlBotones.setPreferredSize(new java.awt.Dimension(400, 72));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregarPersona.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.setPreferredSize(new java.awt.Dimension(80, 60));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        pnlBotones.add(btnAgregar);
        pnlBotones.add(filler1);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 60));
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });
        pnlBotones.add(btnEditar);
        pnlBotones.add(filler2);

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.setPreferredSize(new java.awt.Dimension(80, 60));
        btnBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBorrarMouseClicked(evt);
            }
        });
        pnlBotones.add(btnBorrar);

        pnlDatos.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlTitulo.setBackground(new java.awt.Color(255, 153, 51));
        pnlTitulo.setLayout(new java.awt.BorderLayout());

        titulo.setBackground(new java.awt.Color(204, 102, 0));
        titulo.setFont(new java.awt.Font("Bitstream Charter", 3, 36)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Datos de Huesped");
        pnlTitulo.add(titulo, java.awt.BorderLayout.CENTER);

        pnlDatos.add(pnlTitulo, java.awt.BorderLayout.PAGE_START);

        pnlCampos.setBackground(new java.awt.Color(255, 255, 255));

        idTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "ID"));
        idTxt.setEnabled(false);

        apellidoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Apellido"));
        apellidoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoTxtKeyTyped(evt);
            }
        });

        nombresTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nombres"));
        nombresTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombresTxtKeyTyped(evt);
            }
        });

        dniTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DNI"));
        dniTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dniTxtKeyTyped(evt);
            }
        });

        celularTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Celular"));
        celularTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularTxtActionPerformed(evt);
            }
        });
        celularTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celularTxtKeyTyped(evt);
            }
        });

        emailTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Email"));

        provinciaTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buenos Aires", "Ciudad Autónoma de Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán" }));
        provinciaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Provincia"));

        ciudadTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Ciudad"));
        ciudadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ciudadTxtKeyTyped(evt);
            }
        });

        observacionesTxt.setColumns(20);
        observacionesTxt.setRows(5);
        observacionesTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Observaciones"));
        jScrollPane1.setViewportView(observacionesTxt);

        fechaNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha de Nacimiento"));

        fechaAlta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha de Alta"));

        cuilText.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuil"));
        try {
            cuilText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(provinciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ciudadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(94, 94, 94)
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombresTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                    .addComponent(cuilText)))))
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(provinciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ciudadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cuilText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pnlDatos.add(pnlCampos, java.awt.BorderLayout.CENTER);

        add(pnlDatos, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void celularTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celularTxtKeyTyped
       int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (celularTxt.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_celularTxtKeyTyped

    private void dniTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dniTxtKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (dniTxt.getText().trim().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_dniTxtKeyTyped

    private void apellidoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoTxtKeyTyped
         //Con este evento, no deja que el usuario ingrese números o caracteres especiales en el textfield.
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
        if (apellidoTxt.getText().trim().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_apellidoTxtKeyTyped

    private void nombresTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresTxtKeyTyped
       //Con este evento, no deja que el usuario ingrese números o caracteres especiales en el textfield.
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
        if (nombresTxt.getText().trim().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_nombresTxtKeyTyped

    private void ciudadTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ciudadTxtKeyTyped
        //Con este evento, no deja que el usuario ingrese números o caracteres especiales en el textfield.
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
        if (ciudadTxt.getText().trim().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_ciudadTxtKeyTyped

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        Object[] oReturn = _validar();
        boolean ok = (boolean) oReturn[0];
        if (ok) {
            Huesped hNuevo = (Huesped) oReturn[1];

            Huespedes cnx = new Huespedes();
            if (cnx.conectar()) {
                ok = cnx.isIngresar(hNuevo);
                _init();
                _limpiar();
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        //Boton para modificar datos, se debe seleccionar un registro
        String cKey = listaHuespedes.getSelectedValue();
        if (!listaHuesped.containsKey(cKey)) {
            JOptionPane.showMessageDialog(null, "Para modificar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Huesped hModif = (Huesped) oReturn[1];
                String msj = "¿Desea modificar los datos?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Huespedes cnx = new Huespedes();
                    if (cnx.conectar()) {
                        ok = cnx.isModificar(hModif);
                        JOptionPane.showMessageDialog(null, "Registro modificado con exito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _seleccionar();
                        _init();
                        _limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Controle los datos!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarMouseClicked

    private void btnBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMouseClicked
       //Boton para eliminar datos, se debe seleccionar un registro
        String cKey = listaHuespedes.getSelectedValue();
        if (!listaHuesped.containsKey(cKey)) {
            JOptionPane.showMessageDialog(null, "Para eliminar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Huesped hBorrar = (Huesped) oReturn[1];
                String msj = "¿Desea eliminar a " + hBorrar.getNombres().trim() + "?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Huespedes cnx = new Huespedes();
                    if (cnx.conectar()) {
                        ok = cnx.isEliminar(hBorrar);
                        _init();
                        _limpiar();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Controle los datos Cargados.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarMouseClicked

    private void listaHuespedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaHuespedesMouseClicked
          _seleccionar();
    }//GEN-LAST:event_listaHuespedesMouseClicked

    private void listaHuespedesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaHuespedesKeyReleased
        _seleccionar();
    }//GEN-LAST:event_listaHuespedesKeyReleased

    private void filtrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarKeyReleased
        _filtar1(filtrar.getText().trim());
    }//GEN-LAST:event_filtrarKeyReleased

    private void celularTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private Miselaneos.Btn btnAgregar;
    private Miselaneos.Btn btnBorrar;
    private Miselaneos.Btn btnEditar;
    private javax.swing.JTextField celularTxt;
    private javax.swing.JTextField ciudadTxt;
    private javax.swing.JFormattedTextField cuilText;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JTextField emailTxt;
    private com.toedter.calendar.JDateChooser fechaAlta;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField filtrar;
    private javax.swing.JLabel idTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaHuespedes;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextArea observacionesTxt;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlRegistros;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JComboBox<String> provinciaTxt;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
