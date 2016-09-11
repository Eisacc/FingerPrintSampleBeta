/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Constantes.ConstantesSqlParams;
import Model.ParameterInSql;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.sql.Types;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author Vidal
 */
public class HelperSqlParams {

    /**
     *
     * @param preparedStatement
     * @param listParameters
     */
    public static void fillParameters(final PreparedStatement preparedStatement, final List<ParameterInSql> listParameters) {
        for (ParameterInSql parameter : listParameters) {
            try {
                switch (parameter.getType()) {
                    case ConstantesSqlParams.TYPE_INTEGER:
                        preparedStatement.setObject(parameter.getIndexParam(), parameter.getValor() == null ? null : (int) parameter.getValor());
                        break;
                    case ConstantesSqlParams.TYPE_STRING:
                        preparedStatement.setString(parameter.getIndexParam(), (String) parameter.getValor());
                        break;
                    case ConstantesSqlParams.TYPE_DATE:
                        Date fechaUtil = parameter.getValor() == null ? null : (Date) parameter.getValor();
                        java.sql.Date fecha = fechaUtil == null ? null : new java.sql.Date(fechaUtil.getTime());
                        preparedStatement.setDate(parameter.getIndexParam(), fecha);
                        break;
                    case ConstantesSqlParams.TYPE_BINARY:
                        byte arrayBytes[] = parameter.getValor() == null ? null : (byte[]) parameter.getValor();
                        ByteArrayInputStream arrayInputStream = arrayBytes == null ? null : new ByteArrayInputStream(arrayBytes);
                        preparedStatement.setBinaryStream(parameter.getIndexParam(), arrayInputStream, arrayInputStream == null ? 0 : arrayBytes.length);
                        break;

                }
            } catch (Exception error) {
                System.out.println("Ocurrio un error al llenar los parametros" + error.getMessage());
            }
        }
    }

    /**
     *
     * @param metaDatos
     * @return
     * @throws SQLException
     */
    public static String[] getColumNamesQuery(final ResultSetMetaData metaDatos) throws SQLException {
        String columNames[] = new String[0];
        final int numberOfColums;
        if (metaDatos != null) {
            numberOfColums = metaDatos.getColumnCount();
            columNames = new String[numberOfColums];
            for (int i = 0; i < numberOfColums; i++) {
                columNames[i] = metaDatos.getColumnName(i + 1);
            }
        }
        return columNames;
    }

    /**
     *
     * @param resulSet
     */
    public static JsonArray parseSqlRowsJson(final ResultSet resulSet) {
        final JsonArray arrayJson = new JsonArray();

        try {
            final ResultSetMetaData resultSetMetaData = resulSet.getMetaData();
            final int numColumns = resultSetMetaData.getColumnCount();
            int numRow = 1;
            int numColum = 1;
            final String[] columNames = getColumNamesQuery(resultSetMetaData);
            while (resulSet.next()) {
                JsonObject jsonObject = fillJsonObjectWithRowSQL(resulSet, resultSetMetaData, columNames);
                arrayJson.add(jsonObject);
            }

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error");
        }
        return arrayJson;
    }

    /**
     *
     * @param resultSet
     * @param resultSetMetaData
     * @param columNames
     * @return JsonObject
     */
    public static JsonObject fillJsonObjectWithRowSQL(final ResultSet resultSet, final ResultSetMetaData resultSetMetaData, final String[] columNames) {
        final JsonObject jsonObject = new JsonObject();
        try {
            final int currentRow = resultSet.getRow();
            int type = 0;
            int numColum = 1;
            for (String columName : columNames) {
                type = resultSetMetaData.getColumnType(numColum);
                switch (type) {
                    case Types.BINARY:
                        final byte[] arrayBytes = resultSet.getBytes(columName);
                        String base64Binario = arrayBytes == null ? null : new String(Base64.getEncoder().encode(arrayBytes));
                        jsonObject.addProperty(columName, base64Binario);
                        break;
                    case Types.INTEGER:
                        final int datoInt = resultSet.getInt(columName);
                        jsonObject.addProperty(columName, datoInt);
                        break;
                    case Types.VARCHAR:
                        final String datoString = resultSet.getString(columName);
                        jsonObject.addProperty(columName, datoString);
                        break;
                    case Types.DATE:
                        Date date = resultSet.getDate(columName);
                        Long fechaLong = date == null ? null : date.getTime();
                        jsonObject.addProperty(columName, fechaLong);
                        break;

                }

                numColum = numColum + 1;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return jsonObject;
    }

}
