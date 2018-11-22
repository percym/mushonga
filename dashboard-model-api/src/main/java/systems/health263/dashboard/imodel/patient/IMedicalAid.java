package systems.health263.dashboard.imodel.patient;


import systems.health263.dashboard.imodel.address.IAddress;
import systems.health263.dashboard.imodel.address.IContactDetails;
import systems.health263.dashboard.imodel.general.IActive;

/**
 * IMedicalAid interface for defining medical aids within the system.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IMedicalAid extends IActive {

    /**
     * Returns the name for this medical aid.
     *
     * @return the name for this medical aid.
     */
    String getName();

    /**
     * Sets the name for this medical aid.
     *
     * @param name the name for this medical aid.
     */
    void setName(String name);

    /**
     * Returns the bioCode for this medical aid.
     *
     * @return the bioCode for this medical aid.
     */
    String getBioCode();

    /**
     * Sets the bioCode for this medical aid.
     *
     * @param bioCode the bioCode for this medical aid.
     */
    void setBioCode(String bioCode);

    /**
     * Returns the claimCode for this medical aid.
     *
     * @return the claimCode for this medical aid.
     */
    String getClaimCode();

    /**
     * Sets the claimCode for this medical aid.
     *
     * @param claimCode the claimCode for this medical aid.
     */
    void setClaimCode(String claimCode);

    /**
     * Returns the {@link IAddress address} for this patient.
     *
     * @return the {@link IAddress address} for this patient.
     */
    IAddress getAddress();

    /**
     * Sets the {@link IAddress address} for this patient.
     *
     * @param address the {@link IAddress address} for this patient.
     */
    void setAddress(IAddress address);

    /**
     * Returns the {@link IContactDetails contactDetails} for this patient.
     *
     * @return the {@link IContactDetails contactDetails} for this patient.
     */
    IContactDetails getContactDetails();

    /**
     * Sets the {@link IContactDetails contactDetails} for this patient.
     *
     * @param contactDetails the {@link IContactDetails contactDetails} for this patient.
     */
    void setContactDetails(IContactDetails contactDetails);

}
