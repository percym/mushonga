package systems.health263.dashboard.imodel.clinical;

import java.math.BigDecimal;

/**
 * An interface for the uptake of medicine
 *
 * @author Percy mugadza
 */
public interface ITariffCodes {

    /**
     * Returns the cashPrice for a particular service rendered
     *
     * @return the cashPrice for this item.
     * */
        BigDecimal getCashPrice();

    /**
     * Sets the cashPrice for a particular service to be administered
     *
     * @param   cashPrice for this item.
     * */
        void setCashPrice(BigDecimal cashPrice);


    /**
     * Returns the medicalAidPrice for a particular service to be administered
     *
     * @return the medicalAidPrice for this item.
     * */
        BigDecimal getMedicalAidPrice();

    /**
     * Sets the medicalAidPrice for a particular service rendered
     *
     * @param   medicalAidPrice for this item.
     * */
    void setMedicalAidPrice(BigDecimal medicalAidPrice);

    /**
     * Returns the unit for a particular drug to be administered
     *
     * @return the unit for this item.
     * */

}
