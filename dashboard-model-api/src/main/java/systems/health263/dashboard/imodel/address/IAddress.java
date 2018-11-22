package systems.health263.dashboard.imodel.address;


import systems.health263.dashboard.imodel.general.IAuditing;
import systems.health263.dashboard.imodel.general.ITimeActiveRecord;
import systems.health263.dashboard.utility.enums.IndicatorISOCountryCode;

/**
 * The definition of an address in Africa.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IAddress extends IAuditing, ITimeActiveRecord {

    /**
     * Returns line 1 of this address.
     *
     * @return line 1 of this address.
     */
    String getLine1();

    /**
     * Sets line 1 of this address.
     *
     * @param line1 line 1 of this address.
     */
    void setLine1(String line1);

    /**
     * Returns the suburb for this address.
     *
     * @return the suburb for this address.
     */
    String getSuburb();

    /**
     * Sets the suburb for this address.
     *
     * @param suburb the suburb for this address.
     */
    void setSuburb(String suburb);

    /**
     * Returns the city for this address.
     *
     * @return the city for this address.
     */
    String getCity();

    /**
     * Sets the city for this address.
     *
     * @param city the city for this address.
     */
    void setCity(String city);

    /**
     * Returns the postal code for this address.
     *
     * @return the postal code for this address.
     */
    String getPostalCode();

    /**
     * Sets the postal code for this address.
     *
     * @param postalCode the postal code for this address.
     */
    void setPostalCode(String postalCode);

    /**
     * Returns the {@link IndicatorISOCountryCode country code} for this address.
     *
     * @return the {@link IndicatorISOCountryCode country code} for this address.
     */
    default IndicatorISOCountryCode getCountryCode() {
        return null;
    }

    /**
     * Sets the {@link IndicatorISOCountryCode country code} for this address.
     *
     * @param countryCode the {@link IndicatorISOCountryCode country code} for this address.
     */
    void setCountryCode(IndicatorISOCountryCode countryCode);

}
