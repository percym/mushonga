package info.mushonga.search.imodel.pharmacy;


import info.mushonga.search.imodel.address.IAddress;
import info.mushonga.search.imodel.address.IContactDetails;
import info.mushonga.search.imodel.general.IActive;
import info.mushonga.search.imodel.logo.ILogo;
import info.mushonga.search.imodel.product.IProduct;
import info.mushonga.search.imodel.user.IPharmacySystemUser;
import info.mushonga.search.imodel.user.ISystemUser;

import java.util.Collection;

/**
 * IProduct interface for the product class and related entities
 *
 * @param <T> any class that extends IProduct
 * @param <T> any class that extends ISystemUser
 * @author percym
 */
public interface IPharmacy<T extends IProduct,U extends ISystemUser> extends IActive {

    /**
     * Returns the tradingName for this pharmacy.
     *
     * @return the tradingName for this pharmacy.
     */

    String getTradingName();

    /**
     * Sets the tradingName for this pharmacy.
     *
     * @param tradingName the title for this pharmacy.
     */

    void setTradingName(String tradingName);

    /**
     * Returns the registeredName for this pharmacy.
     *
     * @return the registeredName for this pharmacy.
     */

    String getRegisteredName();

    /**
     * Sets the tradingName for this pharmacy.
     *
     * @param registeredName the title for this pharmacy.
     */

    void setRegisteredName(String registeredName);

    /**
     * Returns the regNumber of the pharmacy.
     *
     * @return the regNumber of the pharmacy.
     */
    String getRegNumber();

    /**
     * Sets the regNumber of the user.
     *
     * @param regNumber the name of the user.
     */
    void setRegNumber(String regNumber);

    /**
     * Returns the {@link IContactDetails contactDetails} for this pharmacy.
     *
     * @return the {@link IContactDetails contactDetails} for this pharmacy.
     */
    IContactDetails getContactDetails();

    /**
     * Sets the {@link IContactDetails contactDetails} for this pharmacy.
     *
     * @param contactDetails the {@link IContactDetails contactDetails} for this pharmacy.
     */
    void setContactDetails(IContactDetails contactDetails);

    /**
     * Gets the {@link IAddress address} for this pharmacy.
     *
     * @returns address the {@link IAddress address} for this pharmacy.
     */


    IAddress getAddress();

    /**
     * Sets the {@link IAddress address} for this pharmacy.
     *
     * @param address the {@link IAddress address} for this pharmacy.
     */

    void setAddress(IAddress address);



    /**
     * Returns the list of {@link IProduct products} for this pharmacy.
     *
     * @return the list of {@link IProduct products} for this pharmacy.
     */

    Collection<T> getProducts();

    /**
     * sets the list of {@link IProduct products} for this pharmacy.
     *
     * @param products the  list of {@link IProduct products} for this pharmacy.
     */

    void setProducts(Collection<T> products);

    /**
     * Returns the logo for this pharmacy.
     *
     * @return the logo for this pharmacy.
     */

    ILogo getLogo();

    /**
     * Sets the logo for this client.
     *
     * @param logo for this client.
     */

    void setLogo(ILogo logo);

    /**
     * Returns the systemUsers for this pharmacy.
     *
     * @return the systemUsers for this pharmacy.
     */

    Collection<U> getSystemUsers();

    /**
     * Sets the systemUsers for this pharmacy.
     *
     * @param systemUsers for this pharmacy.
     */

   void setSystemUsers(Collection<U> systemUsers);

}
