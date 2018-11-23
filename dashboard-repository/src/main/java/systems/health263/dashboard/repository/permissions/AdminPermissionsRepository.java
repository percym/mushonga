package systems.health263.dashboard.repository.permissions;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.permissions.AdminPermissions;
import systems.health263.dashboard.model.permissions.Permissions;

/**
 * @author Percy Mugadza
 * <p>
 * Repository for Permissions entity
 */
@Repository
public interface AdminPermissionsRepository extends JpaRepository<AdminPermissions, Long>, JpaSpecificationExecutor<AdminPermissions> {

     AdminPermissions findFirstByTitle(String title);
}