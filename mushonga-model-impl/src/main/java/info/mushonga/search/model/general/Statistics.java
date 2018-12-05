package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.IStatistics;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author percym
 */
@MappedSuperclass
@Audited
public class Statistics extends ProductDescription implements IStatistics {

    private static final long serialVersionUID = 6987128322042242128L;


    @Column(name = "zz_search_date")
    private LocalDateTime lastSearchedDate;

    @DecimalMin("0.00")
    @Column(name = "zz_total_searched_times", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal totalSearchedTimes;


    @Override
    public LocalDateTime getLastSearchedDate() {
        return lastSearchedDate;
    }

    @Override
    public void setLastSearchedDate(LocalDateTime lastSearchedDate) {
        this.lastSearchedDate = lastSearchedDate;

    }

    @Override
    public BigDecimal getTotalSearchedTimes() {
        return totalSearchedTimes;
    }

    @Override
    public void setTotalSearchedTimes(BigDecimal totalSearchedTimes) {
        this.totalSearchedTimes = totalSearchedTimes;
    }
}
