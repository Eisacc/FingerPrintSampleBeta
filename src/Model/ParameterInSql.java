/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Administrador
 */
public class ParameterInSql<T> {

    /**
     * indexParam
     */
    private Integer indexParam;

    /**
     * type
     */
    private int type;

    /**
     * value
     */
    private T valor;

    /**
     *
     * @param indexParam
     * @param valor
     * @param type
     */
    public ParameterInSql(final Integer indexParam, final T valor, final int type) {
        this.indexParam = indexParam;
        this.valor = valor;
        this.type = type;
    }

    /**
     *
     */
    public ParameterInSql() {
    }

    /**
     * @return the indexParam
     */
    public Integer getIndexParam() {
        return indexParam;
    }

    /**
     * @param indexParam the indexParam to set
     */
    public void setIndexParam(Integer indexParam) {
        this.indexParam = indexParam;
    }

    /**
     * @return the valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

}
