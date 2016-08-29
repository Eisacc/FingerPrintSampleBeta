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
import Model.HuellaEmpleado;
import Model.ParameterInSql;
import com.google.gson.reflect.TypeToken;
import comunes.DaoGenericImplements;
import comunes.LoadProperties;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class DaoHuellaEmpleadoImplements extends DaoGenericImplements<HuellaEmpleado> implements DaoHuellaEmplado {

    /**
     * mapSqlQuerys
     */
    private Map<String, String> mapSqlQuerys;

    public DaoHuellaEmpleadoImplements() {
        super();
        try {
            final Type type = new TypeToken<List<HuellaEmpleado>>() {
            }.getType();
            setType(type);
            setGeneric(HuellaEmpleado.class);
            LoadProperties loadProperties = new LoadProperties(ServerDbConstantes.FILE_PROPERTIES_SQL_HUELLAS_EMPLEADO);
            loadProperties.loadFileProperties();

            mapSqlQuerys = loadProperties.getPropertiesValues();
        } catch (IOException ex) {
            System.out.println("Errror cargando Archivo" + ex.getMessage());
        }

    }

    @Override
    public HuellaEmpleado create(HuellaEmpleado entidad) {
        final List<ParameterInSql> listParametros = new ArrayList<>();
        listParametros.add(new ParameterInSql<>(1, entidad.getIdEmpleado(), ConstantesSqlParams.TYPE_INTEGER));
        listParametros.add(new ParameterInSql<>(2, entidad.getIdDedo(), ConstantesSqlParams.TYPE_INTEGER));
        listParametros.add(new ParameterInSql<>(3, entidad.getHuella(), ConstantesSqlParams.TYPE_BINARY));

        HuellaEmpleado huellaEmpleado = create(mapSqlQuerys.get(QueryConstantesSQL.SQL_INSERT), listParametros);

        return huellaEmpleado;
    }

    @Override
    public boolean update(HuellaEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HuellaEmpleado> findAll(HuellaEmpleado entidad) {
        final List<ParameterInSql> listParametros = new ArrayList<>();
        listParametros.add(new ParameterInSql(1, entidad.getIdEmpleado(), ConstantesSqlParams.TYPE_INTEGER));
        List<HuellaEmpleado> list = findAll(mapSqlQuerys.get(QueryConstantesSQL.SQL_SELECT), listParametros);
        return list;
    }

    @Override
    public HuellaEmpleado findById(HuellaEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HuellaEmpleado> findAll() {
        final List<ParameterInSql> listParametros = new ArrayList<>();
        List<HuellaEmpleado> list = findAll(mapSqlQuerys.get(QueryConstantesSQL.SQL_SELECT_ALL), listParametros);
        return list;
    }

}
