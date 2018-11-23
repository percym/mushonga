package systems.health263.dashboard.model.clinical;

import com.health263.imodel.clinical.ITariffCodes;
import com.health263.model.general.TariffRateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the ITariff interface.
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "static", name = "tariff_code")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "tariff_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "tariff_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "tariff_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "tariff_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "tariff_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "tariff_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "tariff_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "tariff_is_active")),
        @AttributeOverride(name = "genericCode", column = @Column(name = "tariff_generic_code")),
        @AttributeOverride(name = "genericName", column = @Column(name = "tariff_generic_name")),
        @AttributeOverride(name = "cashPrice", column = @Column(name = "tariff_cash_price")),
        @AttributeOverride(name = "medicalAidPrice", column = @Column(name = "tariff_medical_aid_price"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "tariff__code_serial_seq", allocationSize = 1)
public class

TariffCode extends TariffRateCode implements ITariffCodes {
    private static final long serialVersionUID = 5133767837051230130L;



}