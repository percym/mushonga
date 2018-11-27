package systems.health263.dashboard.service.drugnsundry;


import systems.health263.dashboard.model.drugnsundry.DrugNSundry;

import java.util.List;
import java.util.stream.Stream;

/**Interface for DrugNSundry entity
 * @author percym
 */
public interface IDrugNSundryService {
    /**
     * Get the "id" DrugNSundry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DrugNSundry findOne(Long id);

    /**
     * Get all the DrugNSundry.
     *  @return the list of entities
     */
    List<DrugNSundry> findAll();

    /**
     * Stream all the DrugNSundry.
     *  @return the list of entities
     */
    Stream<DrugNSundry> getAllByGenericCodeNotNull();

    /**
     * Save aDrugNSundry.
     *
     * @param drugNSundry the entity to save
     * @return the persisted entity
     */
    DrugNSundry save(DrugNSundry drugNSundry);



}
