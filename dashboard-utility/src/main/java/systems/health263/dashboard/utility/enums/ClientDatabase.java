package systems.health263.dashboard.utility.enums;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * Enum defining the different clients that have been setup in the system.
 *
 * @author Munyaradzi Takayindisa
 */
public enum ClientDatabase {

    /**
     * Dova default
     */
    DOVA("dova", "dova"),

    /**
     * Gonda
     */
    GONDA("gonda", "gonda"),

    /**
     * Test
     */
    TEST("test", "test");

    private static final Map<String, ClientDatabase> CODE3_LOOKUP = new HashMap<>();

    static {
        for (final ClientDatabase values : ClientDatabase.values()) {
            CODE3_LOOKUP.put(values.iso3Code, values);
        }
    }

    private String iso3Code;
    private String iso1Code;
    private String displayName;

    /**
     * Creates a new instance of {@code ClientDatabase}.
     *
     * @param iso3Code    The ISO 639-3 code
     * @param displayName The language display name
     */
    ClientDatabase(final String iso3Code, final String displayName) {

        this.iso3Code = iso3Code;
        this.displayName = displayName;
    }

    /**
     * Create a new instance by matching the supplied ISO 639-3 code to the known values. A new instance will be
     * returned if a match is found.
     *
     * @param iso3Code The code to match against
     * @return A new instance
     */
    public static ClientDatabase fromISO3Code(final String iso3Code) {

            if (iso3Code != null) {
            final String lowerCaseCode = iso3Code.toLowerCase();
            if (CODE3_LOOKUP.containsKey(lowerCaseCode)) {
                return CODE3_LOOKUP.get(lowerCaseCode);
            }
            throw new IllegalArgumentException("Client with name " + iso3Code + " not yet configured");
        }
        return null;

    }

    /**
     * Returns the ISO 639-3 code
     *
     * @return the ISO 639-3 code
     */
    public String getISO3Code() {

        return iso3Code;
    }

    /**
     * Returns the ISO 639-1 code
     *
     * @return the ISO 639-1 code
     */
    public String getISO1Code() {

        return iso1Code;
    }

    /**
     * Returns the language display name
     *
     * @return the language display name
     */
    public String getDisplayName() {

        return displayName;
    }

    /**
     * A JPA 2.1 type converter to convert the {@link IndicatorISOLanguage} enumeration into the value to be
     * stored in the database and vice versa.
     *
     * @author Munyaradzi Takayindisa
     */
    @Converter(autoApply = true)
    public static class ClientDatabaseConverter
            implements AttributeConverter<ClientDatabase, String> {

        /**
         * {@inheritDoc}
         *
         * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(Object)
         */
        @Override
        public String convertToDatabaseColumn(final ClientDatabase attribute) {

            return attribute != null ? attribute.getISO3Code() : null;
        }

        /**
         * {@inheritDoc}
         *
         * @see javax.persistence.AttributeConverter#convertToEntityAttribute(Object)
         */
        @Override
        public ClientDatabase convertToEntityAttribute(final String dbData) {

            return dbData != null ? ClientDatabase.fromISO3Code(dbData) : null;
        }

    }
}

