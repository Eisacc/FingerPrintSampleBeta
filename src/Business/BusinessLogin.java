/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Model.Usuario;
import Util.UtilFunctions;
import comunes.ConexionDb;

/**
 *
 * @author Administrador
 */
public class BusinessLogin {

    /**
     *
     * @param usuario
     * @return
     */
    public Boolean loginUser(final Usuario usuario) {
        Boolean login = Boolean.FALSE;
        if (UtilFunctions.isNotNull(usuario)) {
            ConexionDb conexionDb = ConexionDb.getConnectionDb();
            conexionDb.setUsuario(usuario.getUser());
            conexionDb.setPassword(usuario.getPassword());
            conexionDb.initConnection();
            if (UtilFunctions.isNotNull(conexionDb.getConexion())) {
                login = Boolean.TRUE;
            }

        }
        return login;
    }

}
