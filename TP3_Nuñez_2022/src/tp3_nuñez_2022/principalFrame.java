/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tp3_nuñez_2022;


import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author emi
 */
public class principalFrame extends javax.swing.JFrame {

    /**
     * Creates new form principalFrame
     */
    public principalFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(0, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 153, 0));
        jButton1.setText("Ejercicio 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 204, 204));
        jButton2.setText("Ejercicio 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 255, 255));
        jButton3.setForeground(new java.awt.Color(204, 102, 255));
        jButton3.setText("Ejercicio 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 255, 255));
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Ejercicio 4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Trabajo Práctico 3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addGap(0, 52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /* Dato un array con la información de 15 notas, hallar la nota mas alta. Posteriormente emitir
        la nota por consola.
        Nota: controlar si hay algun error en la carga de información en el array, corregir para
        poder hacer el ejercicio.*/
        double[] notas = new double[15];
            notas[0]=10.00d;
            notas[1]=5.51d;
            notas[2]=8.21d;
            notas[3]=3.63d;
            notas[4]=7.21d;
            notas[5]=6.15d;
            notas[6]=9.00d;
            notas[7]=8.00d;
            notas[8]=7.81d;
            notas[9]=8.05d;
            notas[10]=6.60d;
            notas[11]=4.15d;
            notas[12]=4.15d;
            notas[13]=4.15d;
            notas[14]=4.15d;
  
      
        double p = -99.99;    
        for (int i = 0;i < notas.length;i++){
            double nota = notas[i];
            if(nota > p){
                p = nota;
                
            }
        }
        System.out.println("La nota más alta es: "+p);
            
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Dado un ArrayList con información de notas, sacar su promedio.
        ArrayList<Double> notas = new ArrayList<Double>();
            notas.add(10.00d);
            notas.add(5.51d);
            notas.add(8.21d);
            notas.add(3.63d);
            notas.add(7.21d);
            notas.add(6.15d);
            notas.add(9.00d);
            notas.add(8.00d);
            notas.add(7.81d);
            notas.add(8.05d);
            notas.add(6.60d);
            notas.add(4.15d);
            notas.add(4.15d);
            notas.add(4.15d);
            notas.add(4.15d);
            
        Iterator<Double> iterador1 = notas.iterator();
        int cant = notas.size();
        Double valor1 = 0.0;
            while (iterador1.hasNext()){
                valor1 += iterador1.next();  
            }
            Double promedio = valor1 / cant;
            
        System.out.println("La suma total es: "+valor1);
        System.out.println("El promedio es: "+promedio);
            
            
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /*Utilizando el array del Ejercicio 2, hallar la posición de la nota mas alta. Posteriormente
        emitir la posición y su nota por consola.*/
            double[] notas = new double[15];
            notas[0]=10.00d;
            notas[1]=5.51d;
            notas[2]=8.21d;
            notas[3]=3.63d;
            notas[4]=7.21d;
            notas[5]=6.15d;
            notas[6]=9.00d;
            notas[7]=8.00d;
            notas[8]=7.81d;
            notas[9]=8.05d;
            notas[10]=6.60d;
            notas[11]=4.15d;
            notas[12]=4.15d;
            notas[13]=4.15d;
            notas[14]=4.15d;
  
      
        double p = -99.99;   
        int posicion = 0;
        for (int i = 0;i < notas.length;i++){
            double nota = notas[i];
            if(nota > p){
                p = nota;
                posicion = i;
                
                
            }
        }
        System.out.println("La nota más alta es: "+p+" y su posicion es: "+posicion);
        
           
           
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        /*Dada una lista de personas(ArrayList), separar y cargar en otras 2 listas( listFemeninos y
        listMasculinos), teniendo en cuenta el criterio de sexo, si es F=femenino y M=masculino.
        Información a considerar para el ejercicio:
        Crear una clase Persona con dos atributos o propiedades; nombre y sexo(F/M).
        Cargar en un ArrayList de personas, 5 personas con diferentes sexos.
        Por ultimo emitir por consola el ArrayList listFemeninos y listMasculinos.*/
        ArrayList<Persona> personas = new ArrayList<Persona>();

        Persona p1 = new Persona();
        p1.setNombre("Juan");
        p1.setSexo("M");
        personas.add(p1);
        
        Persona p2 = new Persona();
        p2.setNombre("Anna");
        p2.setSexo("F");
        personas.add(p2);
        
        Persona p3 = new Persona();
        p3.setNombre("Pedro");
        p3.setSexo("M");
        personas.add(p3);
        
        Persona p4 = new Persona();
        p4.setNombre("Emilia");
        p4.setSexo("F");
        personas.add(p4);
        
        Persona p5 = new Persona();
        p5.setNombre("Gian");
        p5.setSexo("M");
        personas.add(p5);
        
        ArrayList<Persona> listFemeninos = new ArrayList<Persona>();
        ArrayList<Persona> listMasculinos = new ArrayList<Persona>();
        
        for(int index=0;index<personas.size();index++){
            Persona p = personas.get(index);
            if(p.getSexo().equals("F")){
                listFemeninos.add(p);
                
            }else{
                listMasculinos.add(p);
            }
        }
        for (int i = 0; i <= listMasculinos.size()-1; i++) { 
                System.out.println("Nombre: " + listMasculinos.get(i).getNombre() +"\n"+"Sexo: "
                        + listMasculinos.get(i).getSexo());}
        for (int i = 0; i <= listFemeninos.size()-1; i++) { 
                System.out.println("Nombre: " + listFemeninos.get(i).getNombre() +"\n"+"Sexo: "
                        + listFemeninos.get(i).getSexo());}
        
    System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(principalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
