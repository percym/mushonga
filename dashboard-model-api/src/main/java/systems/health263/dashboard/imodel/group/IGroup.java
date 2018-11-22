package systems.health263.dashboard.imodel.group;


import systems.health263.dashboard.imodel.permissions.IPermissionConsumption;

import java.util.Collection;

/*
 * IGroup interface to hold Group entity.
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Percy Mugadza
 *
 */
public interface IGroup <T extends IPermissionConsumption> {
    /**
     * Returns the title for the Permissions.
     *
     * @return the title for the Permissions.
     */
    String getTitle();

    /**
     * Returns the title for the Permissions.
     *
     * @param title for the Permissions.
     */
    void setTitle(String title);

    /**
     * Returns the active status for the Permissions .
     *
     * @return the active status for the Permissions.
     */
    boolean getActive();
    /**
     * Sets the active status for the Permissions .
     *
     * @param  active active status for the Permissions.
     */
    void setActive(boolean active);

    /**
     * Returns the list of {@link IPermissionConsumption permissions}  for this group.
     *
     * @return the list of  {@link IPermissionConsumption permissions}  for this group.
     */
    Collection<T> getPermissions();

    /**
     * Sets the list of {@link IPermissionConsumption permissions} for this group.
     *
     * @param permissions the list of  {@link IPermissionConsumption permissions}  for this group.
     */
    void setPermissions(Collection<T> permissions);

}
