/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Constantes.UtilConstants;
import Dao.DaoEmpleado;
import Dao.DaoEmpleadoImplements;
import Model.Empleado;
import Util.UtilFunctions;
import Util.UtilJOptionMessages;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class BusinessEmpleado {

    /**
     * daoEmpleado
     */
    private final DaoEmpleado daoEmpleado;
    /**
     * empleados
     */
    private List<Empleado> empleados;

    public BusinessEmpleado() {
        daoEmpleado = new DaoEmpleadoImplements();
    }

    /**
     * createRegistroEmpleado
     *
     * @param empleado
     * @return
     */
    public Boolean createRegistroEmpleado(final Empleado empleado) {
        Boolean reBoolean = Boolean.FALSE;
        final Empleado empl = daoEmpleado.create(empleado);
        if (UtilFunctions.isNotNull(empl) && UtilFunctions.isNotNull(empl.getId())) {
            empleado.setId(empl.getId());
            UtilJOptionMessages.dialogShowInformation(UtilConstants.OK_REGISTRO_EMPLEADO);
            reBoolean = Boolean.TRUE;
        }
        return reBoolean;
    }

    /**
     *
     * @param jTable
     * @param emp
     */
    public void fillJtableEmpleados(final JTable jTable, final Empleado emp) {
        final DefaultTableModel defaultTableModel = new DefaultTableModel();
        final String[] columNames = {"Rfc", "Nombre", "Apellido Paterno", "Apellido Materno"};
        defaultTableModel.setColumnIdentifiers(columNames);
        if (UtilFunctions.isNotNull(jTable)) {
            empleados = daoEmpleado.findAll(emp);
            for (Empleado empleado : empleados) {
                Object[] valuesColumns = new Object[4];
                valuesColumns[0] = empleado.getRfc();
                valuesColumns[1] = empleado.getNombre();
                valuesColumns[2] = empleado.getApellidoPaterno();
                valuesColumns[3] = empleado.getApellidoMaterno();

                defaultTableModel.addRow(valuesColumns);
            }
            jTable.setModel(defaultTableModel);

        }
    }

    /**
     *
     * @param row
     * @return
     */
    public ImageIcon getFotoEmpleado(int row) {
        ImageIcon iconEmpleado = null;
        Empleado empleado = empleados.get(row);
        byte[] fotoEmpleado = empleado.getFoto();
        iconEmpleado = UtilFunctions.arrayBytesToImage(fotoEmpleado);

        if (!UtilFunctions.isNotNull(iconEmpleado)) {
            File fileImFile = new File(getClass().getClassLoader().getResource("files/sinImagen.png").getPath());
            fotoEmpleado = UtilFunctions.fileToArrayBytes(fileImFile);
            iconEmpleado = UtilFunctions.arrayBytesToImage(fotoEmpleado);
        }
        System.out.println("icon empleado" + iconEmpleado);
        return iconEmpleado;
    }

}
