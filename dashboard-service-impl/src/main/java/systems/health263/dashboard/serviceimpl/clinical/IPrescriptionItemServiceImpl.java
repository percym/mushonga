package systems.health263.dashboard.serviceimpl.clinical;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.clinical.PrescriptionItem;
import systems.health263.dashboard.repository.clinical.PrescriptionItemRepository;
import systems.health263.dashboard.service.clinical.PrescriptionItemService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

/**
 * Service implementation for PrescriptionItem entity
 *
 * @author Munyaradzi Takayindisa
 */
@Slf4j
@Service
@Transactional
public class IPrescriptionItemServiceImpl implements PrescriptionItemService {

    @PersistenceContext
    private EntityManager em;

    private final PrescriptionItemRepository prescriptionItemRepository;

    public IPrescriptionItemServiceImpl(PrescriptionItemRepository prescriptionItemRepository) {
        this.prescriptionItemRepository = prescriptionItemRepository;
    }

    /**
     * Save a prescriptionItem.
     *
     * @param prescriptionItem the entity to save
     * @return the persisted entity
     */
    @Override
    public PrescriptionItem save(PrescriptionItem prescriptionItem) {
        log.debug("Request to save PrescriptionItems : {}", prescriptionItem);
        return em.merge(prescriptionItem);
    }

    /**
     * Get all the prescriptionItem.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrescriptionItem> findAll(Pageable pageable) {
        log.debug("Request to get all PrescriptionItems");
        return prescriptionItemRepository.findAll(pageable);
    }

    /**
     * Get one prescriptionItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PrescriptionItem findOne(Long id) {
        log.debug("Request to get PrescriptionItems : {}", id);
        return prescriptionItemRepository.getOne(id);
    }

    /**
     * Delete the prescriptionItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrescriptionItems : {}", id);
        prescriptionItemRepository.deleteById(id);
    }

    /**
     * Search for the prescriptionItem corresponding to the query.
     *
     * @param criteria the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<PrescriptionItem> findAll(Specification<PrescriptionItem> criteria, Pageable pageable) {
        log.debug("find by criteria : {}, page: {}", criteria, pageable);
        return prescriptionItemRepository.findAll(criteria, pageable);
    }

    /**
     * Return all items.
     *
     * @return the list of entities
     */

    @Override
    public List<PrescriptionItem> findAll() {
        return  prescriptionItemRepository.findAll();
    }

    @Override
    public Stream<PrescriptionItem> findAllByGenericNameNotNull() {
        return prescriptionItemRepository.findAllByGenericNameNotNull();
    }
}