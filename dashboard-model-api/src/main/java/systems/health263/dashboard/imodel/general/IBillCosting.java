package systems.health263.dashboard.imodel.general;

import java.math.BigDecimal;

/**
 * IBill interface for the costing of any chargeable item
 *
 * @author Munyaradzi Takayindisa
 */
public interface IBillCosting extends IAuditing, ITimeActiveRecord{

    /**
     * Returns the amount charged the insurance/medical aid.
     *
     * @return the amount charged the insurance/medical aid.
     */
    BigDecimal getInsuranceBillAmount();

    /**
     * Sets the amount charged the insurance/medical aid.
     *
     * @param insuranceBillAmount the amount charged the insurance/medical aid.
     */
    void setInsuranceBillAmount(BigDecimal insuranceBillAmount);

    /**
     * Returns the amount the patient should pay.
     *
     * @return the amount the patient should pay.
     */
    BigDecimal getPatientPayAmount();

    /**
     * Sets the amount the patient should pay.
     *
     * @param patientPayAmount the amount the patient should pay.
     */
    void setPatientPayAmount(BigDecimal patientPayAmount);

    /**
     * Returns the total amount for the bill i.e insuranceBillAmount + patientPayAmount.
     *
     * @return the total amount for the bill i.e insuranceBillAmount + patientPayAmount.
     */
    BigDecimal getTotalBillAmount();

    /**
     * Sets the total amount for the bill i.e insuranceBillAmount + patientPayAmount.
     *
     * @param totalBillAmount the total amount for the bill i.e insuranceBillAmount + patientPayAmount.
     */
    void setTotalBillAmount(BigDecimal totalBillAmount);

    /**
     * Returns the quantity for this dispensing item.
     *
     * @return the quantity for this dispensing item.
     */
    BigDecimal getQuantity();

    /**
     * Sets the quantity for this dispensing item.
     *
     * @param quantity the quantity for this dispensing item.
     */
    void setQuantity(BigDecimal quantity);

}
