/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Empleado;
import java.util.List;

/**

HOHOHOHO

 *
 * @author Administrador
 */
public interface DaoEmpleado {
      /**
     *
     * @param entidad
     * @return
     */
    public Empleado create(Empleado entidad);

    /**
     *
     * @param entidad
     * @return
     */
    public boolean update(Empleado entidad);

    /**
     *
     * @return
     */
    public List<Empleado> findAll();

    /**
     *
     * @param entidad
     * @return
     */
    public Empleado findById(Empleado entidad);
}
