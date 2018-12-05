package info.mushonga.search.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import info.mushonga.search.imodel.account.IAccount;
import info.mushonga.search.imodel.user.ISystemUser;
import info.mushonga.search.model.account.Account;
import info.mushonga.search.model.general.Active;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.UserType;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;

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
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SystemUserDTO extends Active implements ISystemUser<Pharmacy> {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Valid
    private IAccount<?,?> account;

    @Valid
    private Collection<Pharmacy> pharmacies;

}
