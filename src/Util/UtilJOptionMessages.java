/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class UtilJOptionMessages {

    public static Boolean dialogShowConfirm(String message) {
        Boolean bool = Boolean.FALSE;
        int respuesta = JOptionPane.showConfirmDialog(null, message, "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            bool = Boolean.TRUE;
        }
        return bool;
    }

    public static void dialogShowInformation(String message) {

        JOptionPane.showMessageDialog(null, message, "Mensage Informativo", JOptionPane.INFORMATION_MESSAGE);

    }
}
