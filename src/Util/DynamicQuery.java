/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Constantes.ConstantesSqlParams;
import Constantes.QueryConstantesSQL;
import Model.ParameterInSql;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DynamicQuery {

    /**
     * stringBuilder
     */
    private final StringBuilder stringBuilder;
    /**
     * listParametros
     */
    private final List<ParameterInSql> listParametros;

    /**
     * countParameters
     */
    private Integer countParameters = 1;

    /**
     *
     * @param string
     */
    public DynamicQuery(final String string) {
        stringBuilder = new StringBuilder(string);
        listParametros = new ArrayList<>();

    }

    /**
     *
     * @param nameParametro
     * @param value
     * @return
     */
    public DynamicQuery appendParameter(final String nameParametro, final Object value) {
        if (UtilFunctions.isNotNullOrEmptyString(value)
                && UtilFunctions.isNotNull(nameParametro)) {
            addWhereOrAnd(nameParametro, value);
        }
        return this;
    }

    /**
     *
     * @param str
     * @return
     */
    public DynamicQuery appendString(String str) {
        stringBuilder.append(" ").append(str).append(" ");
        return this;
    }

    /**
     *
     * @param nameParametro
     * @param value
     */
    private void addWhereOrAnd(final String nameParametro, Object value) {
        String cadena = null;
        if (stringBuilder.indexOf(QueryConstantesSQL.WHERE) == -1) {

            stringBuilder.append(" ").append(QueryConstantesSQL.WHERE).append(" ");
            if (value instanceof String) {
                stringBuilder.append(nameParametro).append(" LIKE ? ");
                cadena = (String) value;
                cadena = cadena + "%";
                value = cadena;
            } else {
                stringBuilder.append(nameParametro).append(" = ? ");
            }

            getListParametros().add(new ParameterInSql<>(countParameters, value, getTypeParameter(value)));

        } else if (stringBuilder.indexOf(QueryConstantesSQL.WHERE) > -1) {
            stringBuilder.append(" ").append(QueryConstantesSQL.AND).append(" ");
            if (value instanceof String) {
                stringBuilder.append(nameParametro).append(" LIKE ? ");
                cadena = (String) value;
                cadena = cadena + "%";
                value = cadena;
            } else {
                stringBuilder.append(nameParametro).append(" = ? ");
            }
            getListParametros().add(new ParameterInSql<>(countParameters, value, getTypeParameter(value)));
        }
        countParameters = countParameters + 1;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    /**
     *
     * @param object
     * @return
     */
    public int getTypeParameter(Object object) {
        int type = 0;
        if (object instanceof String) {
            type = ConstantesSqlParams.TYPE_STRING;
        } else if (object instanceof Integer) {
            type = ConstantesSqlParams.TYPE_INTEGER;
        } else if (object instanceof Date) {
            type = ConstantesSqlParams.TYPE_DATE;
        } else if (object instanceof byte[]) {
            type = ConstantesSqlParams.TYPE_BINARY;
        }

        return type;
    }

    /**
     * @return the listParametros
     */
    public List<ParameterInSql> getListParametros() {
        return listParametros;
    }

}
