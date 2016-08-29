    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import Model.ParameterInSql;
import Util.GsonHelperTypeBytes;
import Util.GsonHelperTypeDate;
import Util.HelperSqlParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vidal
 * @param <T>
 */
public class DaoGenericImplements<T> {

    /**
     * conexion
     */
    private Connection conexion = null;
    private PreparedStatement preparedStatement;
    private Type type;
    private Class generic;

    /**
     * DaoGeneric
     *
     * @param type
     */
    public DaoGenericImplements() {
        conexion = ConexionDb.getConnectionDb().getConexion();
    }

    public void setType(Type type) {
        this.type = type;

    }

    /**
     *
     * @param sqlQuery
     * @param parameters
     * @return
     */
    public T create(final String sqlQuery, final List<ParameterInSql> parameters) {
        T t = null;
        if (conexion != null) {
            try {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeHierarchyAdapter(byte[].class, new GsonHelperTypeBytes());
                builder.registerTypeAdapter(Date.class, new GsonHelperTypeDate());
                Gson gson = builder.create();
                preparedStatement = conexion.prepareStatement(sqlQuery);
                HelperSqlParams.fillParameters(preparedStatement, parameters);
                if (preparedStatement.execute()) {
                    ResultSet resultSet = preparedStatement.getResultSet();
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    String[] columNames = HelperSqlParams.getColumNamesQuery(metaData);

                    while (resultSet.next()) {
                        JsonObject jsonObject = HelperSqlParams.fillJsonObjectWithRowSQL(resultSet, metaData, columNames);
                        t = (T) gson.fromJson(jsonObject, generic);
                    }

                }

                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(DaoGenericImplements.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return t;
    }

    /**
     *
     * @return
     */
    public boolean remove() {
        return true;
    }

    /**
     *
     * @return
     */
    public boolean update() {
        return true;
    }

    /**
     *
     * @param sqlQuery
     * @param parameters
     * @return
     */
    public List<T> findAll(final String sqlQuery, final List<ParameterInSql> parameters) {
        List<T> listObjects = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(byte[].class, new GsonHelperTypeBytes());
        builder.registerTypeAdapter(Date.class, new GsonHelperTypeDate());
        Gson gson = builder.create();

        if (conexion != null) {
            try {
                preparedStatement = conexion.prepareStatement(sqlQuery);
                HelperSqlParams.fillParameters(preparedStatement, parameters);
                ResultSet resultSet = preparedStatement.executeQuery();
                final JsonArray array = HelperSqlParams.parseSqlRowsJson(resultSet);
                listObjects = gson.fromJson(array, this.type);
                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(DaoGenericImplements.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listObjects;
    }

    /**
     *
     * @param sqlQuery
     * @return
     */
    public T findById(final String sqlQuery) {
        T object = null;

        return object;
    }

    /**
     * @param generic the generic to set
     */
    public void setGeneric(Class generic) {
        this.generic = generic;
    }

}
