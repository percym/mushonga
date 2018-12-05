package info.mushonga.search.imodel.general;

/**
 * IMedicine interface for medicinal properties description  strength  and unit of entities.
 *
 * @author percym
 */
public interface IMedicine extends IActive {

    /**
     * Returns the genericDescription for this generic item.
     *
     * @return the genericDescription for this generic item.
     */
    String getGenericDescription();

    /**
     * Sets the genericCode for this generic item.
     *
     * @param genericDescription the genericCode for this generic item.
     */
    void setGenericDescription(String genericDescription);

    /**
     * Returns the strength for this ite
     *
     * @return the strength for this item.
     */
    String getStrength();

    /**
     * Sets the strength for this item.
     *
     * @param strength the genericName for this item.
     */
    void setStrength(String strength);

    /**
     * Returns the strength for this ite
     *
     * @return the strength for this item.
     */
    String getUnitOfMeasure();

    /**
     * Sets the unitOfMeasure for this item.
     *
     * @param unitOfMeasure the unitOfMeasure for this item.
     */
    void setUnitOfMeasure(String unitOfMeasure);


}
