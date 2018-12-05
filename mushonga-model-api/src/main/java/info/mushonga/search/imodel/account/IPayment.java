package info.mushonga.search.imodel.account;



import info.mushonga.search.imodel.general.ICosting;
import info.mushonga.search.utility.enums.PaymentMethod;

import java.math.BigDecimal;

/**
 * @author percym
 */
public interface IPayment extends ICosting {
    /**
     * Returns the amountPaid for this payment.
     *
     * @return the amountPaid  for this payment.
     */
    BigDecimal getAmountPaid();

    /**
     * Returns the amountPaid for this payment.
     *
     * @param amountPaid for this payment.
     */
    void setAmountPaid(BigDecimal amountPaid);

    /**
     * Returns the {@link PaymentMethod paymentMethod} for this payment.
     *
     * @return the {@link PaymentMethod paymentMethod}  for this payment.
     */
    PaymentMethod getPaymentMethod();

    /**
     * Sets the  {@link PaymentMethod paymentMethod} for this payment.
     *
     * @param paymentMethod the  {@link PaymentMethod paymentMethod} for this payment.
     */

    void setPaymentMethod(PaymentMethod paymentMethod);

    /**
     * Returns the balanceBought for this account.
     *
     * @return the balanceBought  for this account.
     */
    BigDecimal getBalanceBought();

    /**
     * Returns the balanceBought for this account.
     *
     * @param balanceBought for this account.
     */
    void setBalanceBought(BigDecimal balanceBought);

}
