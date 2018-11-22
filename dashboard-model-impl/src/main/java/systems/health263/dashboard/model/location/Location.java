package systems.health263.dashboard.model.location;
/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Percy Mugadza
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
import systems.health263.dashboard.imodel.location.ILocation;
import systems.health263.dashboard.model.address.Address;
import systems.health263.dashboard.model.address.ContactDetails;
import systems.health263.dashboard.model.client.Client;
import systems.health263.dashboard.model.client.Facility;
import systems.health263.dashboard.model.general.Active;
import systems.health263.dashboard.utility.enums.ClientType;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "location")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "location_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "location_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "location_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "location_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "location_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "location_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "location_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "location_is_active"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "location_serial_seq", allocationSize = 1)
public class Location extends Active implements ILocation <Facility>{

    private static final long serialVersionUID = -5749829234349753159L;

    @NotNull
    @Size(max = 50)
    @Column(name = "location_name", length = 50)
    private String name;

    @Valid
    @OneToMany(fetch = FetchType.LAZY,    cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Collection<Facility> facilities;

    @Valid
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type", length = 15)
    private ClientType locationType;

    @Valid
    @JsonDeserialize(as = Address.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private IAddress address;

    @Valid
    @JsonDeserialize(as = ContactDetails.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ContactDetails.class,  cascade = {CascadeType.ALL}, orphanRemoval = true)
    private IContactDetails contactDetails;

    @Valid
    @NotNull
    @JsonDeserialize(as = Client.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Client.class,  cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private IClient client;

}
