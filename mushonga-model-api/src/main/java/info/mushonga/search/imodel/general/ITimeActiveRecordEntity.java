package info.mushonga.search.imodel.general;

import java.time.LocalDateTime;

/**
 * Interface that add time functionality to the basic {@link IPrimaryKey} interface to create entities
 * that is valid in the given start-end dates.
 *
 * @author Munyaradzi Takayindisa
 */
public interface ITimeActiveRecordEntity extends IPrimaryKey {

    /**
     * Returns the date from which this record is valid.
     *
     * @return The date from which this record is valid.
     */
    LocalDateTime getStartDate();

    /**
     * Sets the date from which this record is valid.
     *
     * @param startDate The date from which this record is valid.
     */
    void setStartDate(LocalDateTime startDate);

    /**
     * Returns the date until which this record is valid.
     *
     * @return The date until which this record is valid.
     */
    LocalDateTime getEndDate();

    /**
     * Sets the date until which this record is valid.
     *
     * @param endDate The date until which this record is valid.
     */
    void setEndDate(LocalDateTime endDate);

}
