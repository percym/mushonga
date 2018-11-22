package systems.health263.dashboard.imodel.general;

/**
 * IActive interface for checking the record state.
 *
 * @Author Percy Mugadza
 */
public interface IApproved extends IActive {

    /**
     * Returns if this record is approved.
     *
     * @return if this record is approved.
     */
    Boolean getApprove();

    /**
     * Sets if this record is approved.
     *
     * @param approve if this record is approved.
     */
    void setApprove(Boolean approve);

}