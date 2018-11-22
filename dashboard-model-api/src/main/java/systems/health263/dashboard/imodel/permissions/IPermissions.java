package systems.health263.dashboard.imodel.permissions;

/*
 * IPermissions interface to hold Permissions entity.
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Percy Mugadza
 *
 */
public interface IPermissions {
    /**
     * Returns the id for the Permissions.
     *
     * @return the id for the Permissions.
     */
    Long getId();

    /**
     * Returns the id for the Permissions.
     *
     * @param id for the Permissions.
     */
    void setId(Long id);

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
    Boolean getActive();
    /**
     * Sets the active status for the Permissions .
     *
     * @param  active active status for the Permissions.
     */
    void setActive(Boolean active);

}
