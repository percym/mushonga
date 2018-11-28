package systems.health263.dashboard.model.drugnsundry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.drugnsundry.IDrugNSundry;
import systems.health263.dashboard.model.general.GeneralNaming;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author percym
 */

@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "drug_n_sundry")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "drug_n_sundry_serial")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "drug_n_sundry_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "drug_n_sundry_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "drug_n_sundry_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "drug_n_sundry_updated_on")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "drug_n_sundry_updated_on")),
        @AttributeOverride(name = "startDate", column = @Column(name = "drug_n_sundry_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "drug_n_sundry_end_date")),
        @AttributeOverride(name = "active", column = @Column(name = "drug_n_sundry_is_active")),
        @AttributeOverride(name = "genericCode", column = @Column(name = "drug_n_sundry_generic_code")),
        @AttributeOverride(name = "genericName", column = @Column(name = "drug_n_sundry_generic_name"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "drug_n_sundry_seq", allocationSize = 1)
public class DrugNSundry extends GeneralNaming implements IDrugNSundry {

    @NotNull
    @Column(name = "system_id" , nullable = false)
    private String systemId;

    @NotNull
    @Column(name = "drug_or_sundry" , nullable = false)
    private String drugORSundry;

}
