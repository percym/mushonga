package systems.health263.dashboard.service.permissions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.permissions.Permissions;

import java.util.List;

public interface IPermissionsService {

    Permissions save(Permissions permissions);

    void delete(Long permissionId);

    Permissions findOne(Long permissionId);

    Permissions getPermissionsByTitle(String title);

    Page<Permissions> findAll(Pageable pageable);

    Page<Permissions> findAll(Specification spec, Pageable pageable);

    List<Permissions> findAll();
}
