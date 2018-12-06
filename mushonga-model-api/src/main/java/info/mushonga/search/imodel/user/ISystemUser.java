package info.mushonga.search.imodel.user;


import info.mushonga.search.imodel.account.IAccount;
import info.mushonga.search.imodel.general.IActive;
import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.UserType;

import java.util.Collection;

/**
 * IUser interface for the User class
 *
 *@author Munyaradzi Takayindisa
 */
public interface ISystemUser  extends IActive {

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
     * Returns the {@link UserType userType} for this ISystemUser.
     *
     * @return the {@link UserType userType} for this com.health263.model.encounter.
     */
    UserType getUserType();

    /**
     * Sets the {@link UserType userType} for this ISystemUser.
     *
     * @param userType the {@link UserType userType} for thisISystemUser.
     */
    void setUserType(UserType userType);

    /**
     * Returns the {@link AccountType accountType} for this ISystemUser.
     *
     * @return the {@link AccountType accountType}  for this ISystemUser.
     */
    AccountType getAccountType();

    /**
     * Sets the {@link AccountType accountType} for this ISystemUser.
     *
     * @param accountType the {@link AccountType accountType} for this ISystemUser.
     */
    void setAccountType(AccountType accountType);

    /**
     * Returns the {@link IAccount<?,?> account} for this ISystemUser.
     *
     * @return the {@link IAccount<?,?> account}  for this ISystemUser.
     */
    IAccount<?,?> getAccount();

    /**
     * Sets the {@link IAccount<?,?> account} for this ISystemUser.
     *
     * @param  account {@link IAccount<?,?> account}  for this ISystemUser.
     */
    void setAccount(IAccount<?,?> account);

    /**
     * Returns the list of {@link IPharmacy pharmacy} for this user.
     *
     * @return the list of {@link IPharmacy IPharmacy} for this user.
     */

    IPharmacy<?,?> getPharmacy();

    /**
     * sets the list of {@link IPharmacy pharmacy} for this user.
     *
     * @param pharmacy the  list of {@link IPharmacy pharmacy} for this user.
     */
    void setPharmacy(IPharmacy<?,?> pharmacy);

}
