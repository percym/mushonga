package info.mushonga.search.imodel.general;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author percym
 */
public interface ICosting  extends IAuditingEntity, ITimeActiveRecordEntity{
    /**
     * Returns the amountBalance for this account.
     *
     * @return the amountBalance  for this account.
     */
    BigDecimal getAmountBalance();

    /**
     * Returns the amountBalance for this account.
     *
     * @param amountBalance for this account.
     */
    void setAmountBalance(BigDecimal amountBalance);

    /**
     * Returns the searchBalance for this account.
     *
     * @return the searchBalance  for this account.
     */
    BigDecimal getSearchBalance();

    /**
     * Returns the searchBalance for this account.
     *
     * @param searchBalance for this account.
     */
    void setSearchBalance(BigDecimal searchBalance);

    /**
     * Returns the date the amount in the account expires.
     *
     * @return the date the amount in the account expires.
     */
    LocalDate getExpiryDate();

    /**
     * Sets the date the amount in the account expires.
     *
     * @param expiryDate The user that created this record.
     */
    void setExpiryDate(LocalDate expiryDate);
}
