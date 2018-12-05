package info.mushonga.search.imodel.user;


import info.mushonga.search.imodel.general.IActive;
import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.utility.enums.UserType;

import java.util.Collection;

/**
 * IUser interface for the IPharmacySystemUser
 *@param <T> any class that implememts IPharmarcy
 *@author percym
 */
public interface IPharmacySystemUser<T extends IPharmacy <?,?>> extends IActive {

    /**
     * Returns the userName of the user.
     *
     * @return the userName of the user.
     */
    String getUserName();

    /**
     * Sets the userName of the user.
     *
     * @param userName the name of the user.
     */
    void setUserName(String userName);

    /**
     * Returns the email of the user.
     *
     * @return the email of the user.
     */
    String getEmail();

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user.
     */
    void setEmail(String email);

    /**
     * Returns the phone of the user.
     *
     * @return the phone of the user.
     */
    String getPhone();

    /**
     * Sets the phone of the user.
     *
     * @param phone the email of the user.
     */
    void setPhone(String phone);


    /**
     * Returns the password of the user.
     *
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user.
     */
    void setPassword(String password);

    /**
     * Returns the {@link UserType userType} for this user.
     *
     * @return the {@link UserType userType} for this user.
     */
    UserType getUserType();

    /**
     * Sets the {@link UserType userType} for this user.
     *
     * @param userType the {@link UserType userType} for this user.
     */
    void setUserType(UserType userType);

    /**
     * Returns the list of {@link IPharmacy pharmacy} for this user.
     *
     * @return the list of {@link IPharmacy IPharmacy} for this user.
     */

    Collection<T> getPharmacies();

    /**
     * sets the list of {@link IPharmacy pharmacy} for this user.
     *
     * @param pharmacies the  list of {@link IPharmacy pharmacy} for this user.
     */
    void setPharmacies(Collection<T> pharmacies);


}
