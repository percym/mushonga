package info.mushonga.search.imodel.general;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Interface that add time functionality to the basic {@link IPrimaryKey} interface to create entities
 * that is valid in the given start-end dates.
 *
 * @author percym
 */
public interface ISearcheableRecordEntity extends ITimeActiveRecordEntity {

    /**
     * Returns the date from which this record is was last searched for.
     *
     * @return The date from which this record is was last searched for.
     */
    LocalDateTime getDateLastSearched();

    /**
     * Sets the date from which this record is was last searched for.
     *
     * @param dateLastSearched The date from which this record is was last searched for.
     */
    void setDateLastSearched(LocalDateTime dateLastSearched);

    /**
     * Returns the number of times a product has been searched.
     *
     * @return The number of times a product has been searched.
     */
    BigDecimal getTotalNumberOfTimesSearched();

    /**
     * Sets the number of times a product has been searched.
     *
     * @param totalNumberOfTimesSearched The number of times a product has been searched.
     */
    void setTotalNumberOfTimesSearched(LocalDateTime totalNumberOfTimesSearched);

}
