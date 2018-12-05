package info.mushonga.search.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import info.mushonga.search.imodel.account.IAccount;
import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.imodel.user.ISystemUser;
import info.mushonga.search.model.account.Account;
import info.mushonga.search.model.general.Active;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IUser class
 */
@Entity
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "system_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_email", "user_username"}, name = "un_data_system_user")})
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
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "system_user_serial_seq", allocationSize = 1)
public class SystemUser extends Active implements ISystemUser<Pharmacy>{

    private static final long serialVersionUID = -5803233040844849239L;

    @NotNull
    @Size(max = 80)
    @Column(name = "user_username", length = 50, unique = true, nullable = false, updatable = false)
    private String userName;


    @Size(max = 150)
    @Column(name = "user_password", length = 150)
    private String password;

    @Email
    @Column(name = "user_email", unique = true)
    private String email;

    @Size(max = 20)
    @Column(name = "user_phone", length = 20)
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_account_type", length = 15, nullable = false)
   private AccountType accountType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_user_type", length = 15, nullable = false)
    private UserType userType;

    @Valid
    @JsonDeserialize(as= Account.class)
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Account.class, cascade = CascadeType.ALL)
    private IAccount<?,?> account;

    @Valid
    @ManyToMany(mappedBy = "systemUsers")
    private Collection<Pharmacy> pharmacies;

}
