package systems.health263.dashboard.serviceimpl.location;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.location.Location;
import systems.health263.dashboard.repository.location.LocationRepository;
import systems.health263.dashboard.service.location.ILocationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service implementation for ILocationService
 *
 * @author Percy Mugadza
 */
@Service
@Transactional
public class ILocationServiceImpl implements ILocationService {

    @PersistenceContext
    private EntityManager em;

    private LocationRepository locationRepository;

    public ILocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location save(Location location) {
        return em.merge(location);
    }

    @Override
    public void delete(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    public Location findOne(Long locationId) {
        return locationRepository.getOne(locationId);
    }

    @Override
    public Page<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    @Override
    public Page<Location> findAll(Specification spec, Pageable pageable) {
        return locationRepository.findAll(spec,pageable);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findAllByClient_Id(Long clientId) {
        return locationRepository.findAllByClient_Id(clientId);
    }

}
