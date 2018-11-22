package systems.health263.dashboard.imodel.general;

/**
 * IGenericNaming interface for naming the code and description of entities.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IGenericNaming extends IActive {

    /**
     * Returns the genericCode for this generic item.
     *
     * @return the genericCode for this generic item.
     */
    String getGenericCode();

    /**
     * Sets the genericCode for this generic item.
     *
     * @param genericCode the genericCode for this generic item.
     */
    void setGenericCode(String genericCode);

    /**
     * Returns the genericName for this item.
     *
     * @return the genericName for this item.
     */
    String getGenericName();

    /**
     * Sets the genericName for this item.
     *
     * @param genericName the genericName for this item.
     */
    void setGenericName(String genericName);
}
