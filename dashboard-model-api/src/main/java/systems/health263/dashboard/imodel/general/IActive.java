package systems.health263.dashboard.imodel.general;

/**
 * IActive interface for checking the record state.
 *
 * @Author Percy Mugadza
 */
public interface IActive extends IAuditing, ITimeActiveRecord {

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