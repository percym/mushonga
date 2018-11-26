package systems.health263.dashboard.service.permissions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.permissions.AdminPermissions;

import java.util.List;

public interface IAdminPermissionsService {

    AdminPermissions save(AdminPermissions permissions);

    void delete(Long permissionId);

    AdminPermissions findOne(Long AdminPermissions);

    AdminPermissions getPermissionsByTitle(String title);

    Page<AdminPermissions> findAll(Pageable pageable);

    Page<AdminPermissions> findAll(Specification spec, Pageable pageable);

    List<AdminPermissions> findAll();
}
