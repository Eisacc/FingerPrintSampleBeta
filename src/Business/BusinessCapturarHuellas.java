/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Constantes.UtilConstants;
import Dao.DaoHuellaDedos;
import Dao.DaoHuellaDedosImplements;
import Dao.DaoHuellaEmplado;
import Dao.DaoHuellaEmpleadoImplements;
import Model.HuellaDedos;
import Model.HuellaEmpleado;
import Util.UtilFunctions;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author Administrador
 */
public class BusinessCapturarHuellas {

    /**
     * mapaDedos
     */
    private Map<Integer, String> mapaDedos = new HashMap<>();

    /**
     * mapaDedos
     */
    private Map<Integer, byte[]> capturaHuellaDedos = new HashMap<>();

    /**
     * daoHuellaDedos
     */
    private final DaoHuellaDedos daoHuellaDedos;

    /**
     * daoHuellaEmplado
     */
    private final DaoHuellaEmplado daoHuellaEmplado;

    /**
     * pathImagesDedos
     */
    private Map<Integer, String> pathImagesDedos = new HashMap<>();

    /**
     * BusinessCapturarHuellas
     */
    public BusinessCapturarHuellas() {
        daoHuellaDedos = new DaoHuellaDedosImplements();
        daoHuellaEmplado = new DaoHuellaEmpleadoImplements();
        pathImagesDedos.put(0, "files/s");
        pathImagesDedos.put(1, "files/ic_pulgar_izquierdo-web.png");
        pathImagesDedos.put(2, "files/ic_indice_izquierdo-web.png");
        pathImagesDedos.put(3, "files/ic_medio_izquierdo-web.png");
        pathImagesDedos.put(4, "files/ic_anular_izquierdo-web.png");
        pathImagesDedos.put(5, "files/ic_menique_izquierdo-web.png");
        pathImagesDedos.put(6, "files/ic_pulgar_derecho-web.png");
        pathImagesDedos.put(7, "files/ic_indice_derecho-web.png");
        pathImagesDedos.put(8, "files/ic_medio_derecho-web.png");
        pathImagesDedos.put(9, "files/ic_anular_derecho-web.png");
        pathImagesDedos.put(10, "files/ic_menique_derecho-web.png");

        HuellaEmpleado he = new HuellaEmpleado();
        he.setIdEmpleado(107);
        List<HuellaEmpleado> Huellas = daoHuellaEmplado.findAll(he);
        for (HuellaEmpleado Huella : Huellas) {
            capturaHuellaDedos.put(Huella.getIdDedo(), Huella.getHuella());
        }

    }

    /**
     *
     * @param comboBox
     */
    public void fillComboDedos(final JComboBox comboBox) {
        if (UtilFunctions.isNotNull(comboBox)) {
            List<HuellaDedos> dedos = daoHuellaDedos.findAll();
            comboBox.addItem("");
            for (HuellaDedos dedo : dedos) {
                comboBox.addItem(dedo.getId() + " - " + dedo.getDescripcion());
            }
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public File getImageDedoMano(final Integer index) {
        File fileImage = null;
        try {
            getClass().getClassLoader().getResource(pathImagesDedos.get(index)).getPath();
            fileImage = new File(getClass().getClassLoader().getResource(pathImagesDedos.get(index)).getPath());
        } catch (Exception e) {
            fileImage = null;
        }
        return fileImage;
    }

    /**
     *
     * @param index
     */
    public int operacionHuella(int index) {
        int operacion = UtilConstants.CREACION_HUELLA;
        if (index == 0) {
            operacion = UtilConstants.SIN_ACCION;
        } else if (UtilFunctions.isNotNull(capturaHuellaDedos.get(index))) {
            operacion = UtilConstants.ACTUALIZACION_HUELLA;
        }
        return operacion;
    }

    public void createRegistroHuella(HuellaEmpleado huellaEmpleado) {
        capturaHuellaDedos.put(huellaEmpleado.getIdDedo(), huellaEmpleado.getHuella());
        daoHuellaEmplado.create(huellaEmpleado);

    }

    public List<HuellaEmpleado> findAllHuellas() {
        List<HuellaEmpleado> Huellas = null;
        Huellas = daoHuellaEmplado.findAll();
        return Huellas;
    }

}
