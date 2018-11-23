package systems.health263.dashboard.service.clinical;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import systems.health263.dashboard.model.clinical.DataICD10;

import java.util.List;
import java.util.stream.Stream;

/*
 * Service interface for prescriptionItem.
 *
 * @author Munyaradzi Takayindisa
 *
 */
public interface Icd10Service {

    /**
     * Save an idc10 item code.
     *
     * @param dataICD10 the entity to save
     * @return the persisted entity
     */
    DataICD10 save(DataICD10 dataICD10);

    /**
     * Get all the idc10 item.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DataICD10> findAll(Pageable pageable);

    /**
     * Get the "id" idc10 item.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DataICD10 findOne(Long id);

    /**
     * Delete the "id" idc10 item.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the idc10 items corresponding to the query.
     *
     * @param spec     the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DataICD10> findAll(Specification<DataICD10> spec, Pageable pageable);

    /**
     *Get all idc10 items corresponding to the query.
     *
     * @return the list of entities
     */

    List<DataICD10> findAll();

    /**
     * Search an idc10 items by codeCodeField.
     *
     * @param codeContains the search param
     * @return the persisted entity
     */
    List<DataICD10> findAllByCodeFieldContaining(String codeContains);

    /**
     * Search an idc10 items by DescriptionField.
     *
     * @param descriptionContains the search param
     * @return the persisted entity
     */

    List<DataICD10> findAllByDescriptionFieldContaining(String descriptionContains);
    /**
     *Get all idc10 items which have  a code field which is not null.
     *
     * @return the list of entities
     */
    Stream<DataICD10> findAllByCodeFieldNotNull();
}
