/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHuella;

import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;

/**
 *
 * @author Administrador
 */
public interface DataAdapterInterface {

    /**
     *
     * @param e
     */
    public void dataAcquiredFinger(DPFPDataEvent e);

    /**
     *
     * @param dPFPErrorEvent
     */
    public void errorReaderFingerDevice(DPFPErrorEvent dPFPErrorEvent);

    /**
     *
     * @param e
     */
    public void readerConnectedDevice(DPFPReaderStatusEvent e);

    /**
     *
     * @param e
     */
    public void readerDisconnectDevice(DPFPReaderStatusEvent e);

    /**
     *
     * @param event
     */
    public void fingerTouchedDevice(DPFPSensorEvent event);

    /**
     *
     * @param event
     */
    public void fingerGoneDevice(DPFPSensorEvent event);
}
