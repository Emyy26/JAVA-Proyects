/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles;

import Datos.Habitaciones;
import Datos.Personales;
import Entidades.Habitacion;
import Entidades.Huesped;
import Entidades.Personal;
import static Paneles.pnlHuesped.esCorreo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class pnlPersonal extends javax.swing.JPanel {

  private DefaultTableModel modelo = null;//nuevo modelo de tabla
    private TableRowSorter<TableModel> elQueOrdena = null;
    private TreeMap<String, Personal> listaPersonal = null;
    
    
    /**
     * Creates new form pnlHabitacion
     */
     public pnlPersonal() {
        initComponents();
        _seleccionar();
        _cargarPersonal();
        _tabla();
    }
     
     //Funcion para validar que sea un correo electronico.
    public static boolean esCorreo(String a) {
        return a != null && a.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    //Configuracion de la tabla, de los datos mas importantes a mostrar
    public void _tabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cargo");

        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tabla.setModel(modelo);
        tabla.setRowSorter(elQueOrdena);
        

        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(140);
        tabla.getTableHeader().setFont(new Font("MS Reference Sans Serif", Font.BOLD, 12));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(32, 136, 203));
        tabla.getTableHeader().setForeground(new Color(255, 255, 255));
        tabla.setRowHeight(25);
    }
    //Limpia la tabla

    private void _limpiarModelo() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }
    
    private void _limpiar() {
        apellidoTxt.setText("");
        nombresTxt.setText("");
        dniTxt.setText("");
        cuilText.setText("");
        celularTxt.setText("");
        emailTxt.setText("");
        fechaAlta.setCalendar(null);
        sexoCombo.setSelectedIndex(0);
        tipoPersonalCombo.setSelectedIndex(0);
        observacionesTxt.setText("");
    }
    
    //Carga de personal en treemap
    public void _cargarPersonal() {
        //Si Los botones no estan habilitados(Enabled) no seguir adelante (return)
        if (!btnAgregar.isEnabled()) {
            return;
        }
        if (!btnEditar.isEnabled()) {
            return;
        }
        if (!btnBorrar.isEnabled()) {
            return;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                btnAgregar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnBorrar.setEnabled(false);

                _limpiarModelo();

                Personales cnx = new Personales();

                ArrayList<Personal> list = new ArrayList<Personal>();

                if (cnx.conectar()) {
                    list = cnx.getList("SELECT * FROM personal");
                }

                listaPersonal = new TreeMap<String, Personal>();

                for (int index = 0; index < list.size(); index++) {
                    Personal p = list.get(index);
                    modelo.addRow(p.getInfo());
                    String key = p.getId() + "";
                    listaPersonal.put(key, p);
                }

                btnAgregar.setEnabled(true);
                btnEditar.setEnabled(true);
                btnBorrar.setEnabled(true);
            }
        });
        t.start();
    }
    
    public void _seleccionar() {
        String key = "";
        Personal p = null;
        try {
            int indexTable = tabla.getSelectedRow();
            int indexModelo = tabla.convertRowIndexToModel(indexTable);

            key = modelo.getValueAt(indexModelo, 0).toString();
            if (listaPersonal.containsKey(key)) {
                p = listaPersonal.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _mostrarPersonales(p);
        }
    }
    
     //Filtro de datos del personal
    public void _filtar(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter("(?i)" + texto.trim()));
        }
    }
    
     public void _mostrarPersonales(Personal per) {
        String id = "";
        String apellido = "";
        String nombres = "";
        String dni = "";
        String cuil = "";
        String celular = "";
        String email = "";
        String tipoPersonal = "";
        Date fecha_Alta = null;
        String sexo = "";
        String observaciones = "";

        if (per != null) {
            id = per.getId() + "";
            apellido = per.getApellido().trim();
            nombres = per.getNombres().trim();
            dni = per.getNro_dni() + "";
            cuil = per.getCuil() + "";
            celular = per.getCelular().trim();
            email = per.getEmail().trim();
            tipoPersonal = per.getTipoPersonal().trim();
            fecha_Alta = per.getFecha_alta();
            sexo = per.getSexo();
            observaciones = per.getObservaciones();
        }
        idTxt.setText(id);
        apellidoTxt.setText(apellido);
        nombresTxt.setText(nombres);
        dniTxt.setText(dni);
        cuilText.setText(cuil);
        celularTxt.setText(celular);
        emailTxt.setText(email);
        tipoPersonalCombo.setSelectedItem(tipoPersonal);
        fechaAlta.setDate(fecha_Alta);
        sexoCombo.setSelectedItem(sexo);
        observacionesTxt.setText(observaciones);
    }
     
     // Metodos de validacion de textfield.
    private boolean _validarCamposEnBlanco(String nombres, String apellido, String dni, String cuil,
            String celular, String email, Date fechaAlta1) {

        boolean ok = true;

        if (nombres.isEmpty() || apellido.isEmpty() || dni.isEmpty() || cuil.isEmpty() || celular.isEmpty()
                || email.isEmpty() || fechaAlta1 == null) {
            ok = false;
            JOptionPane.showMessageDialog(null, "No se permiten campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }
    
    //Validacion de huesped que se agrega
    private Object[] _validar() {
        boolean ok = false;
        Personal p = new Personal();

        Object[] oResult = new Object[2];
        oResult[0] = ok;

        String apellido = apellidoTxt.getText().trim();
        String nombres = nombresTxt.getText().trim();
        String dni = dniTxt.getText().trim();
        String cuil = cuilText.getText().trim();
        String celular = celularTxt.getText().trim();
        String email = emailTxt.getText();
        Date fechaAlta1 = fechaAlta.getDate();

        if (_validarCamposEnBlanco(apellido, nombres, dni, cuil, celular, email, fechaAlta1)) {

            apellido = (apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase());
            nombres = (nombres.substring(0, 1).toUpperCase() + nombres.substring(1).toLowerCase());
            p.setApellido(apellido);
            p.setNombres(nombres);
            p.setNro_dni(dniTxt.getText().trim());
            p.setCuil(cuilText.getText().trim());
            p.setCelular(celularTxt.getText().trim());
            p.setFecha_alta(fechaAlta1);
            p.setTipoPersonal(tipoPersonalCombo.getSelectedItem().toString());
            p.setSexo(sexoCombo.getSelectedItem().toString());
            p.setObservaciones(observacionesTxt.getText().trim());

            //Valida que el correo ingresado sea uno valido
            if (esCorreo(email)) {
                p.setEmail(email);
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
                    p.setId(0);
                } else {
                    p.setId(Integer.parseInt(idTxt.getText()));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se produjo un error", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                if ((p.isValidar())) {
                    ok = true;

                }
            }
        }

        Object[] miObjeto = new Object[2];
        miObjeto[0] = ok;
        miObjeto[1] = p;
        return miObjeto;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistros = new javax.swing.JPanel();
        pnlFiltrar = new javax.swing.JPanel();
        filtrar = new javax.swing.JTextField();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
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
        tipoPersonalCombo = new javax.swing.JComboBox<>();
        fechaAlta = new com.toedter.calendar.JDateChooser();
        sexoCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesTxt = new javax.swing.JTextArea();
        cuilText = new javax.swing.JFormattedTextField();

        setPreferredSize(new java.awt.Dimension(1024, 431));
        setLayout(new java.awt.BorderLayout());

        pnlRegistros.setMaximumSize(new java.awt.Dimension(700, 491));
        pnlRegistros.setPreferredSize(new java.awt.Dimension(650, 491));
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

        pnlTabla.setLayout(new java.awt.BorderLayout());

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        pnlTabla.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlRegistros.add(pnlTabla, java.awt.BorderLayout.CENTER);

        add(pnlRegistros, java.awt.BorderLayout.CENTER);

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setMaximumSize(new java.awt.Dimension(408, 491));
        pnlDatos.setPreferredSize(new java.awt.Dimension(700, 491));
        pnlDatos.setLayout(new java.awt.BorderLayout());

        pnlBotones.setBackground(new java.awt.Color(255, 153, 51));
        pnlBotones.setPreferredSize(new java.awt.Dimension(400, 72));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/personal.png"))); // NOI18N
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
        titulo.setText("Datos de Personal");
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
        celularTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celularTxtKeyTyped(evt);
            }
        });

        emailTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Email"));

        tipoPersonalCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recepcionista", "Chef", "Spa", "Seguridad", "Limpieza" }));
        tipoPersonalCombo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tipo de personal"));

        fechaAlta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha de Alta"));

        sexoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        sexoCombo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Sexo"));

        observacionesTxt.setColumns(20);
        observacionesTxt.setRows(5);
        observacionesTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Observaciones"));
        jScrollPane1.setViewportView(observacionesTxt);

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
                .addGap(314, 314, 314)
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCamposLayout.createSequentialGroup()
                                        .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                                        .addComponent(tipoPersonalCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                    .addComponent(fechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCamposLayout.createSequentialGroup()
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombresTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                    .addComponent(cuilText))))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                        .addComponent(sexoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286))))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuilText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoPersonalCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sexoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        pnlDatos.add(pnlCampos, java.awt.BorderLayout.CENTER);

        add(pnlDatos, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
         //Boton para agregar una habitacion
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Personal p = (Personal) oResult[1];
            Personales cnx = new Personales();
            if (cnx.conectar()) {
                boolean okNuevo = cnx.isIngresar(p);
                if (okNuevo) {
                    _cargarPersonal();
                    Personal hBlando = new Personal();
                    _mostrarPersonales(hBlando);
                } else {
                    JOptionPane.showMessageDialog(pnlCampos, "No Se Registro al personal.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(pnlCampos, "Controle los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMouseClicked
         //Boton para eliminar datos, se debe seleccionar un registro
        int index = tabla.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para borrar un registro, seleccionelo", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Personal pElim = (Personal) oReturn[1];
                String msj = "¿Desea borrar al personal: " + pElim.getApellido().trim() + ", " + pElim.getNombres().trim() + "?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    Personales cnx = new Personales();
                    if (cnx.conectar()) {
                        ok = cnx.isEliminar(pElim);
                        JOptionPane.showMessageDialog(null, "Registro borrado con exito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarPersonal();
                        _limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se borro al usuario", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Controle los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarMouseClicked

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
         //Boton para modificar datos
        int index = tabla.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para modificar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Personal pModif = (Personal) oReturn[1];
                String msj = "¿Desea modificar los datos del personal?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Personales cnx = new Personales();
                    if (cnx.conectar()) {
                        ok = cnx.isModificar(pModif);
                        JOptionPane.showMessageDialog(null, "Registro modificado con exito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarPersonal();
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

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
       _seleccionar();
    }//GEN-LAST:event_tablaMouseClicked

    private void apellidoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoTxtKeyTyped
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

    private void filtrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarKeyReleased
        _filtar(filtrar.getText().trim());
    }//GEN-LAST:event_filtrarKeyReleased
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private Miselaneos.Btn btnAgregar;
    private Miselaneos.Btn btnBorrar;
    private Miselaneos.Btn btnEditar;
    private javax.swing.JTextField celularTxt;
    private javax.swing.JFormattedTextField cuilText;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JTextField emailTxt;
    private com.toedter.calendar.JDateChooser fechaAlta;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField filtrar;
    private javax.swing.JLabel idTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextArea observacionesTxt;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlRegistros;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JComboBox<String> sexoCombo;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> tipoPersonalCombo;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
