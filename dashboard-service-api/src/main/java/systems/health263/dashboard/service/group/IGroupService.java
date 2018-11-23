package systems.health263.dashboard.service.group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.group.Group;

import java.util.List;

public interface IGroupService {

    Group saveGroup(Group group);

    void deleteGroup(Long groupId);

    Group findOne(Long groupId);

    Group getGroupByTitle(String title);

    Page<Group> findAll(Pageable pageable);

    Page<Group> findAll(Specification spec, Pageable pageable);

    List<Group> findAll();
}
