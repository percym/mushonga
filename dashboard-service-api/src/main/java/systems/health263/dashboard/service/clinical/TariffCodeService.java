package systems.health263.dashboard.service.clinical;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import systems.health263.dashboard.model.clinical.TariffCode;

import java.util.List;
import java.util.stream.Stream;

/**
 * Service interface for Tariffs
 *
 * @author Munyaradzi Takayindisa
 */
public interface TariffCodeService {

    /**
     * Save an tariff.
     *
     * @param tariffCode the entity to save
     * @return the persisted entity
     */
    TariffCode save(TariffCode tariffCode);

    /**
     * Get all the tariffCodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TariffCode> findAll(Pageable pageable);

    /**
     * Get all the tariffCodes.
     *     * @return the list of entities
     */
    List<TariffCode> findAll();

    /**
     * Get the "id" tariffCode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TariffCode findOne(Long id);

    /**
     * Delete the "id" tariff.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the tariffs corresponding to the query.
     *
     * @param criteria     the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<TariffCode> findAll(TariffCode criteria, Pageable pageable) ;

    /**
     * Get all the streams of tariff.
     *
     * @return the list of entities
     */
    Stream<TariffCode> findAllByGenericNameNotNull();

}
