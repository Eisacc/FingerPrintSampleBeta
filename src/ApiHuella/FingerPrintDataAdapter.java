/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHuella;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrador
 */
public class FingerPrintDataAdapter {

    /**
     * lectorHuella
     */
    final private DPFPCapture lectorHuella = DPFPGlobal.getCaptureFactory().createCapture();
    /**
     * reclutador
     */
    final private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    /**
     * verficador
     */
    final private DPFPVerification verficador = DPFPGlobal.getVerificationFactory().createVerification();
    /**
     * template
     */
    private DPFPTemplate template;
    /**
     * TEMPLATE_PROPERTY
     */
    final private static String TEMPLATE_PROPERTY = "template";
    /**
     * featuresInscripcion
     */
    private DPFPFeatureSet featuresInscripcion;
    /**
     * featuresVerificacion
     */
    private DPFPFeatureSet featuresVerificacion;

    /**
     * dataAdapterInterface
     */
    private DataAdapterInterface dataAdapterInterface = null;
    /**
     * image
     */
    private Image imageHuella;

    private boolean isReadyFingerPrint = false;

    /**
     *
     * @param dataAdapterInterface
     */
    public FingerPrintDataAdapter(final DataAdapterInterface dataAdapterInterface) {
        this.dataAdapterInterface = dataAdapterInterface;
        if (this.dataAdapterInterface != null) {
            initConponentsFingerPrint();
            initLector();
        }
    }

    /**
     *
     */
    public void initLector() {
        if (!lectorHuella.isStarted()) {
            lectorHuella.startCapture();
            setTemplate(null);
            setIsReadyFingerPrint(false);
        }
    }

    /**
     *
     */
    public void stopLector() {
        if (lectorHuella.isStarted()) {
            lectorHuella.stopCapture();
        }
    }

    /**
     * initConponentsFingerPrint
     */
    public void initConponentsFingerPrint() {

        lectorHuella.addDataListener(new DPFPDataAdapter() {

            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                if (dataAdapterInterface != null) {
                    captureFingerPrint(e.getSample());
                    dataAdapterInterface.dataAcquiredFinger(e);
                }
            }
        });

        lectorHuella.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(DPFPReaderStatusEvent e) {
                if (dataAdapterInterface != null) {
                    dataAdapterInterface.readerConnectedDevice(e);
                }
            }

            @Override
            public void readerDisconnected(DPFPReaderStatusEvent e) {
                if (dataAdapterInterface != null) {
                    dataAdapterInterface.readerDisconnectDevice(e);
                }

            }

        });

        lectorHuella.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent event) {
                if (dataAdapterInterface != null) {
                    dataAdapterInterface.fingerTouchedDevice(event);
                }

            }

            @Override
            public void fingerGone(final DPFPSensorEvent event) {
                if (dataAdapterInterface != null) {
                    dataAdapterInterface.fingerGoneDevice(event);
                }

            }

        });

        lectorHuella.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent dPFPErrorEvent) {
                if (dataAdapterInterface != null) {
                    dataAdapterInterface.errorReaderFingerDevice(dPFPErrorEvent);
                }

            }
        });

    }

    /**
     *
     * @param sample
     * @return
     */
    private void captureFingerPrint(DPFPSample sample) {

        featuresInscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featuresVerificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        if (featuresInscripcion != null) {
            try {

                reclutador.addFeatures(featuresInscripcion);

                setImageHuella(crearImageHuella(sample));

            } catch (Exception e) {
                System.out.println("Error aca hue" + e.getMessage());
            }
            switch (reclutador.getTemplateStatus()) {
                case TEMPLATE_STATUS_READY:
                    stopLector();
                    setTemplate(reclutador.getTemplate());
                    setIsReadyFingerPrint(true);
                    reclutador.clear();
                    break;
                case TEMPLATE_STATUS_FAILED:
                    reclutador.clear();
                    stopLector();
                    setTemplate(null);
                    initLector();
                    break;

            }

        }

    }

    public void cleanTemplate() {
        reclutador.clear();
        stopLector();
        setTemplate(null);
        initLector();

    }

    private DPFPFeatureSet extraerCaracteristicas(final DPFPSample sample, final DPFPDataPurpose purpose) {
        DPFPFeatureSet dPFPFeatureSet = null;
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    private Image crearImageHuella(DPFPSample sample) {
        Image image = null;
        image = DPFPGlobal.getSampleConversionFactory().createImage(sample);
        return image;
    }

    /**
     * @return the template
     */
    public DPFPTemplate getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = template;
        this.template = template;

    }

    /**
     * @return the imageHuella
     */
    public Image getImageHuella() {
        return imageHuella;
    }

    /**
     * @param imageHuella the imageHuella to set
     */
    public void setImageHuella(Image imageHuella) {
        this.imageHuella = imageHuella;
    }

    /**
     * @return the isReadyFingerPrint
     */
    public boolean isIsReadyFingerPrint() {
        return isReadyFingerPrint;
    }

    /**
     * @param isReadyFingerPrint the isReadyFingerPrint to set
     */
    public void setIsReadyFingerPrint(boolean isReadyFingerPrint) {
        this.isReadyFingerPrint = isReadyFingerPrint;
    }

    public Boolean verificar() {

        DPFPVerificationResult DPFPVerificationResult = verficador.verify(featuresVerificacion, template);
        return DPFPVerificationResult.isVerified();
    }

}
