package systems.health263.dashboard.serviceimpl.permissions;

import systems.health263.dashboard.model.permissions.Permissions;
import systems.health263.dashboard.repository.permissions.PermissionsRepository;
import systems.health263.dashboard.service.permissions.IPermissionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class IPermissionsServiceImpl implements IPermissionsService {

    @PersistenceContext
    private EntityManager em;

    private PermissionsRepository permissionsRepository;

    public IPermissionsServiceImpl(PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }

    @Override
    public Permissions save(Permissions permissions) {
        return em.merge(permissions);
    }

    @Override
    public void delete(Long permissionId) {
        permissionsRepository.deleteById(permissionId);
    }

    @Override
    public  Permissions findOne(Long permissionId) {
        return permissionsRepository.getOne(permissionId);
    }

    @Override
    public Permissions getPermissionsByTitle(String title) {
            return permissionsRepository.findFirstByTitle(title);
    }

    @Override
    public Page<Permissions> findAll(Pageable pageable) {
        return permissionsRepository.findAll(pageable);
    }

    @Override
    public Page<Permissions> findAll(Specification spec, Pageable pageable) {
        return permissionsRepository.findAll(spec,pageable);
    }

    @Override
    public List<Permissions> findAll() {
        return permissionsRepository.findAll();
    }

}
