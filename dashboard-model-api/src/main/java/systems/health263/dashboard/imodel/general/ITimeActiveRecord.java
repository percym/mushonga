package systems.health263.dashboard.imodel.general;

import java.time.LocalDateTime;

/**
 *  defines methods for creation ie dates
 * @author percym
 */
public interface ITimeActiveRecord {
    /*
    * Returns the date which item is created
    *
    * @return time item created
    * */
    LocalDateTime getStartDate();

    /*
    * Sets the date which the item is created
    *
    * @param time item is created
    * */
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
