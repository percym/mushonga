package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.ICosting;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *  Costing entity bean for getting costing  records.
 *
 * @author percym
 */
@MappedSuperclass
@Audited
public class Costing extends TimeActiveRecord implements ICosting {

    private static final long serialVersionUID = 6254169787797145038L;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_amount_balance", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal amountBalance;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "zz_search_balance", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal searchBalance;

    @NotNull
    @Column(name = "zz_expiry_date", updatable = false)
    private LocalDate expiryDate;

    @Override
    public int hashCode(){
        return Objects.hash(
                super.hashCode()
                ,getAmountBalance()
                ,getSearchBalance(),
                getExpiryDate()
                );
    }

    @Override
   public boolean equals(final Object obj){
        if (this == obj){
            return true;
        }

        if (!super.equals(obj)){
            return false;
        }

        if(!(obj instanceof Costing)){
            return false;
        }
        final Costing other = (Costing) obj;
        return Objects.equals(getAmountBalance(),other.getAmountBalance())
                && Objects.equals(getExpiryDate(),other.getExpiryDate())
                && Objects.equals(getSearchBalance(), other.getSearchBalance());
   }


    @Override
    public BigDecimal getAmountBalance() {
        return amountBalance;
    }

    @Override
    public void setAmountBalance(BigDecimal amountBalance) {
        this.amountBalance = amountBalance;

    }

    @Override
    public BigDecimal getSearchBalance() {
        return searchBalance;
    }

    @Override
    public void setSearchBalance(BigDecimal searchBalance) {
        this.searchBalance = searchBalance;

    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;

    }
}
