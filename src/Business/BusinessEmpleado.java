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

/**
 *
 * @author Administrador
 */
public class BusinessEmpleado {

    private DaoEmpleado daoEmpleado;

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

}
