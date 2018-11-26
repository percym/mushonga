package systems.health263.dashboard.service.clinical;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.clinical.PrescriptionItem;

import java.util.List;
import java.util.stream.Stream;

/*
 * Service interface for prescriptionItem.
 *
 * @author Munyaradzi Takayindisa
 *
 */
public interface PrescriptionItemService {

    /**
     * Save an prescriptionItem.
     *
     * @param prescriptionItem the entity to save
     * @return the persisted entity
     */
    PrescriptionItem save(PrescriptionItem prescriptionItem);

    /**
     * Get all the prescriptionItem.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PrescriptionItem> findAll(Pageable pageable);

    /**
     * Get the "id" prescriptionItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PrescriptionItem findOne(Long id);

    /**
     * Delete the "id" prescriptionItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the prescriptionItems corresponding to the query.
     *
     * @param spec     the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PrescriptionItem> findAll(Specification<PrescriptionItem> spec, Pageable pageable);


    /**
     * Return all items.
     *
     * @return the list of entities
     */
    List<PrescriptionItem> findAll();
    /**
     * Stream all the prescriptionItem with name not null.
     *
     * @return the list of entities
     */
    Stream<PrescriptionItem> findAllByGenericNameNotNull();


}
