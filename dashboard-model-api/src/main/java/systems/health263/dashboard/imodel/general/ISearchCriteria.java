package systems.health263.dashboard.imodel.general;

import systems.health263.dashboard.utility.enums.SearchOperation;

/**
 * Interface to specify the basic Search Criteria for an entity.
 *
 * @author Munyaradzi Takayindisa
 */
public interface ISearchCriteria {

    /**
     * Sets the key for this record.
     *
     * @param key the key for this record.
     */
    void setKey(String key);

    /**
     * Returns the key for this record.
     *
     * @return the key for this record.
     */
    String getKey();

    /**
     * Returns the {@link SearchOperation searchOperation} for criteria.
     *
     * @return the {@link SearchOperation searchOperation} for criteria.
     */
    SearchOperation getOperation();

    /**
     * Sets the {@link SearchOperation searchOperation} for criteria.
     *
     * @param searchOperation the {@link SearchOperation searchOperation} for criteria.
     */
    void setOperation(SearchOperation searchOperation);

    /**
     * Returns the value for this record.
     *
     * @return the value for this record.
     */
    Object getValue();

    /**
     * Sets the value for this record.
     *
     * @param value the value for this record.
     */
    void setValue(Object value);

}