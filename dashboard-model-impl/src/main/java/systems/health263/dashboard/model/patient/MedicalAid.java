package systems.health263.dashboard.model.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import systems.health263.dashboard.imodel.address.IAddress;
import systems.health263.dashboard.imodel.address.IContactDetails;
import systems.health263.dashboard.imodel.patient.IMedicalAid;
import systems.health263.dashboard.model.address.Address;
import systems.health263.dashboard.model.address.ContactDetails;
import systems.health263.dashboard.model.general.Active;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Medical Aid entity class
 *
 * @author Munyaradzi Takayindisa
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@ToString(callSuper = true)
@Table(schema = "static", name = "medical_aid")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "med_aid_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "med_aid_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "med_aid_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "med_aid_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "med_aid_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "med_aid_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "med_aid_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "med_aid_is_active"))
})
@SequenceGenerator(name = "default_seq", schema = "static", sequenceName = "med_aid_serial_seq", allocationSize = 1)
public class MedicalAid extends Active implements IMedicalAid {

    private static final long serialVersionUID = 3805321415775981254L;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "med_aid_name", length = 20, nullable = false)
    private String name;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "med_aid_bio_code", length = 10, nullable = false)
    private String bioCode;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "med_aid_claim_code", length = 10, nullable = false)
    private String claimCode;

    @Valid
    @JsonDeserialize(as = Address.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "med_aid_add_serial", referencedColumnName = "add_serial",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_static_med_aid_add"))
    private IAddress address;

    @Valid
    @JsonDeserialize(as = ContactDetails.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ContactDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "med_aid_contact_serial", referencedColumnName = "contact_serial",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_static_med_aid_contact"))
    private IContactDetails contactDetails;

}