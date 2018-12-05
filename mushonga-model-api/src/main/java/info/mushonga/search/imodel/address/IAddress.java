package info.mushonga.search.imodel.address;


import info.mushonga.search.imodel.general.IAuditingEntity;
import info.mushonga.search.imodel.general.ITimeActiveRecordEntity;
import info.mushonga.search.utility.enums.AddressType;
import info.mushonga.search.utility.enums.IndicatorISOCountryCode;

/**
 * The definition of an address in Africa.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IAddress extends IAuditingEntity, ITimeActiveRecordEntity {

    /**
     * Returns the {@link AddressType type} of address this is.
     *
     * @return the {@link AddressType type} of address.
     */
    AddressType getType();

    /**
     * Sets the {@link AddressType type} of address this is.
     *
     * @param type the {@link AddressType type} of address.
     */
    void setType(AddressType type);

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
     * Returns line 2 of this address.
     *
     * @return line 2 of this address.
     */
    String getLine2();

    /**
     * Sets line 2 of this address.
     *
     * @param line2 line 2 of this address.
     */
    void setLine2(String line2);

    /**
     * Returns line 3 of this address.
     *
     * @return line 3 of this address.
     */
    String getLine3();

    /**
     * Sets line 3 of this address.
     *
     * @param line3 line 3 of this address.
     */
    void setLine3(String line3);

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
    IndicatorISOCountryCode getCountryCode();

    /**
     * Sets the {@link IndicatorISOCountryCode country code} for this address.
     *
     * @param countryCode the {@link IndicatorISOCountryCode country code} for this address.
     */
    void setCountryCode(IndicatorISOCountryCode countryCode);

}
