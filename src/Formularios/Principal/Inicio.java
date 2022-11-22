/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios.Principal;

import javax.swing.ImageIcon;

/**
 *
 * @author emi
 */
import static java.lang.Thread.sleep;
public class Inicio extends javax.swing.JFrame {

  
    public Inicio() {
        initComponents();
        setLocationRelativeTo(null); //Muestra en el centro de la pantalla el frame.
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logoPrincipal.png")).getImage());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loading = new javax.swing.JProgressBar();
        titulo = new javax.swing.JLabel();
        materia = new javax.swing.JLabel();
        alumno = new javax.swing.JLabel();
        emi = new javax.swing.JLabel();
        dani = new javax.swing.JLabel();
        textCarga = new javax.swing.JLabel();
        imagenFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loading.setBackground(new java.awt.Color(153, 153, 153));
        loading.setFont(new java.awt.Font("Bitstream Charter", 3, 18)); // NOI18N
        loading.setForeground(new java.awt.Color(255, 102, 0));
        loading.setStringPainted(true);
        jPanel1.add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 730, 20));

        titulo.setFont(new java.awt.Font("Bitstream Charter", 3, 40)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 102, 0));
        titulo.setText("HOTEL POCHITA");
        jPanel1.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 384, 50));

        materia.setFont(new java.awt.Font("Bitstream Charter", 3, 18)); // NOI18N
        materia.setForeground(new java.awt.Color(255, 102, 0));
        materia.setText("Materia: Programación II");
        jPanel1.add(materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        alumno.setFont(new java.awt.Font("Bitstream Charter", 3, 18)); // NOI18N
        alumno.setForeground(new java.awt.Color(255, 102, 0));
        alumno.setText("Alumnos: ");
        jPanel1.add(alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        emi.setFont(new java.awt.Font("Bitstream Charter", 3, 18)); // NOI18N
        emi.setForeground(new java.awt.Color(255, 102, 0));
        emi.setText("Nuñez, Emilia");
        jPanel1.add(emi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        dani.setFont(new java.awt.Font("Bitstream Charter", 3, 18)); // NOI18N
        dani.setForeground(new java.awt.Color(255, 102, 0));
        dani.setText("Urban, Daniel");
        jPanel1.add(dani, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        textCarga.setForeground(new java.awt.Color(255, 102, 0));
        jPanel1.add(textCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 730, 20));

        imagenFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        jPanel1.add(imagenFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
 
         Inicio ini = new Inicio();
         ini.setVisible(true);
         
        try {
            for( int i=0;i<=100;i++){
                sleep(40);
                ini.loading.setValue(i);
                ini.textCarga.setText("Cargando la aplicación...");
                if (i==100){
                    ini.setVisible(false);
                    FrmPrincipal f = new FrmPrincipal();
                    f.setVisible(true);
                }
            }
            
        } catch (Exception e) {}
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alumno;
    private javax.swing.JLabel dani;
    private javax.swing.JLabel emi;
    private javax.swing.JLabel imagenFondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar loading;
    private javax.swing.JLabel materia;
    private javax.swing.JLabel textCarga;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
