/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles;

import Datos.Habitaciones;
import Entidades.Habitacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nejo
 */
public class pnlHabitacion extends javax.swing.JPanel {

    private DefaultTableModel modelo = null;//nuevo modelo de tabla
    private TableRowSorter<TableModel> elQueOrdena = null;
    private TreeMap<String, Habitacion> listaHabitacion = null;

    /**
     * Creates new form pnlHabitacion
     */
    public pnlHabitacion() {
        initComponents();
        _tabla();
        tabla.getTableHeader().setFont(new Font("MS Reference Sans Serif", Font.BOLD, 12));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(32, 136, 203));
        tabla.getTableHeader().setForeground(new Color(255, 255, 255));
        tabla.setRowHeight(25);
        _seleccionar();
        _cargarHabitacion();
    }

    //Configuracion de la tabla, de los datos mas importantes a mostrar
    public void _tabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Titulo");
        modelo.addColumn("Capacidad");

        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tabla.setModel(modelo);
        tabla.setRowSorter(elQueOrdena);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(140);
    }

    //Limpia la tabla
    private void _limpiarModelo() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }

    //Vacia los campos de datos
    private void _limpiar() {
        tituloTxt.setText("");
        tarifaTxt.setText("");
        capacidadTxt.setText("");
        descripcionTxt.setText("");
        lblImagen.setIcon(null);
        lblImagen.setToolTipText("");
        tvCombo.setSelectedIndex(0);
        wifiCombo.setSelectedIndex(0);
        aireCombo.setSelectedIndex(0);
        descripcionTxt.setText("");
    }

    //Carga de habitaciones en treemap
    public void _cargarHabitacion() {
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

                Habitaciones cnx = new Habitaciones();

                ArrayList<Habitacion> list = new ArrayList<Habitacion>();

                if (cnx.conectar()) {
                    list = cnx.getList("SELECT * FROM habitaciones");
                }

                listaHabitacion = new TreeMap<String, Habitacion>();

                for (int index = 0; index < list.size(); index++) {
                    Habitacion h = list.get(index);
                    modelo.addRow(h.getInfo());
                    String key = h.getId() + "";
                    listaHabitacion.put(key, h);
                }

                btnAgregar.setEnabled(true);
                btnEditar.setEnabled(true);
                btnBorrar.setEnabled(true);
            }
        });
        t.start();

    }

    private Image cargarImagenV2(String id) {
        BufferedImage image = null;
        if (!id.trim().isBlank()) {
            Habitaciones cnx = new Habitaciones();
            String query = "SELECT * FROM habitaciones WHERE id=" + id;
            if (cnx.conectar()) {
                ArrayList<Habitacion> list = cnx.getList(query);
                if (list.size() > 0) {
                    try {
                        String pathFile = list.get(0).getImg().getDescription().trim();
                        image = ImageIO.read(new File(pathFile));
                    } catch (IOException ex) {
                        image = null;
                    } catch (NullPointerException ex) {
                        image = null;
                    }
                }
            }
        }
        return image;
    }

    public void _seleccionar() {
        String key = "";
        Habitacion h = null;
        try {
            int indexTable = tabla.getSelectedRow();
            int indexModelo = tabla.convertRowIndexToModel(indexTable);

            key = modelo.getValueAt(indexModelo, 0).toString();
            if (listaHabitacion.containsKey(key)) {
                h = listaHabitacion.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _mostrarHabitaciones(h);
        }
    }

    //Filtro de datos del personal
    public void _filtar(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter("(?i)" + texto.trim()));
        }
    }

    public void _mostrarHabitaciones(Habitacion hab) {
        String id = "";
        String titulo = "";
        String descripcion = "";
        String capacidad = "";
        String tarifa = "0.000,00";
        String tvCable = "";
        String wifi = "";
        String aireAcondicionado = "";
        ImageIcon imagen = null;
        if (hab != null) {
            id = hab.getId() + "";
            titulo = hab.getTitulo().trim();
            descripcion = hab.getDescripcion().trim();
            capacidad = hab.getCapacidad_pers() + "";
            tarifa = hab.getTarifa() + "";
            tvCable = hab.getTv().trim();
            wifi = hab.getWifi().trim();
            aireAcondicionado = hab.getAireAcondicionado().trim();
            Image img = cargarImagenV2(hab.getId() + "");
            try {
                imagen = new ImageIcon(img);
            } catch (NullPointerException ex) {

            }
        }
        idTxt.setText(id);
        tituloTxt.setText(titulo);
        capacidadTxt.setText(capacidad);
        tarifaTxt.setText(tarifa);
        tvCombo.setSelectedItem(tvCable);
        wifiCombo.setSelectedItem(wifi);
        aireCombo.setSelectedItem(aireAcondicionado);
        descripcionTxt.setText(descripcion);
        try {
            lblImagen.setIcon(imagen);
            lblImagen.setToolTipText(imagen.getDescription().trim());
        } catch (NullPointerException ex) {
            lblImagen.setToolTipText("");
        }
    }

    // Metodos de validacion de textfield.
    private boolean _validarCamposEnBlanco(String titulo, String descripcion, String cantidad, String tarifa) {

        boolean ok = true;

        if (titulo.isEmpty() || descripcion.isEmpty() || cantidad.isEmpty() || tarifa.isEmpty()) {
            ok = false;
            JOptionPane.showMessageDialog(null, "No se permiten campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }

    //Validacion de personal que se agrega
    private Object[] _validar() {
        boolean ok = false;
        Habitacion h = new Habitacion();
        String titulo = tituloTxt.getText().trim().toUpperCase();
        String descripcion = descripcionTxt.getText().trim().toUpperCase();
        String cantidad = capacidadTxt.getText().trim().toUpperCase();
        String tarifa = tarifaTxt.getText().trim();

        if (_validarCamposEnBlanco(titulo, descripcion, cantidad, tarifa)) {
            int capacidad = Integer.parseInt(cantidad);

            h.setTitulo(tituloTxt.getText().trim());
            h.setDescripcion(descripcionTxt.getText().trim());
            h.setCapacidad_pers(capacidad);
            h.setTarifa(tarifaTxt.getText().trim());
            h.setTv(tvCombo.getSelectedItem().toString());
            h.setWifi(wifiCombo.getSelectedItem().toString());
            h.setAireAcondicionado(aireCombo.getSelectedItem().toString());

            ImageIcon imgIcon = null;
            try {
                imgIcon = new ImageIcon(lblImagen.getToolTipText().trim());
            } catch (Exception ex) {
            }
            h.setImg(imgIcon);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        tituloTxt = new javax.swing.JTextField();
        tvCable = new javax.swing.JLabel();
        wifi = new javax.swing.JLabel();
        aireAcondicionado = new javax.swing.JLabel();
        tvCombo = new javax.swing.JComboBox<>();
        wifiCombo = new javax.swing.JComboBox<>();
        aireCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionTxt = new javax.swing.JTextArea();
        capacidadTxt = new javax.swing.JTextField();
        idTxt = new Miselaneos.TxtNro();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblImagen = new Labels.lbl();
        tarifaTxt = new javax.swing.JFormattedTextField();

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
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
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

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregarHabitacion.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.setPreferredSize(new java.awt.Dimension(80, 60));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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
        titulo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Datos de Habitación");
        pnlTitulo.add(titulo, java.awt.BorderLayout.CENTER);

        pnlDatos.add(pnlTitulo, java.awt.BorderLayout.PAGE_START);

        pnlCampos.setBackground(new java.awt.Color(255, 255, 255));

        tituloTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Titulo"));

        tvCable.setText("TV Cable:");

        wifi.setText("WiFi:");

        aireAcondicionado.setText("Aire Acondicionado:");

        tvCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        wifiCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        aireCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        descripcionTxt.setColumns(20);
        descripcionTxt.setRows(5);
        descripcionTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Descripción"));
        jScrollPane1.setViewportView(descripcionTxt);

        capacidadTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Capacidad"));
        capacidadTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capacidadTxtActionPerformed(evt);
            }
        });
        capacidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                capacidadTxtKeyTyped(evt);
            }
        });

        idTxt.setEditable(false);
        idTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("id"));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        lblImagen.setBackground(new java.awt.Color(255, 255, 255));
        lblImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Imagen"));
        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setText("");
        lblImagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lblImagen);

        tarifaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarifa"));
        try {
            tarifaTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("$#.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tarifaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifaTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCamposLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wifi)
                            .addComponent(aireAcondicionado)
                            .addComponent(tvCable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wifiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aireCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(capacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(tarifaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                .addGap(62, 62, 62))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarifaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tvCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tvCable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wifiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wifi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aireCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aireAcondicionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDatos.add(pnlCampos, java.awt.BorderLayout.CENTER);

        add(pnlDatos, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        //Boton para agregar una habitacion
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Habitacion h = (Habitacion) oResult[1];
            Habitaciones cnx = new Habitaciones();
            if (cnx.conectar()) {
                boolean okNuevo = cnx.isIngresar(h);
                if (okNuevo) {
                    _cargarHabitacion();
                    Habitacion hBlando = new Habitacion();
                    _mostrarHabitaciones(hBlando);
                } else {
                    JOptionPane.showMessageDialog(pnlCampos, "No Se Registro la Habitación.-", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(pnlCampos, "NO NO", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        _seleccionar();
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        _seleccionar();
    }//GEN-LAST:event_tablaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //Boton para agregar una habitacion
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Habitacion h = (Habitacion) oResult[1];
            Habitaciones cnx = new Habitaciones();
            if (cnx.conectar()) {
                boolean okNuevo = cnx.isIngresar(h);
                if (okNuevo) {
                    _cargarHabitacion();
                    Habitacion hBlando = new Habitacion();
                    _mostrarHabitaciones(hBlando);
                } else {
                    JOptionPane.showMessageDialog(pnlCampos, "No Se Registro la Habitación.-", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(pnlCampos, "NO NO", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void capacidadTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capacidadTxtKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (capacidadTxt.getText().trim().length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_capacidadTxtKeyTyped

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        //Boton para modificar datos
        int index = tabla.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para modificar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Habitacion hModif = (Habitacion) oReturn[1];
                String msj = "¿Desea modificar los datos de la habitación?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Habitaciones cnx = new Habitaciones();
                    if (cnx.conectar()) {
                        ok = cnx.isModificar(hModif);
                        JOptionPane.showMessageDialog(null, "Registro modificado con exito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarHabitacion();
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
        int index = tabla.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para eliminar un registro, seleccionelo", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Habitacion hElim = (Habitacion) oReturn[1];
                String msj = "¿Desea eliminar la habitación: " + hElim.getTitulo().trim() + "?";
                if (JOptionPane.showConfirmDialog(null, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    Habitaciones cnx = new Habitaciones();
                    if (cnx.conectar()) {
                        ok = cnx.isEliminar(hElim);
                        JOptionPane.showMessageDialog(null, "Registro eliminado con exito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarHabitacion();
                        _limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se elimino al usuario", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Controle los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarMouseClicked

    private void filtrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarKeyReleased
        _filtar(filtrar.getText().trim());
    }//GEN-LAST:event_filtrarKeyReleased

    private void capacidadTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capacidadTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capacidadTxtActionPerformed

    private void tarifaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifaTxtActionPerformed

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenMouseClicked
        JFileChooser fc = null;
        if (fc == null) {
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            fc.setFileFilter(imgFilter);
        }

        int returnVal = fc.showDialog(null, "Seleccionar Imagen");

        lblImagen.setToolTipText("");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File name = fc.getSelectedFile();
            float longuitudArch = name.length();
            int longMB = (int) longuitudArch / 1024000;
            if (longMB > 3) {
                JOptionPane.showMessageDialog(lblImagen, "El Archivo Supero la cantidad de MB permitido que es de 3 MB." + '\n'
                    + "Imposible cargar la Imagen.-", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                return;
            } else {
                lblImagen.setIcon(new ImageIcon(name.getPath()));
                lblImagen.setToolTipText(name.getPath());
            }
        }
        fc.setSelectedFile(null);
        
    }//GEN-LAST:event_lblImagenMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aireAcondicionado;
    private javax.swing.JComboBox<String> aireCombo;
    private Miselaneos.Btn btnAgregar;
    private Miselaneos.Btn btnBorrar;
    private Miselaneos.Btn btnEditar;
    private javax.swing.JTextField capacidadTxt;
    private javax.swing.JTextArea descripcionTxt;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField filtrar;
    private Miselaneos.TxtNro idTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Labels.lbl lblImagen;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlRegistros;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tabla;
    private javax.swing.JFormattedTextField tarifaTxt;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField tituloTxt;
    private javax.swing.JLabel tvCable;
    private javax.swing.JComboBox<String> tvCombo;
    private javax.swing.JLabel wifi;
    private javax.swing.JComboBox<String> wifiCombo;
    // End of variables declaration//GEN-END:variables
}
