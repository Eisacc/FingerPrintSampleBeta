/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.HuellaEmpleado;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface DaoHuellaEmplado {

    /**
     *
     * @param entidad
     * @return
     */
    public HuellaEmpleado create(HuellaEmpleado entidad);

    /**
     *
     * @param entidad
     * @return
     */
    public boolean update(HuellaEmpleado entidad);

    /**
     *
     * @param entidad
     * @return
     */
    public List<HuellaEmpleado> findAll(final HuellaEmpleado entidad);
    
     /**
     *
     * @return
     */
    public List<HuellaEmpleado> findAll();


    /**
     *
     * @param entidad
     * @return
     */
    public HuellaEmpleado findById(HuellaEmpleado entidad);
}
