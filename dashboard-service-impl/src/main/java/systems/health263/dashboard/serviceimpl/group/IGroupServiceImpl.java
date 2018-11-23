package systems.health263.dashboard.serviceimpl.group;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.group.Group;
import systems.health263.dashboard.repository.group.GroupRepository;
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
public class IGroupServiceImpl implements IGroupService {

    @PersistenceContext
    private EntityManager em;

    private GroupRepository groupRepository;

    public IGroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group saveGroup(Group group) {
        return em.merge(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public Group findOne(Long groupId) {
        return groupRepository.getOne(groupId);
    }

    @Override
    public Group getGroupByTitle(String title) {
        return groupRepository.getGroupByTitle(title);
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Page<Group> findAll(Specification spec, Pageable pageable) {
        return groupRepository.findAll(spec,pageable);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

}
