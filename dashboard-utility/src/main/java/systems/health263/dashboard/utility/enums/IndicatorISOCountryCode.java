package systems.health263.dashboard.utility.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum IndicatorISOCountryCode {

    /**
     * Algeria
     */
    DZ("DZ", "Algeria", "Africa/Algiers", IndicatorISOCurrency.DZD),

    /**
     * Angola
     */
    AO("AO", "Angola", "Africa/Luanda", IndicatorISOCurrency.AOA),

    /**
     * Benin
     */
    BJ("BJ", "Benin", "Africa/Porto-Novo", null),

    /**
     * Botswana
     */
    BW("BW", "Botswana", "Africa/Gaborone", IndicatorISOCurrency.BWP),

    /**
     * Burkina Faso
     */
    BF("BF", "Burkina Faso", "Africa/Gaborone", null),

    /**
     * Burundi
     */
    BI("BI", "Burundi", "Africa/Bujumbura", IndicatorISOCurrency.BIF),

    /**
     * Cameroon
     */
    CM("CM", "Cameroon", "Africa/Douala", null),

    /**
     * Cape Verde
     */
    CV("CV", "Cape Verde", "Atlantic/Cape_Verde", IndicatorISOCurrency.CVE),

    /**
     * Central African Republic
     */
    CF("CF", "Central African Republic", "Africa/Bangui", null),

    /**
     * Chad
     */
    TD("TD", "Chad", "Africa/Ndjamena", null),

    /**
     * Comoros
     */
    KM("KM", "Comoros", "Indian/Comoro", null),

    /**
     * Congo
     */
    CG("CG", "Congo", "Africa/Brazzaville", IndicatorISOCurrency.CDF),

    /**
     * Congo, the Democratic Republic of the
     */
    CD("CD", "Congo, the Democratic Republic of the", "Africa/Lubumbashi", null),

    /**
     * CÃ´te d'Ivoire
     */
    CI("CI", "CÃ´te d'Ivoire", "Africa/Abidjan", null),

    /**
     * Djibouti
     */
    DJ("DJ", "Djibouti", "Africa/Djibouti", IndicatorISOCurrency.DJF),

    /**
     * Egypt
     */
    EG("EG", "Egypt", "Africa/Cairo", IndicatorISOCurrency.EGP),

    /**
     * Equatorial Guinea
     */
    GQ("GQ", "Equatorial Guinea", "Africa/Malabo", null),

    /**
     * Eritrea
     */
    ER("ER", "Eritrea", "Africa/Asmara", IndicatorISOCurrency.ERN),

    /**
     * Ethiopia
     */
    ET("ET", "Ethiopia", "Africa/Addis_Ababa", IndicatorISOCurrency.ETB),

    /**
     * Gabon
     */
    GA("GA", "Gabon", "Africa/Libreville", null),

    /**
     * Gambia
     */
    GM("GM", "Gambia", "Africa/Banjul", IndicatorISOCurrency.GMD),

    /**
     * Ghana
     */
    GH("GH", "Ghana", "Africa/Accra", IndicatorISOCurrency.GHS),

    /**
     * Guinea
     */
    GN("GN", "Guinea", "Africa/Conakry", IndicatorISOCurrency.GNF),

    /**
     * Guinea-Bissau
     */
    GW("GW", "Guinea-Bissau", "Africa/Bissau", null),

    /**
     * Kenya
     */
    KE("KE", "Kenya", "Africa/Nairobi", IndicatorISOCurrency.KES),

    /**
     * Lesotho
     */
    LS("LS", "Lesotho", "Africa/Maseru", IndicatorISOCurrency.LSL),

    /**
     * Liberia
     */
    LR("LR", "Liberia", "Africa/Monrovia", null),

    /**
     * Libya
     */
    LY("LY", "Libya", "Africa/Tripoli", IndicatorISOCurrency.LYD),

    /**
     * Madagascar
     */
    MG("MG", "Madagascar", "  Indian/Antananarivo", null),

    /**
     * Malawi
     */
    MW("MW", "Malawi", "Africa/Blantyre", IndicatorISOCurrency.MWK),

    /**
     * Mali
     */
    ML("ML", "Mali", "Africa/Bamako", null),

    /**
     * Mauritania
     */
    MR("MR", "Mauritania", "Africa/Nouakchott", IndicatorISOCurrency.MRO),

    /**
     * Mauritius
     */
    MU("MU", "Mauritius", "Indian/Mauritius", IndicatorISOCurrency.MUR),

    /**
     * Morocco
     */
    MA("MA", "Morocco", "Africa/Casablanca", IndicatorISOCurrency.MAD),

    /**
     * Mozambique
     */
    MZ("MZ", "Mozambique", "Africa/Maputo", IndicatorISOCurrency.MZN),

    /**
     * Namibia
     */
    NA("NA", "Namibia", "Africa/Windhoek", IndicatorISOCurrency.NAD),

    /**
     * Niger
     */
    NE("NE", "Niger", "Africa/Niamey", null),

    /**
     * Nigeria
     */
    NG("NG", "Nigeria", "Africa/Lagos", IndicatorISOCurrency.NGN),

    /**
     * Rwanda
     */
    RW("RW", "Rwanda", "Africa/Kigali", IndicatorISOCurrency.RWF),

    /**
     * SÃ£o TomÃ© and PrÃ­ncipe
     */
    ST("ST", "SÃ£o TomÃ© and PrÃ­ncipe", "Africa/Sao_Tome", IndicatorISOCurrency.STD),

    /**
     * Senegal
     */
    SN("SN", "Senegal", "Africa/Dakar", null),

    /**
     * Seychelles
     */
    SC("SC", "Seychelles", "Indian/Mahe", IndicatorISOCurrency.SCR),

    /**
     * Sierra Leone
     */
    SL("SL", "Sierra Leone", "Africa/Freetown", IndicatorISOCurrency.SLL),

    /**
     * Somalia
     */
    SO("SO", "Somalia", "Africa/Mogadishu", IndicatorISOCurrency.SOS),

    /**
     * South Africa
     */
    ZA("ZA", "South Africa", "Africa/Johannesburg", IndicatorISOCurrency.ZAR),

    /**
     * South Sudan
     */
    SS("SS", "South Sudan", "Africa/Juba", IndicatorISOCurrency.SSP),

    /**
     * Sudan
     */
    SD("SD", "Sudan", "Africa/Khartoum", IndicatorISOCurrency.SDG),

    /**
     * Swaziland
     */
    SZ("SZ", "Swaziland", "Africa/Mbabane", IndicatorISOCurrency.SZL),

    /**
     * Tanzania
     */
    TZ("TZ", "Tanzania", "Africa/Dar_es_Salaam", IndicatorISOCurrency.TZS),

    /**
     * Tunisia
     */
    TN("TN", "Tunisia", "Africa/Tunis", IndicatorISOCurrency.TND),

    /**
     * Uganda
     */
    UG("UG", "Uganda", "Africa/Kampala", IndicatorISOCurrency.UGX),

    /**
     * Western Sahara
     */
    EH("EH", "Western Sahara", "Africa/El_Aaiun", IndicatorISOCurrency.MAD),

    /**
     * Zambia
     */
    ZM("ZM", "Zambia", "Africa/Lusaka", IndicatorISOCurrency.ZMW),

    /**
     * Zimbabwe
     */
    // no longer use ZIM $, only USD
    ZW("ZW", "Zimbabwe", "Africa/Harare", IndicatorISOCurrency.USD),

    /**
     * Unknown
     */
    UN("UN", "Unknown", "GMT", IndicatorISOCurrency.XXX),

    /**
     * Unknown
     */
    ZZ("ZZ", "Unknown", "GMT", IndicatorISOCurrency.XXX);

    private static final String ERROR_MSG = "Unknown Country Code abbreviation for conversion: ";

    private static final Map<String, IndicatorISOCountryCode> CODE_LOOKUP = new HashMap<>();

    static {
        for (final IndicatorISOCountryCode values : IndicatorISOCountryCode.values()) {
            CODE_LOOKUP.put(values.isoCode, values);
        }
    }

    private String isoCode;
    private String countryName;
    private String timezone;
    private IndicatorISOCurrency currency;

    /**
     * Creates a new instance of {@code IndicatorISOCountryCode}.
     *
     * @param isoCode     The ISO 3166-1 alpha-2 for this country
     * @param countryName The name of the country
     * @param timezone    The time zone for this country
     * @param currency    The currency for this country
     */
    IndicatorISOCountryCode(final String isoCode, final String countryName, final String timezone,
                            final IndicatorISOCurrency currency) {

        this.isoCode = isoCode;
        this.countryName = countryName;
        this.timezone = timezone;
        this.currency = currency;
    }

    /**
     * Create a new instance by matching the supplied the ISO 3166-1 alpha-2 to the known values. A new
     * instance will be returned if a match is found.
     *
     * @param isoCode The abbreviation to match against.
     * @return A new instance
     */
    public static IndicatorISOCountryCode fromISOCode(final String isoCode) {

        if (isoCode != null) {
            final String upperCaseAbbr = isoCode.toUpperCase();
            if (CODE_LOOKUP.containsKey(upperCaseAbbr)) {
                return CODE_LOOKUP.get(upperCaseAbbr);
            }
            throw new IllegalArgumentException(ERROR_MSG + isoCode);
        }
        return null;
    }

    /**
     * Returns the ISO 3166-1 alpha-2 for this country
     *
     * @return the ISO 3166-1 alpha-2 for this country
     */
    public String getISOCode() {

        return isoCode;
    }

    /**
     * Returns the name of the country
     *
     * @return the name of the country
     */
    public String getCountryName() {

        return countryName;
    }

    /**
     * Returns the default timezone for this country
     *
     * @return the timezone for this country
     */
    public String getTimezone() {

        return timezone;
    }

    /**
     * Returns the default currency for this country
     *
     * @return the currency for this country
     */
    public IndicatorISOCurrency getCurrency() {

        return currency;
    }

    /**
     * A JPA 2.1 type converter to convert the {@link IndicatorISOCountryCode} enumeration into the value
     * to be stored in the database and vice versa.
     *
     * @author Munyaradzi Takayindisa
     */
    @Converter(autoApply = true)
    public static class IndicatorISOCountryCodeConverter implements
            AttributeConverter<IndicatorISOCountryCode, String> {

        @Override
        public String convertToDatabaseColumn(final IndicatorISOCountryCode attribute) {

            return attribute != null ? attribute.getISOCode() : null;
        }

        @Override
        public IndicatorISOCountryCode convertToEntityAttribute(final String dbData) {

            return dbData != null ? IndicatorISOCountryCode.fromISOCode(dbData) : null;
        }

    }

}