package systems.health263.dashboard.serviceimpl.clinical;



import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.clinical.DataICD10;
import systems.health263.dashboard.repository.clinical.Icd10Repository;
import systems.health263.dashboard.service.clinical.Icd10Service;

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
public class Icd10ServiceImpl implements Icd10Service {

    @PersistenceContext
    private EntityManager em;

    private final Icd10Repository icd10Repository;

    public Icd10ServiceImpl(Icd10Repository icd10Repository) {
        this.icd10Repository = icd10Repository;
    }

    /**
     * Save an Save an idc10 item code
     *
     * @param dataICD10 the entity to save
     * @return the persisted entity
     */
    @Override
    public DataICD10 save(DataICD10 dataICD10) {
        log.debug("Request to save an ICD10 item : {}", dataICD10);
        return em.merge(dataICD10);
    }

    /**
     * Get all the idc10 items.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DataICD10> findAll(Pageable pageable) {
        log.debug("Request to get all icd10 items");
        return icd10Repository.findAll(pageable);
    }

    /**
     * Get one idc10item by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DataICD10 findOne(Long id) {
        log.debug("Request to get a specific item : {}", id);
        return icd10Repository.getOne(id);
    }

    /**
     * Delete the idc10item by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrescriptionItems : {}", id);
        icd10Repository.deleteById(id);
    }

    /**
     * Search for the idc10item corresponding to the query.
     *
     * @param criteria the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DataICD10> findAll(Specification<DataICD10> criteria, Pageable pageable) {
        log.debug("find by criteria : {}, page: {}", criteria, pageable);
        return icd10Repository.findAll(criteria, pageable);
    }

    /**
     *Get all idc10 items corresponding to the query.
     *
     * @return the list of entities
     */

    @Override
    public List<DataICD10> findAll() {
        return icd10Repository.findAll();
    }
    /**
     * Search an idc10 items by codeCodeField.
     *
     * @param codeContains the search param
     * @return the persisted entity
     */

    @Override
    public List<DataICD10> findAllByCodeFieldContaining(String codeContains) {
        return icd10Repository.findAllByCodeFieldContaining(codeContains);
    }
    /**
     * Search an idc10 items by DescriptionField.
     *
     * @param descriptionContains the search param
     * @return the persisted entity
     */

    @Override
    public List<DataICD10> findAllByDescriptionFieldContaining(String descriptionContains) {
        return icd10Repository.findAllByDescriptionFieldContaining(descriptionContains);
    }
    /**
     *Get all idc10 items which have  a code field which is not null.
     *
     * @return the list of entities
     */
    @Override
    public Stream<DataICD10> findAllByCodeFieldNotNull() {
        return icd10Repository.findAllByCodeFieldNotNull();
    }
}