package systems.health263.dashboard.service.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.patient.MedicalAid;

/*
 * Service interface for Medical Aid.
 *
 * @author Munyaradzi Takayindisa
 *
 */
public interface IMedicalAidService {

    /**
     * Save a medical aid.
     *
     * @param medicalAid the entity to save
     * @return the persisted entity
     */
    MedicalAid save(MedicalAid medicalAid);

    /**
     * Get all the medical aid.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MedicalAid> findAll(Pageable pageable);

    /**
     * Get the "id" medical aid.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MedicalAid findOne(Long id);

    /**
     * Delete the "id" medical aid.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the medical aids corresponding to the query.
     *
     * @param spec     the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MedicalAid> findAll(Specification<MedicalAid> spec, Pageable pageable);

}
