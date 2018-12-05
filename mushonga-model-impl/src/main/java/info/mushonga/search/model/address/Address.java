package info.mushonga.search.model.address;

import info.mushonga.search.imodel.address.IAddress;
import info.mushonga.search.model.general.TimeActiveRecord;
import info.mushonga.search.utility.enums.AddressType;
import info.mushonga.search.utility.enums.IndicatorISOCountryCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IAddress class
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "static", name = "address")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "add_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "add_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "add_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "add_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "add_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "add_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "add_updated_on"))
})
@SequenceGenerator(name = "default_seq", schema = "static", sequenceName = "add_serial_seq", allocationSize = 1)
public class Address extends TimeActiveRecord implements IAddress {

    private static final long serialVersionUID = 8458014434419658533L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "add_type", length = 10, updatable = false, nullable = false)
    private AddressType type;

    @Size(max = 50)
    @Column(name = "add_line1", length = 50)
    private String line1;

    @Size(max = 50)
    @Column(name = "add_line2", length = 50)
    private String line2;

    @Size(max = 50)
    @Column(name = "add_line3", length = 50)
    private String line3;

    @Size(max = 50)
    @Column(name = "add_suburb", length = 50)
    private String suburb;

    @Size(max = 50)
    @Column(name = "add_city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "add_postal_code", length = 50)
    private String postalCode;

    @NotNull
    @Column(name = "add_country_code", length = 2, nullable = false)
    private IndicatorISOCountryCode countryCode;
}
