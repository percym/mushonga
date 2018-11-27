package systems.health263.dashboard.serviceimpl.drugnsundry;

import systems.health263.dashboard.model.drugnsundry.DrugNSundry;
import systems.health263.dashboard.repository.drugnsundry.DrugsNSundryRepository;
import systems.health263.dashboard.service.drugnsundry.IDrugNSundryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author percym
 */
@Transactional
@Service
public class IDrugNSundryServiceImpl implements IDrugNSundryService {

    private final DrugsNSundryRepository drugNSundryRepository;

    @PersistenceContext
    private EntityManager em;

    public IDrugNSundryServiceImpl(DrugsNSundryRepository drugNSundryRepository) {
        this.drugNSundryRepository = drugNSundryRepository;
    }
    /**
     * Get the "id" DrugNSundry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public DrugNSundry findOne(Long id) {
        return drugNSundryRepository.findOne(id);
    }
    /**
     * Get all the DrugNSundry.
     *  @return the list of entities
     */
    @Override
    public List<DrugNSundry> findAll() {
        return drugNSundryRepository.findAll();
    }

    @Override
    public Stream<DrugNSundry> getAllByGenericCodeNotNull() {
        return drugNSundryRepository.getAllByGenericCodeNotNull();
    }
}
