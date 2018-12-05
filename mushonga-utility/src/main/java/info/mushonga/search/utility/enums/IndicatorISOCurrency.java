package info.mushonga.search.utility.enums;

import java.util.HashMap;
import java.util.Map;

public enum IndicatorISOCurrency {

    /**
     * Angolan kwanza - Kz
     */
    AOA("AOA", "Kz", "Angolan kwanza"), // Kz After

    /**
     * Burundian franc - FBu
     */
    BIF("BIF", "FBu", "Burundian franc"), // FBu After

    /**
     * Botswana pula - P
     */
    BWP("BWP", "P", "Botswana pula"), // P Front

    /**
     * Congolese franc - FC
     */
    CDF("CDF", "FC", "Congolese franc"), // FC After

    /**
     * Cape Verde escudo - $
     */
    CVE("CVE", "$", "Cape Verde escudo"), // Esc After

    /**
     * Djiboutian franc - Fdj
     */
    DJF("DJF", "Fdj", "Djiboutian franc"), // Fdj After

    /**
     * Algerian dinar - DA
     */
    DZD("DZD", "DA", "Algerian dinar"), // DA After

    /**
     * Egyptian pound - E&#163;
     */
    EGP("EGP", "E&#163;", "Egyptian pound"), // LE Front

    /**
     * Eritrean nakfa - Nfk
     */
    ERN("ERN", "Nfk", "Eritrean nakfa"), // Nfk After

    /**
     * Ethiopian birr - Br
     */
    ETB("ETB", "Br", "Ethiopian birr"), // Br After

    /**
     * Euro - &#8364;
     */
    EUR("EUR", "&#8364;", "Euro"), // â‚¬ Front

    /**
     * Pound sterling - &#163;
     */
    GBP("GBP", "&#163;", "Pound sterling"), // Â£ Front

    /**
     * Ghanaian cedi - GH&#8373;
     */
    GHS("GHS", "GH&#8373;", "Ghanaian cedi"), // GHÂ¢ Front

    /**
     * Gambian dalasi - D
     */
    GMD("GMD", "D", "Gambian dalasi"), // D After

    /**
     * Guinean franc - FG
     */
    GNF("GNF", "FG", "Guinean franc"), // FG After

    /**
     * Kenyan shilling -
     */
    KES("KES", "KSh", "Kenyan shilling"), // KSh Front

    /**
     * Lesotho loti - M
     */
    LSL("LSL", "M", "Lesotho loti"), // M Front

    /**
     * Libyan dinar - LD
     */
    LYD("LYD", "LD", "Libyan dinar"), // LD After

    /**
     * Moroccan dirham - MAD
     */
    MAD("MAD", "MAD", "Moroccan dirham"), // DH After

    /**
     * Mauritanian ouguiya - UM
     */
    MRO("MRO", "UM", "Mauritanian ouguiya"), // UM After

    /**
     * Mauritian rupee - &#8360;
     */
    MUR("MUR", "&#8360;", "Mauritian rupee"), // â‚¨ Front

    /**
     * Malawian kwacha - MK
     */
    MWK("MWK", "MK", "Malawian kwacha"), // MK Front

    /**
     * Mozambican metical - MT
     */
    MZN("MZN", "MT", "Mozambican metical"), // MT After

    /**
     * Namibian dollar - N$
     */
    NAD("NAD", "N$", "Namibian dollar"), // N$ Front

    /**
     * Nigerian naira - &#8358;
     */
    NGN("NGN", "&#8358;", "Nigerian naira"), // â‚¦ Front

    /**
     * Rwandan franc - FRw
     */
    RWF("RWF", "FRw", "Rwandan franc"), // FRw After

    /**
     * Seychelles rupee - SRe
     */
    SCR("SCR", "SRe", "Seychelles rupee"), // SRe After

    /**
     * Sudanese pound - &#163;
     */
    SDG("SDG", "&#163;", "Sudanese pound"), // Â£ Front

    /**
     * Sierra Leonean leone - Le
     */
    SLL("SLL", "Le", "Sierra Leonean leone"), // Le Front

    /**
     * Somali shilling - Sh
     */
    SOS("SOS", "Sh", "Somali shilling"), // Sh After

    /**
     * South Sudanese pound - &#163;
     */
    SSP("SSP", "&#163;", "South Sudanese pound"), // Â£ Front

    /**
     * SÃ£o TomÃ© and PrÃ­ncipe dobra - Db
     */
    STD("STD", "Db", "SÃ£o TomÃ© and PrÃ­ncipe dobra"), // Db After

    /**
     * Swazi lilangeni - E
     */
    SZL("SZL", "E", "Swazi lilangeni"), // E Front

    /**
     * Tunisian dinar - DT
     */
    TND("TND", "DT", "Tunisian dinar"), // DT Front

    /**
     * Tanzanian shilling - TSh
     */
    TZS("TZS", "TSh", "Tanzanian shilling"), // TSh After

    /**
     * Ugandan shilling - USh
     */
    UGX("UGX", "USh", "Ugandan shilling"), // Ush Front

    /**
     * United States dollar - $
     */
    USD("USD", "$", "United States dollar"), // $ Front

    /**
     * South African rand - R
     */
    ZAR("ZAR", "R", "South African rand"), // R Front

    /**
     * Zambian kwacha - K
     */
    ZMW("ZMW", "K", "Zambian kwacha"), // K Front

    /**
     * Zimbabwe dollar - Z$
     */
    ZWD("ZWD", "Z$", "Zimbabwe dollar"), // Z$ Front

    /**
     * No currency
     */
    XXX("XXX", "X", "No currency");

    private static final String ERROR_MSG = "Unknown Currency Code abbreviation for conversion: ";

    private static final Map<String, IndicatorISOCurrency> CODE_LOOKUP = new HashMap<>();

    static {
        for (final IndicatorISOCurrency values : IndicatorISOCurrency.values()) {
            CODE_LOOKUP.put(values.isoCode, values);
        }
    }

    private String isoCode;
    private String symbol;
    private String description;

    /**
     * Creates a new instance of {@code IndicatorISOCurrency}.
     *
     * @param isoCode     The ISO 4217 code
     * @param symbol      The currency symbol
     * @param description The human-readable description
     */
    private IndicatorISOCurrency(final String isoCode, final String symbol, final String description) {

        this.isoCode = isoCode;
        this.symbol = symbol;
        this.description = description;
    }

    /**
     * Create a new instance by matching the supplied ISO 4217 code to the known values. A new instance will be
     * returned if a match is found.
     *
     * @param isoCode The abbreviation to match against.
     * @return A new instance
     */
    public static IndicatorISOCurrency fromISOCode(final String isoCode) {

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
     * Returns the ISO 4217 code
     *
     * @return the ISO 4217 code
     */
    public String getISOCode() {

        return isoCode;
    }

    /**
     * Returns the currency symbol
     *
     * @return the currency symbol
     */
    public String getSymbol() {

        return symbol;
    }

    /**
     * Returns the currency description
     *
     * @return the currency description
     */
    public String getDescription() {

        return description;
    }
}