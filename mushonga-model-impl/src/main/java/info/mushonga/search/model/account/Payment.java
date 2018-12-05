package info.mushonga.search.model.account;


import info.mushonga.search.imodel.account.IPayment;
import info.mushonga.search.model.general.Costing;
import info.mushonga.search.utility.enums.PaymentMethod;
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
 * The Payment class for all payments for an individual account
 *
 * @author percym
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "payment")
//@AttributeOverrides({
//        @AttributeOverride(name = "id", column = @Column(name = "payment_serial")),
//        @AttributeOverride(name = "startDate", column = @Column(name = "payment_start_date")),
//        @AttributeOverride(name = "endDate", column = @Column(name = "payment_end_date")),
//        @AttributeOverride(name = "createdBy", column = @Column(name = "payment_created_by")),
//        @AttributeOverride(name = "createdOn", column = @Column(name = "payment_created_on")),
//        @AttributeOverride(name = "updatedBy", column = @Column(name = "payment_updated_by")),
//        @AttributeOverride(name = "updatedOn", column = @Column(name = "payment_updated_on")),
//        @AttributeOverride(name = "amountBalance", column = @Column(name = "payment_amount_balance")),
//        @AttributeOverride(name = "searchBalance", column = @Column(name = "payment_search_balance")),
//        @AttributeOverride(name = "expiryDate", column = @Column(name = "payment_expiry_date"))
//})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "payment_serial_seq", allocationSize = 1)
public class Payment extends Costing implements IPayment {


    private static final long serialVersionUID = -3057952289113615962L;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "payment_amount_paid", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal amountPaid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_payment_method", length = 15, nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "payment_balance_bought", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal balanceBought;



}
