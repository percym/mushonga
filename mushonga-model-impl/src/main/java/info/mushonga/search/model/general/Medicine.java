package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.IGenericNaming;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * Active entity bean for getting active records.
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class Medicine extends GeneralNaming implements IGenericNaming {

    private static final long serialVersionUID = -7331270591406353590L;

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_medicine_dosage", columnDefinition = "numeric(12,2) default '0.00'")
    private double dosage;


    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_medicine_days", columnDefinition = "numeric(12,2) default '0.00'")
    private double days;


    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_medicine_units", columnDefinition = "numeric(12,2) default '0.00'")
    private double units;

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }
}
