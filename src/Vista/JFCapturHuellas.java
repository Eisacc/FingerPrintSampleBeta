/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import ApiHuella.DataAdapterInterface;
import ApiHuella.FingerPrintDataAdapter;
import Business.BusinessCapturarHuellas;
import Constantes.UtilConstants;
import Model.Empleado;
import Model.HuellaEmpleado;
import Util.Session;
import Util.UtilFunctions;
import Util.UtilJOptionMessages;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import comunes.JFrameBase;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrador
 */
public class JFCapturHuellas extends JFrameBase implements DataAdapterInterface {

    /**
     * fingerPrintDataAdapter
     */
    private FingerPrintDataAdapter fingerPrintDataAdapter;
    /**
     * businessCapturarHuellas
     */
    private BusinessCapturarHuellas businessCapturarHuellas;
    /**
     * operacionEnCurso
     */
    private Integer operacionEnCurso = 0;

    /**
     * empleado
     */
    private Empleado empleado;

    /**
     * Creates new form JFCapturHuellas
     */
    public JFCapturHuellas() {
        initComponents();
        setFrameCurrent(this);
        fingerPrintDataAdapter = new FingerPrintDataAdapter(this);
        fingerPrintDataAdapter.stopLector();

        businessCapturarHuellas = new BusinessCapturarHuellas();
        empleado = getEmpleado();
        businessCapturarHuellas.loadHuellasEmpleado(empleado);
        businessCapturarHuellas.fillComboDedos(jComboDedos);
        jBCapturarHuella.setEnabled(false);
        jBGuardar.setEnabled(false);
        JBActualizar.setEnabled(false);
        jBCancelar.setEnabled(Boolean.FALSE);

    }

    private Empleado getEmpleado() {
        Session session = Session.createInstanceSession();
        Empleado empleado = null;
        if (UtilFunctions.isNotNull(session.getObjectSession(UtilConstants.EMPLEADO_ALTA))) {
            empleado = (Empleado) session.getObjectSession(UtilConstants.EMPLEADO_ALTA);
        }
        return empleado;
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
        jLImageHuella = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboDedos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLHuella = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTLogDactilar = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jBGuardar = new javax.swing.JButton();
        JBActualizar = new javax.swing.JButton();
        jBCapturarHuella = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLImageHuella, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLImageHuella, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboDedos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboDedosActionPerformed(evt);
            }
        });

        jLabel1.setText("Dedos:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jComboDedos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboDedos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLHuella, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLHuella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTLogDactilar.setColumns(20);
        jTLogDactilar.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jTLogDactilar.setForeground(new java.awt.Color(255, 0, 51));
        jTLogDactilar.setRows(5);
        jScrollPane1.setViewportView(jTLogDactilar);

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        JBActualizar.setText("Actualizar");

        jBCapturarHuella.setText("Iniciar Captura");
        jBCapturarHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCapturarHuellaActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBCapturarHuella)
                .addGap(18, 18, 18)
                .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(JBActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGuardar)
                    .addComponent(JBActualizar)
                    .addComponent(jBCapturarHuella)
                    .addComponent(jBCancelar))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jComboDedosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDedosActionPerformed
        final int index = jComboDedos.getSelectedIndex();
        File file = businessCapturarHuellas.getImageDedoMano(index);
        ImageIcon icon = UtilFunctions.arrayBytesToImage(UtilFunctions.fileToArrayBytes(file));
        setImageIconJlabel(jLImageHuella, icon);

        switch (businessCapturarHuellas.operacionHuella(index)) {
            case UtilConstants.CREACION_HUELLA:
                jBGuardar.setEnabled(Boolean.FALSE);
                jBCapturarHuella.setEnabled(Boolean.TRUE);
                JBActualizar.setEnabled(Boolean.FALSE);
                jBCancelar.setEnabled(Boolean.FALSE);
                break;
            case UtilConstants.ACTUALIZACION_HUELLA:
                jBGuardar.setEnabled(Boolean.FALSE);
                jBCapturarHuella.setEnabled(Boolean.FALSE);
                JBActualizar.setEnabled(Boolean.FALSE);
                jBCancelar.setEnabled(Boolean.FALSE);

                break;
            case UtilConstants.SIN_ACCION:
                jBGuardar.setEnabled(Boolean.FALSE);
                jBCapturarHuella.setEnabled(Boolean.FALSE);
                JBActualizar.setEnabled(Boolean.FALSE);
                break;

        }


    }//GEN-LAST:event_jComboDedosActionPerformed

    private void jBCapturarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCapturarHuellaActionPerformed
        fingerPrintDataAdapter.initLector();
        UtilJOptionMessages.dialogShowInformation(UtilConstants.MSJ_INFORMATIVO_CAPTURA);
        jBCancelar.setEnabled(Boolean.TRUE);
        jComboDedos.setEnabled(Boolean.FALSE);
        jBCapturarHuella.setEnabled(Boolean.FALSE);
    }//GEN-LAST:event_jBCapturarHuellaActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        jComboDedos.setEnabled(Boolean.TRUE);
        jBCancelar.setEnabled(Boolean.FALSE);
        jBGuardar.setEnabled(Boolean.FALSE);
        HuellaEmpleado huellaEmpleado = new HuellaEmpleado();
        huellaEmpleado.setIdDedo(jComboDedos.getSelectedIndex());
        huellaEmpleado.setHuella(fingerPrintDataAdapter.getTemplate().serialize());
        huellaEmpleado.setIdEmpleado(empleado.getId());
        businessCapturarHuellas.createRegistroHuella(huellaEmpleado);

    }//GEN-LAST:event_jBGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(JFCapturHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFCapturHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFCapturHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFCapturHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFCapturHuellas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBActualizar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBCapturarHuella;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JComboBox jComboDedos;
    private javax.swing.JLabel jLHuella;
    private javax.swing.JLabel jLImageHuella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTLogDactilar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void dataAcquiredFinger(DPFPDataEvent e) {
        setImageIconJlabel(jLHuella, new ImageIcon(fingerPrintDataAdapter.getImageHuella()));
        if (fingerPrintDataAdapter.isIsReadyFingerPrint()) {
            this.jTLogDactilar.append("Huella Capturada Correctamente\n");
            jBGuardar.setEnabled(Boolean.TRUE);

        }
    }

    @Override
    public void errorReaderFingerDevice(DPFPErrorEvent dPFPErrorEvent) {

    }

    @Override
    public void readerConnectedDevice(DPFPReaderStatusEvent e) {
        this.jTLogDactilar.append("Dispositivo conectado \n");
    }

    @Override
    public void readerDisconnectDevice(DPFPReaderStatusEvent e) {
        this.jTLogDactilar.append("Dispositivo Desconectado\n");
    }

    @Override
    public void fingerTouchedDevice(DPFPSensorEvent event) {

    }

    @Override
    public void fingerGoneDevice(DPFPSensorEvent event) {

    }
}
