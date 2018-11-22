package systems.health263.dashboard.imodel.client;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import systems.health263.dashboard.imodel.address.IContactDetails;
import systems.health263.dashboard.imodel.general.IActive;
import systems.health263.dashboard.utility.enums.ClientDatabase;
import systems.health263.dashboard.utility.enums.IndicatorISOCountryCode;

/**
 * IClient interface for Client class
 *
 * @author Munyaradzi Takayindisa
 */
public interface IClient extends IActive {

    /**
     * Returns the name for this client.
     *
     * @return the name for this client.
     */
    String getName();

    /**
     * Sets the name for this client.
     *
     * @param name the name for this client.
     */
    void setName(String name);

    /**
     * Returns the {@link IContactDetails contactDetails} for this client.
     *
     * @return the {@link IContactDetails contactDetails} for this client.
     */
    IContactDetails getContactDetails();

    /**
     * Sets the {@link IContactDetails contactDetails} for this client.
     *
     * @param contactDetails the {@link IContactDetails contactDetails} for this client.
     */
    void setContactDetails(IContactDetails contactDetails);

    /**
     * Returns the registration number for this client.
     *
     * @return the the registration number for this client.
     */
    String getRegNumber();

    /**
     * Sets the registration number for this client.
     *
     * @param regNumber the registration number for this client.
     */
    void setRegNumber(String regNumber);

    /**
     * Returns the {@link ClientDatabase clientDatabase} for this client.
     *
     * @return the {@link ClientDatabase clientDatabase} for this client.
     */
    ClientDatabase getClientDatabase();

    /**
     * Sets the {@link ClientDatabase clientDatabase} for this client.
     *
     * @param clientDatabase the {@link ClientDatabase clientDatabase} for this client.
     */
    void setClientDatabase(ClientDatabase clientDatabase);

    /**
     * Returns the {@link IndicatorISOCountryCode IndicatorISOCountryCode} for this client.
     *
     * @return the {@link IndicatorISOCountryCode IndicatorISOCountryCode} for this client.
     */
    IndicatorISOCountryCode getCountryCode();

    /**
     * Sets the {@link IndicatorISOCountryCode IndicatorISOCountryCode} for this client.
     *
     * @param countryCode {@link IndicatorISOCountryCode IndicatorISOCountryCode} for this client.
     */
    void setCountryCode(IndicatorISOCountryCode countryCode);

    /**
     * Returns the time zone for this client.
     *
     * @return the time zone for this client.
     */
    String getTimeZone();

    /**
     * Sets the time zone for this client.
     *
     * @param timeZone the time zone for this client.
     */
    void setTimeZone(String timeZone);

    /**
     * Returns the logo for this client.
     *
     * @return the logo for this client.
     */
    ILogo getLogo();

    /**
     * Sets the logo for this client.
     *
     * @param logo  for this client.
     */
    void setLogo(ILogo logo);

}
