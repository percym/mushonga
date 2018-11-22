package systems.health263.dashboard.repository.location;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.location.Location;

import java.util.List;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for Group entity
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {
    List<Location> findAllByClient_Id(Long clientId);
}