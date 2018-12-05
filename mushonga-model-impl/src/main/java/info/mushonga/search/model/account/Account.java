package info.mushonga.search.model.account;


import info.mushonga.search.imodel.account.IAccount;
import info.mushonga.search.model.general.Costing;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The account class for tracking payments and usage
 *
 * @author percym
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "account"
        ,uniqueConstraints={
    @UniqueConstraint(columnNames = {"account_serial"} , name = "un_data_account_number")
}
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "account_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "account_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "account_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "account_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "account_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "account_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "account_updated_on")),
        @AttributeOverride(name = "amountBalance", column = @Column(name = "account_amount_balance")),
        @AttributeOverride(name = "searchBalance", column = @Column(name = "account_search_balance")),
        @AttributeOverride(name = "expiryDate", column = @Column(name = "account_expiry_date"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "account_serial_seq", allocationSize = 1)
public class Account extends Costing implements IAccount<Payment,SearchTransaction> {

    @NotNull
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Valid
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL}, orphanRemoval = true)
    Collection<Payment> payments = new ArrayList<>();

    @Valid
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL}, orphanRemoval = true)
    Collection<SearchTransaction> searchTransactions = new ArrayList<>();

}
