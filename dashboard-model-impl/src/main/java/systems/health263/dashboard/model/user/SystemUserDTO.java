package com.health263.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.health263.imodel.client.IFacility;
import com.health263.imodel.group.IGroup;
import com.health263.imodel.location.ILocation;
import com.health263.imodel.user.ISystemUser;
import com.health263.model.client.Facility;
import com.health263.model.general.Active;
import com.health263.model.group.Group;
import com.health263.model.location.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IUser class
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "system_user_enc")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "system_user_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "system_user_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "system_user_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "system_user_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "system_user_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "system_user_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "system_user_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "system_user_is_active"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "system_user_enc_serial_seq", allocationSize = 1)
public class SystemUserDTO extends Active implements ISystemUser {

    private static final long serialVersionUID = -5803233040844849239L;

    @NotNull
    @Size(max = 80)
    @Column(name = "user_username", length = 50,  nullable = false)
    private String userName;

    @JsonIgnore
    @Size(max = 1)
    @Column(name = "user_password", length = 1)
    private String password;

    @Email
    @Column(name = "user_email")
    private String email;

    @Size(max = 20)
    @Column(name = "user_phone", length = 20)
    private String phone;

    @Valid
    @JsonDeserialize(as = Location.class)
    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true, targetEntity = Location.class)
    private ILocation<?> location;

    @Valid
    @JsonDeserialize(as = Group.class)
    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true, targetEntity = Group.class)
    private IGroup<?> group;

    @Valid
    @JsonDeserialize(as= Facility.class)
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Facility.class, cascade = CascadeType.ALL)
    private IFacility facility;

}
