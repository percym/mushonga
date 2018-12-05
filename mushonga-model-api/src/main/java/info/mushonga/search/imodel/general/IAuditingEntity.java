package info.mushonga.search.imodel.general;

import java.time.LocalDateTime;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IAuditingEntity extends IPrimaryKey {

    /**
     * Returns the user who created this record.
     *
     * @return the user that created this record.
     */
    String getCreatedBy();

    /**
     * Sets the user that created this record.
     *
     * @param createdBy The user that created this record.
     */
    void setCreatedBy(String createdBy);

    /**
     * Returns the timestamp when this record was created.
     *
     * @return the timestamp when this record was created.
     */
    LocalDateTime getCreatedOn();

    /**
     * Sets the timestamp for this record was created.
     *
     * @param createdOn The timestamp when this record was created.
     */
    void setCreatedOn(LocalDateTime createdOn);

    /**
     * Returns the user that last changed this record.
     *
     * @return the user that last changed this record.
     */
    String getUpdatedBy();

    /**
     * Sets the user that last changed this record.
     *
     * @param updatedBy The user that last changed this record.
     */
    void setUpdatedBy(String updatedBy);

    /**
     * Returns the timestamp when last this record changed.
     *
     * @return the timestamp when last this record changed.
     */
    LocalDateTime getUpdatedOn();

    /**
     * Sets the timestamp when last this record changed.
     *
     * @param updatedOn the timestamp when last this record changed.
     */
    void setUpdatedOn(LocalDateTime updatedOn);

}
