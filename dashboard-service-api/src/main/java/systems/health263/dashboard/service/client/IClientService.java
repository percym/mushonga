package systems.health263.dashboard.service.client;

import systems.health263.dashboard.model.client.Client;
import systems.health263.dashboard.utility.enums.ClientDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Interface for Client entity
 *
 * @author Munyaradzi Takayindisa
 */
public interface IClientService {

    /**
     * Save an client.
     *
     * @param client the entity to save
     * @return the persisted entity
     */
    Client save(Client client);

    /**
     * Get all the client.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Client> findAll(Pageable pageable);

    /**
     * Get the "id" client.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Client findOne(Long id);

    /**
     * Delete the "id" client.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the clients corresponding to the query.
     *
     * @param spec     the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Client> findAll(Specification<Client> spec, Pageable pageable);

    /**
     * Get the first record in client table.
     *
     * @param clientDatabase the client database being used in the current Context.
     * @return the entity
     */
    Client findFirstByClientDatabase(ClientDatabase clientDatabase);

    /**
     * Get the first record in client table.
     *
     * @param clientDatabase the client database being used in the current Context.
     * @return the entity
     */
    Client findFirstByRegNumber(String regNumber);


}
