package systems.health263.dashboard.serviceimpl.permissions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.permissions.AdminPermissions;
import systems.health263.dashboard.repository.permissions.AdminPermissionsRepository;
import systems.health263.dashboard.repository.permissions.PermissionsRepository;
import systems.health263.dashboard.service.permissions.IAdminPermissionsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service implementation for Permissions entity
 *
 * @author Percy Mugadza
 */
@Service
@Transactional
public class IAdminPermissionsServiceImpl implements IAdminPermissionsService {

    @PersistenceContext
    private EntityManager em;

    private AdminPermissionsRepository adminPermissionsRepository;

    public IAdminPermissionsServiceImpl(AdminPermissionsRepository adminPermissionsRepository) {
        this.adminPermissionsRepository = adminPermissionsRepository;
    }

    @Override
    public AdminPermissions save(AdminPermissions permissions) {
        return em.merge(permissions);
    }

    @Override
    public void delete(Long permissionId) {
        adminPermissionsRepository.deleteById(permissionId);
    }

    @Override
    public  AdminPermissions findOne(Long permissionId) {
        return adminPermissionsRepository.getOne(permissionId);
    }

    @Override
    public AdminPermissions getPermissionsByTitle(String title) {
            return adminPermissionsRepository.findFirstByTitle(title);
    }

    @Override
    public Page<AdminPermissions> findAll(Pageable pageable) {
        return adminPermissionsRepository.findAll(pageable);
    }

    @Override
    public Page<AdminPermissions> findAll(Specification spec, Pageable pageable) {
        return adminPermissionsRepository.findAll(spec,pageable);
    }

    @Override
    public List<AdminPermissions> findAll() {
        return adminPermissionsRepository.findAll();
    }

}
