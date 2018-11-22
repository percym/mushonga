package systems.health263.dashboard.serviceimpl.client;

import org.springframework.beans.factory.annotation.Autowired;
import systems.health263.dashboard.model.client.Client;
import systems.health263.dashboard.repository.client.ClientRepository;
import systems.health263.dashboard.service.client.IClientService;
import systems.health263.dashboard.utility.enums.ClientDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Service implementation for Client entity
 *
 * @author Munyaradzi Takayindisa
 */
@Slf4j
@Service
@Transactional
public class IClientServiceImpl implements IClientService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;



    /**
     * Save a client.
     *
     * @param client the entity to save
     * @return the persisted entity
     */
    @Override
    public Client save(Client client) {
        log.debug("Request to save Clients : {}", client);
        return entityManager.merge(client);
    }

    /**
     * Get all the client.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        log.debug("Request to get all Clients");
        return clientRepository.findAll(pageable);
    }

    /**
     * Get one client by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Client findOne(Long id) {
        log.debug("Request to get Clients : {}", id);
        return clientRepository.getOne(id);
    }

    /**
     * Delete the client by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clients : {}", id);
        clientRepository.deleteById(id);
    }

    /**
     * Search for the client corresponding to the query.
     *
     * @param criteria the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Client> findAll(Specification<Client> criteria, Pageable pageable) {
        log.debug("find by criteria : {}, page: {}", criteria, pageable);
        return clientRepository.findAll(criteria, pageable);
    }

    /**
     * Get the first record in client table.
     *
     * @param clientDatabase the client database being used in the current Context.
     * @return the entity
     */
    @Override
    public Client findFirstByClientDatabase(ClientDatabase clientDatabase) {
        return clientRepository.findFirstByClientDatabase(clientDatabase);
    }

    @Override
    public Client findFirstByRegNumber(String regNumber) {
        return clientRepository.findFirstByRegNumber(regNumber);
    }
}