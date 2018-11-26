package systems.health263.dashboard.model.general;

import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.clinical.ITariffCodes;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Active entity bean for getting active records.
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class
TariffRateCode extends GeneralNaming implements ITariffCodes {

    private static final long serialVersionUID = -7331270591406353590L;

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_cash_price", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal cashPrice;


    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_medical_aid_price", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal medicalAidPrice;


    public BigDecimal getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(BigDecimal cashPrice) {
        this.cashPrice = cashPrice;
    }

    public BigDecimal getMedicalAidPrice() {
        return medicalAidPrice;
    }

    public void setMedicalAidPrice(BigDecimal medicalAidPrice) {
        this.medicalAidPrice = medicalAidPrice;
    }
}
