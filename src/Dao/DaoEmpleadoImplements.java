/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Constantes.ConstantesSqlParams;
import Constantes.QueryConstantesSQL;
import Constantes.ServerDbConstantes;
import Model.Empleado;
import Model.ParameterInSql;
import com.google.gson.reflect.TypeToken;
import comunes.DaoGenericImplements;
import comunes.LoadProperties;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vidal
 */
public class DaoEmpleadoImplements extends DaoGenericImplements<Empleado> implements DaoEmpleado {

    /**
     * mapSqlQuerys
     */
    private Map<String, String> mapSqlQuerys = new HashMap<>();

    /**
     * DaoEmpleadoImplements
     */
    public DaoEmpleadoImplements() {
        super();
        try {
            final Type type = new TypeToken<List<Empleado>>() {
            }.getType();
            setType(type);
            setGeneric(Empleado.class);
            LoadProperties loadProperties = new LoadProperties(ServerDbConstantes.FILE_PROPERTIES_SQL_EMPLEADOS);
            loadProperties.loadFileProperties();

            mapSqlQuerys = loadProperties.getPropertiesValues();
        } catch (IOException ex) {
            System.out.println("Errror cargando Archivo" + ex.getMessage());
        }

    }

    /**
     *
     * @param entidad
     * @return
     */
    @Override
    public Empleado create(final Empleado entidad) {
        Empleado empleado = entidad;
        List<ParameterInSql> listParametros = new ArrayList<>();
        listParametros.add(new ParameterInSql<>(1, entidad.getRfc(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(2, entidad.getCurp(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(3, entidad.getNombre(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(4, entidad.getApellidoPaterno(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(5, entidad.getApellidoMaterno(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(6, entidad.getGenero(), ConstantesSqlParams.TYPE_STRING));
        listParametros.add(new ParameterInSql<>(7, entidad.getFechaNacimiento(), ConstantesSqlParams.TYPE_DATE));
        listParametros.add(new ParameterInSql<>(8, entidad.getEdad(), ConstantesSqlParams.TYPE_INTEGER));
        listParametros.add(new ParameterInSql<>(9, entidad.getFoto(), ConstantesSqlParams.TYPE_BINARY));

        Empleado object = create(mapSqlQuerys.get(QueryConstantesSQL.SQL_INSERT), listParametros);

        return object;
    }

    /**
     *
     * @param entidad
     * @return
     */
    @Override
    public boolean update(Empleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public List<Empleado> findAll() {
        List<ParameterInSql> listParametros = new ArrayList<>();
        List<Empleado> empleados = findAll(mapSqlQuerys.get(QueryConstantesSQL.SQL_SELECT), listParametros);
        return empleados;
    }

    /**
     *
     * @param entidad
     * @return
     */
    @Override
    public Empleado findById(Empleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
