/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

/**
 *
 * @author Administrador
 */
public enum FingerEnum {

    // Pulgar derecho

    RIGHT_THUMB(0),
    // Indice derecho
    RIGHT_INDEX(1),
    // Medio derecho
    RIGHT_MIDDLE(2),
    // Anular derecho
    RIGHT_RING(3),
    // Menique derecho
    RIGHT_LITTLE(4),
    // Pulgar izquierdo
    LEFT_THUMB(5),
    // Indice izquierdo
    LEFT_INDEX(6),
    // Medio izquierdo
    LEFT_MIDDLE(7),
    // Anular izquierdo
    LEFT_RING(8),
    // Menique izquierdo
    LEFT_LITTLE(9);

    /**
     * Valor del enum
     */
    private Integer value;

    /**
     * Setea el valor del enum
     *
     * @param value Valor del enum
     */
    FingerEnum(final Integer value) {
        this.value = value;
    }

    /**
     * Regresa el valor del enum
     *
     * @return Valor del enum
     */
    public Integer getValue() {
        return value;
    }
}
