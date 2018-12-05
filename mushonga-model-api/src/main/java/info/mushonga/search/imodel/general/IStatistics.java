package info.mushonga.search.imodel.general;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * IStatistics interface for statistical data properties date last appeared in search number of times searched for.
 *
 * @author percym
 */
public interface IStatistics extends IActive {
    /**
     * Returns the date from which this record  last appeared in a search.
     *
     * @return The date from which this record  last appeared in a search.
     */
    LocalDateTime getLastSearchedDate();
    /**
     * Sets the date from which this record  last appeared in a search.
     *
     * @param lastSearchedDate date from which this record  last appeared in a search.
     */
    void setLastSearchedDate(LocalDateTime lastSearchedDate);

    /**
     * Returns the number of times this items has appeared in searches.
     *
     * @return the number of times this items has appeared in searches.
     */
    BigDecimal getTotalSearchedTimes();

    /**
     * Sets the number of times this items has appeared in searches.
     *
     * @param totalSearchedTimes  the number of times this items has appeared in searches.
     */
    void setTotalSearchedTimes(BigDecimal totalSearchedTimes);

}
