/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class HuellaEmpleado {

    /**
     * idEmpleado
     */
    @SerializedName("empleado_id")
    private Integer idEmpleado;

    /**
     * idDedo
     */
    @SerializedName("dedo_id")
    private Integer idDedo;

    /**
     * huella
     */
    private byte[] huella;

    /**
     * fechaRegistro
     */
    @SerializedName("fecha_registro")
    private Date fechaRegistro;

    /**
     * @return the idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idDedo
     */
    public Integer getIdDedo() {
        return idDedo;
    }

    /**
     * @param idDedo the idDedo to set
     */
    public void setIdDedo(Integer idDedo) {
        this.idDedo = idDedo;
    }

    /**
     * @return the huella
     */
    public byte[] getHuella() {
        return huella;
    }

    /**
     * @param huella the huella to set
     */
    public void setHuella(byte[] huella) {
        this.huella = huella;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
