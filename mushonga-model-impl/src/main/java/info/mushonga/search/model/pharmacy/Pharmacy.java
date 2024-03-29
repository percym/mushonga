package info.mushonga.search.model.pharmacy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import info.mushonga.search.imodel.address.IAddress;
import info.mushonga.search.imodel.address.IContactDetails;
import info.mushonga.search.imodel.logo.ILogo;
import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.imodel.user.IPharmacySystemUser;
import info.mushonga.search.imodel.user.ISystemUser;
import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.address.ContactDetails;
import info.mushonga.search.model.general.Active;
import info.mushonga.search.model.logo.Logo;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.user.PharmacySystemUser;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.model.user.SystemUserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author percym
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "pharmacy"    , uniqueConstraints = {@UniqueConstraint(columnNames = {"pharmacy_registered_name"}, name = "un_data_pharmacy")})
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "pharmacy_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "pharmacy_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "pharmacy_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "pharmacy_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "pharmacy_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "pharmacy_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "pharmacy_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "pharmacy_is_active"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "pharmacy_serial_seq", allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pharmacy extends Active implements IPharmacy<Product,SystemUser> {

    @NotNull
    @Size(max = 100)
    @Column(name = "pharmacy_trading_name", length = 100)
    private String tradingName;

    @NotNull
    @Size(max = 100)
    @Column(name = "pharmacy_registered_name", length = 100)
    private String registeredName;

    @NotNull
    @Size(max = 100)
    @Column(name = "pharmacy_registered_number", length = 100)
    private String regNumber;


    @Valid
    @JsonDeserialize(as = ContactDetails.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ContactDetails.class, cascade = CascadeType.ALL)
    private IContactDetails contactDetails;

    @Valid
    @NotNull
    @JsonDeserialize(as = Address.class)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = {CascadeType.ALL})
    private IAddress address;

    @Valid
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Collection<Product> products = new ArrayList<>();

    @Valid
    @JsonDeserialize(as = Logo.class)
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL, targetEntity = Logo.class)
    ILogo logo;


    @Valid
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL})
    private Collection<SystemUser> systemUsers = new ArrayList<>();


}
