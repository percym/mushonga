package systems.health263.dashboard.service.group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.group.AdminGroup;

import java.util.List;

public interface IAdminGroupService {

    AdminGroup saveGroup(AdminGroup adminGroup);

    void deleteGroup(Long groupId);

    AdminGroup findOne(Long groupId);

    AdminGroup getGroupByTitle(String title);

    Page<AdminGroup> findAll(Pageable pageable);

    Page<AdminGroup> findAll(Specification spec, Pageable pageable);

    List<AdminGroup> findAll();
}
