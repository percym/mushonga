package systems.health263.dashboard.repository.group;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.group.Group;

/**
 * @author percym
 * <p>
 * Repository for Group entity
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

    Group getGroupByTitle(String groupTitle);
}