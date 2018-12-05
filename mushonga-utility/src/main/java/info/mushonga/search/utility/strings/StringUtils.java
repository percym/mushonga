package info.mushonga.search.utility.strings;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    /**
     * Text representation of 'Unknown'
     */
    public static final String UNKNOWN = "Unknown";
    /**
     * Text representation of 'UNK'
     */
    public static final String UNK = "UNK";
    /**
     * Text representation of null
     */
    public static final String NULL = "Null";

    /**
     * Returns a string that is no longer that the specified {@code maxLength} or {@code null} if the input is
     * either {@code null} or an empty string.
     *
     * @param string    the string to constrict to the maximum length.
     * @param maxLength the maximum number of characters in the string.
     * @return the restricted string or {@code null}
     */
    public static String maxLength(final String string, final int maxLength) {

        String str = org.apache.commons.lang3.StringUtils.trimToNull(string);
        if (str != null && str.length() > maxLength) {
            str = org.apache.commons.lang3.StringUtils.substring(str, 0, maxLength);
        }
        return str;
    }
}