package systems.health263.dashboard.service.location;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.location.Location;

import java.util.List;

public interface

ILocationService {

    Location save(Location location);

    void delete(Long locationId);

    Location findOne(Long locationId);

    Page<Location> findAll(Pageable pageable);

    Page<Location> findAll(Specification spec, Pageable pageable);

    List<Location> findAll();

    List<Location> findAllByClient_Id(Long clientId);
}
