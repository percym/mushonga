package systems.health263.dashboard.imodel.user;


import systems.health263.dashboard.imodel.general.IActive;
import systems.health263.dashboard.imodel.group.IGroup;
import systems.health263.dashboard.imodel.location.ILocation;

/**
 * IUser interface for the admin user class
 *
 * @author percym
 * */
public interface ISystemUser extends IActive {

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
     * gets the {@link ILocation location} of the user.
     *
     * @return {@link ILocation location} of the user.
     */
    ILocation<?> getLocation();

    /**
     * Sets the {@link ILocation location} of the user.
     *
     * @param location of the user.
     */
    void setLocation(ILocation<?> location);


    /**
     * Returns the list of {@link IGroup group} for this user.
     *
     * @return the list of {@link IGroup group} for this user.
     */
    IGroup<?> getGroup();

    /**
     * Sets the group {@link IGroup group} for this user.
     *
     * @param group  {@link IGroup group} for this user.
     */
    void setGroup(IGroup<?> group);

}
