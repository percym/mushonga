package info.mushonga.search.model.address;

import info.mushonga.search.imodel.address.IContactDetails;
import info.mushonga.search.model.general.TimeActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IContactDetails class
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "static", name = "contact_details")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "contact_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "contact_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "contact_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "contact_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "contact_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "contact_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "contact_updated_on"))
})
@SequenceGenerator(name = "default_seq", schema = "static", sequenceName = "contact_serial_seq", allocationSize = 1)
public class ContactDetails extends TimeActiveRecord implements IContactDetails {

    private static final long serialVersionUID = 2812013763800265875L;

    @Size(max = 20)
    @Column(name = "contact_work_phone", length = 20)
    private String workPhone;


    @Size(max = 20)
    @Column(name = "contact_home_phone", length = 20)
    private String homePhone;

    @NotNull
    @Size(max = 20)
    @Column(name = "contact_primary_mobile", length = 20)
    private String primaryMobile;


    @Size(max = 20)
    @Column(name = "contact_secondary_mobile", length = 20)
    private String secondaryMobile;

    @Size(max = 80)
    @Email
    @Column(name = "contact_email", length = 80)
    private String email;

}