package systems.health263.dashboard.model.client;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.address.IAddress;
import systems.health263.dashboard.imodel.address.IContactDetails;
import systems.health263.dashboard.imodel.client.IClient;
import systems.health263.dashboard.imodel.client.IFacility;
import systems.health263.dashboard.model.address.Address;
import systems.health263.dashboard.model.address.ContactDetails;
import systems.health263.dashboard.model.general.Active;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IBill class
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"client"})
@ToString(callSuper = true, exclude = {"client"})
@Table(schema = "data", name = "facility")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "facility_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "facility_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "facility_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "facility_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "facility_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "facility_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "facility_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "facility_is_active"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "facility_serial_seq", allocationSize = 1)
public class Facility extends Active implements IFacility {

    private static final long serialVersionUID = -7896223980396662140L;

    @NotNull
    @Size(max = 20)
    @Column(name = "facility_name", length = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "facility_ahfoz_number", length = 20)
    private String AHFoZNumber;

    @Valid
    @JsonDeserialize(as = Address.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_add_serial", referencedColumnName = "add_serial",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_data_facility_add"))
    private IAddress address;

    @Valid
    @JsonDeserialize(as = ContactDetails.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ContactDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_contact_serial", referencedColumnName = "contact_serial",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_data_facility_contact"))
    private IContactDetails contactDetails;

    @Valid
    @JsonDeserialize(as = Client.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Client.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_client_serial", referencedColumnName = "client_serial",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_data_facility_client"))
    private IClient client;


}
