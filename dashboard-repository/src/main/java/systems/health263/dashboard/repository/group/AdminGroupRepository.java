package systems.health263.dashboard.repository.group;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.group.AdminGroup;

/**
 * @author percym
 * <p>
 * Repository for Group entity
 */
@Repository
public interface AdminGroupRepository extends JpaRepository<AdminGroup, Long>, JpaSpecificationExecutor<AdminGroup> {

    AdminGroup getGroupByTitle(String groupTitle);
}