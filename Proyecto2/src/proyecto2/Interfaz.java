/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JOptionPane;

/**
 *
 * @author fabba
 */
public class Interfaz extends javax.swing.JFrame {
    Listas resumenes = new Listas();
    Funciones func = new Funciones();
    boolean guion = false, sin = false;
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
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

        jPanel1 = new javax.swing.JPanel();
        agg = new javax.swing.JButton();
        buscarPC = new javax.swing.JButton();
        buscarAut = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agg.setText("Agregar archivo");
        agg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggActionPerformed(evt);
            }
        });
        jPanel1.add(agg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        buscarPC.setText("Buscar por Palabra Clave");
        buscarPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPCActionPerformed(evt);
            }
        });
        jPanel1.add(buscarPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        buscarAut.setText("Buscar por autores");
        buscarAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarAutActionPerformed(evt);
            }
        });
        jPanel1.add(buscarAut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel1.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggActionPerformed
        JFileChooser choos = new JFileChooser(new File("c:\\"));
        choos.setDialogTitle("Save a file");
        choos.showSaveDialog(null);
        
        try{
            File abre = choos.getSelectedFile();
            FileReader fr = new FileReader(abre.getPath());
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            String autores = "", resumen = "", title = "", pClaves = "";
            boolean pri = true;
            boolean auto = false;
            boolean resu = false;
            while((linea = br.readLine())!= null){
                if(pri){
                    title = linea;
                    pri = false;
                }
                
                if("".equals(linea)){
                    auto = resu = false;                    
                }
                
                if(linea.contains("Palabras claves: ")){
                    String[] aux = linea.split(": ");
                    pClaves = aux[1];
                }
                
                if(auto){
                    autores += linea + ",";
                }
                if(resu){
                    resumen += linea + "\n";
                }
                
                if("Autores".equals(linea)){
                    auto = true;
                    resu = false;
                }
                if("Resumen".equals(linea)){
                    auto = false;
                    resu = true;
                }
            }
            boolean plagio = false;
            if(resumenes.getSize() != 0){
                for (int i = 0; i < resumenes.getSize(); i++) {
                    if(title.equals(resumenes.getArray()[i].getTitle())){
                        JOptionPane.showMessageDialog(null, "A eso mi pana, se le conoce como plagio.");
                        plagio = true;
                    }
                }
                if(!plagio){
                    resumenes.agg(title, autores, resumen, pClaves);
                }
            }else{
                resumenes.agg(title, autores, resumen, pClaves);                
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_aggActionPerformed

    private void buscarPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPCActionPerformed
        String ans = JOptionPane.showInputDialog("Inserte la palabra clave que desea buscar: ");
        ans = ans.toLowerCase();
        Nodos aux = resumenes.getArray()[resumenes.getFirst()];
        int aux2 = resumenes.getFirst();
        int fin = -1;
        for (int i = 0; i < resumenes.getSize(); i++) {
            String[] claves = resumenes.getArray()[aux2].getpClave().split(", ");
            for (int j = 0; j < claves.length; j++) {
                if(ans.equals(claves[j].toLowerCase())){
                    JOptionPane.showMessageDialog(null, aux2 + ". " + aux.getTitle());
                    fin = aux2;
                    while(true){
                        try{
                            int ans2 = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número que está al lado del resumen que desea: "));
                            if(ans2 != aux2){
                                JOptionPane.showMessageDialog(null,"Inserte un número válido");
                            }else{
                                break;
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Inserte un número válido");
                        }
                    }
                    break;
                }
            }
            if(aux.getNext()!=-1){
                aux2 = aux.getNext();
                aux = resumenes.getArray()[aux.getNext()];
            }
        }
        if(fin==-1){
            JOptionPane.showMessageDialog(null,"No se encontró ningún resumen con esa palabra clave");
        }else{
            String ojito = resumenes.print(fin);
            System.out.println(ojito);
        }
        
    }//GEN-LAST:event_buscarPCActionPerformed

    private void buscarAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarAutActionPerformed
        String ans = JOptionPane.showInputDialog("Inserte el nombre del autor que desea buscar: ");
        ans = ans.toLowerCase();
        Nodos aux = resumenes.getArray()[resumenes.getFirst()];
        int aux2 = resumenes.getFirst();
        int fin = -1;
        for (int i = 0; i < resumenes.getSize(); i++) {
            String[] nombres = resumenes.getArray()[aux2].getAutores().split(",");
            for (int j = 0; j < nombres.length; j++) {
                if(ans.equals(nombres[j].toLowerCase())){
                    JOptionPane.showMessageDialog(null, aux2 + ". " + aux.getTitle());
                    fin = aux2;
                    while(true){
                        try{
                            int ans2 = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número que está al lado del resumen que desea: "));
                            if(ans2 != aux2){
                                JOptionPane.showMessageDialog(null,"Inserte un número válido");
                            }else{
                                break;
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Inserte un número válido");
                        }
                    }
                    break;
                }
            }
            if(aux.getNext()!=-1){
                aux2 = aux.getNext();
                aux = resumenes.getArray()[aux.getNext()];
            }
        }
        if(fin==-1){
            JOptionPane.showMessageDialog(null,"No se encontró ningún resumen de ese autor");
        }else{
            String ojito = resumenes.print(fin);
            System.out.println(ojito);
        }
    }//GEN-LAST:event_buscarAutActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        JOptionPane.showMessageDialog(null,"La interfaz es horrible, pero al menos terminó el trimestre :)");
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agg;
    private javax.swing.JButton buscarAut;
    private javax.swing.JButton buscarPC;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
