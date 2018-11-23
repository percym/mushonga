package systems.health263.dashboard.serviceimpl.group;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.group.AdminGroup;
import systems.health263.dashboard.repository.group.AdminGroupRepository;
import systems.health263.dashboard.service.group.IAdminGroupService;
import systems.health263.dashboard.service.group.IGroupService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service implementation for Patient entity
 *
 * @author Munyaradzi Takayindisa
 */
@Service
@Transactional
public class IAdminGroupServiceImpl implements IAdminGroupService {

    @PersistenceContext
    private EntityManager em;

    private AdminGroupRepository adminGroupRepository;

    public IAdminGroupServiceImpl(AdminGroupRepository adminGroupRepository) {
        this.adminGroupRepository = adminGroupRepository;
    }

    @Override
    public AdminGroup saveGroup(AdminGroup group) {
        return em.merge(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        adminGroupRepository.deleteById(groupId);
    }

    @Override
    public AdminGroup findOne(Long groupId) {
        return adminGroupRepository.getOne(groupId);
    }

    @Override
    public AdminGroup getGroupByTitle(String title) {
        return adminGroupRepository.getGroupByTitle(title);
    }

    @Override
    public Page<AdminGroup> findAll(Pageable pageable) {
        return adminGroupRepository.findAll(pageable);
    }

    @Override
    public Page<AdminGroup> findAll(Specification spec, Pageable pageable) {
        return adminGroupRepository.findAll(spec,pageable);
    }

    @Override
    public List<AdminGroup> findAll() {
        return adminGroupRepository.findAll();
    }

}
