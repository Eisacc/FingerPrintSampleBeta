/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Constantes.QueryConstantesSQL;
import Constantes.ServerDbConstantes;
import Model.HuellaDedos;
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
public class DaoHuellaDedosImplements extends DaoGenericImplements<HuellaDedos> implements DaoHuellaDedos {

    /**
     * mapSqlQuerys
     */
    private Map<String, String> mapSqlQuerys;

    /**
     *
     */
    public DaoHuellaDedosImplements() {
        super();
        try {
            final Type type = new TypeToken<List<HuellaDedos>>() {
            }.getType();
            setType(type);
            setGeneric(HuellaDedos.class);
            LoadProperties loadProperties = new LoadProperties(ServerDbConstantes.FILE_PROPERTIES_SQL_HUELLA_DEDOS);
            loadProperties.loadFileProperties();
            mapSqlQuerys = loadProperties.getPropertiesValues();
        } catch (IOException ex) {
            System.out.println("Errror cargando Archivo" + ex.getMessage());
        }

    }

    @Override
    public List<HuellaDedos> findAll() {
        List<ParameterInSql> listParametros = new ArrayList<>();
        List<HuellaDedos> huellaDedoses = findAll(mapSqlQuerys.get(QueryConstantesSQL.SQL_SELECT), listParametros);
        return huellaDedoses;
    }

}
