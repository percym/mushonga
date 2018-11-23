package systems.health263.dashboard.serviceimpl.patient;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.patient.MedicalAid;
import systems.health263.dashboard.repository.patient.MedicalAidRepository;
import systems.health263.dashboard.service.patient.IMedicalAidService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service implementation for MedicalAid entity
 *
 * @author Munyaradzi Takayindisa
 */
@Slf4j
@Service
@Transactional
public class IMedicalAidServiceImpl implements IMedicalAidService {

    @PersistenceContext
    private EntityManager em;

    private final MedicalAidRepository medicalAidRepository;

    public IMedicalAidServiceImpl(MedicalAidRepository medicalAidRepository) {
        this.medicalAidRepository = medicalAidRepository;
    }

    /**
     * Save a medicalAid.
     *
     * @param medicalAid the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicalAid save(MedicalAid medicalAid) {
        log.debug("Request to save MedicalAids : {}", medicalAid);
        return em.merge(medicalAid);
    }

    /**
     * Get all the medicalAid.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalAid> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalAids");
        return medicalAidRepository.findAll(pageable);
    }

    /**
     * Get one medicalAid by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MedicalAid findOne(Long id) {
        log.debug("Request to get MedicalAids : {}", id);
        return medicalAidRepository.getOne(id);
    }

    /**
     * Delete the medicalAid by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalAids : {}", id);
        medicalAidRepository.deleteById(id);
        //medicalAidSearchRepository.delete(id);
    }

    /**
     * Search for the medicalAid corresponding to the query.
     *
     * @param criteria the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<MedicalAid> findAll(Specification<MedicalAid> criteria, Pageable pageable) {
        log.debug("find by criteria : {}, page: {}", criteria, pageable);
        return medicalAidRepository.findAll(criteria, pageable);
    }
}