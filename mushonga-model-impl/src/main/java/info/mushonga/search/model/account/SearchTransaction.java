package info.mushonga.search.model.account;


import info.mushonga.search.imodel.account.ISearchTransaction;
import info.mushonga.search.model.general.Costing;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author percym
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "search_transaction")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "search_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "search_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "search_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "search_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "search_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "search_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "search_updated_on")),
        @AttributeOverride(name = "amountBalance", column = @Column(name = "search_amount_balance")),
        @AttributeOverride(name = "searchBalance", column = @Column(name = "search_search_balance")),
        @AttributeOverride(name = "expiryDate", column = @Column(name = "search_expiry_date"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "search_transaction_serial_seq", allocationSize = 1)
public class SearchTransaction extends Costing implements ISearchTransaction {

    @NotNull
    @Column(name = "search_transaction_term", nullable = false)
    private String searchTerm;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "transaction_total_query_results", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal totalQueryResults;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "transaction_cost", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal transactionCost;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "transaction_balance_after_search", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal balanceAfterSearch;

}
