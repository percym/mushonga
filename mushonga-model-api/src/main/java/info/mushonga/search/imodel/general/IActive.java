package info.mushonga.search.imodel.general;

/**
 * IActive interface for checking the record state.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IActive extends IAuditingEntity, ITimeActiveRecordEntity {

    /**
     * Returns if this record is active.
     *
     * @return if this record is active.
     */
    Boolean getActive();

    /**
     * Sets if this record is active.
     *
     * @param active if this record is active.
     */
    void setActive(Boolean active);

}