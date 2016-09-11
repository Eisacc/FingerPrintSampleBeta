/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Constantes.UtilConstants;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrador
 */
public class UtilFunctions {

    /**
     *
     * @param object
     * @return
     */
    public static Boolean isBoolean(final Object object) {
        Boolean reBoolean = null;
        if (object instanceof Boolean) {
            reBoolean = (Boolean) object;
        }
        return reBoolean;
    }

    /**
     *
     * @param object
     * @return
     */
    public static Boolean isNotNull(final Object object) {
        Boolean reBoolean = Boolean.TRUE;
        if (object == null) {
            reBoolean = Boolean.FALSE;
        }
        return reBoolean;
    }

    /**
     *
     * @param object
     * @return
     */
    public static Boolean isNotNullOrEmptyString(final Object object) {
        Boolean reBoolean = Boolean.FALSE;
        String cadena = null;
        if (isNotNull(object) && object instanceof String) {
            cadena = (String) object;
            cadena = cadena.trim();
            cadena = cadena.equals(UtilConstants.STRING_EMPTY) ? null : cadena;
            if (isNotNull(cadena)) {
                reBoolean = Boolean.TRUE;
            }
        } else if (isNotNull(object)) {
            reBoolean = Boolean.TRUE;

        }
        return reBoolean;
    }

    /**
     *
     * @param pareComponent
     * @return
     */
    public static File selectImage(final Component pareComponent) {
        final JFileChooser chooser = new JFileChooser();
        File file = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                UtilConstants.TYPE_IMAGE_MSJ, UtilConstants.TYPE_IMAGE);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(pareComponent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            System.out.println("" + file.toPath());
        }

        return file;
    }

    /**
     *
     * @param file
     * @return
     */
    public static byte[] fileToArrayBytes(final File file) {
        byte[] arrayBytesFile = null;
        try {
            if (isNotNull(file) && file.exists()) {
                arrayBytesFile = Files.readAllBytes(file.toPath());
            }
        } catch (IOException ex) {

        }
        return arrayBytesFile;
    }

    /**
     *
     * @param arrayBytes
     * @return
     */
    public static ImageIcon arrayBytesToImage(final byte[] arrayBytes) {
        ImageIcon imageIcon = null;
        if (isNotNull(arrayBytes)) {
            imageIcon = new ImageIcon(arrayBytes);
        }
        return imageIcon;
    }

}
