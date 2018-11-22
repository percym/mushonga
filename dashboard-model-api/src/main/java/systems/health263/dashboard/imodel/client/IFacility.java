package systems.health263.dashboard.imodel.client;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import systems.health263.dashboard.imodel.address.IAddress;
import systems.health263.dashboard.imodel.address.IContactDetails;
import systems.health263.dashboard.imodel.general.IActive;

/**
 * IFacility interface for Facility class
 *
 * @author Munyaradzi Takayindisa
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IFacility extends IActive {

    /**
     * Returns the name for this facility.
     *
     * @return the name for this facility.
     */
    String getName();

    /**
     * Sets the name for this facility.
     *
     * @param name the name for this facility.
     */
    void setName(String name);

    /**
     * Returns the AHFoZNumber for this facility.
     *
     * @return the AHFoZNumber for this facility.
     */
    String getAHFoZNumber();

    /**
     * Sets the AHFoZNumber for this facility.
     *
     * @param AHFoZNumber the AHFoZNumber for this facility.
     */
    void setAHFoZNumber(String AHFoZNumber);

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
     * Returns the {@link IContactDetails contactDetails} for this facility.
     *
     * @return the {@link IContactDetails contactDetails} for this facility.
     */
    IContactDetails getContactDetails();

    /**
     * Sets the {@link IContactDetails contactDetails} for this facility.
     *
     * @param contactDetails the {@link IContactDetails contactDetails} for this facility.
     */
    void setContactDetails(IContactDetails contactDetails);

    /**
     * Returns the {@link IClient client} for this facility.
     *
     * @return the {@link IClient client} for this facility.
     */
    IClient getClient();

    /**
     * Sets the {@link IClient client} for this facility.
     *
     * @param client the {@link IClient client} for this facility.
     */
    void setClient(IClient client);

}
