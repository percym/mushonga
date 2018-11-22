package systems.health263.dashboard.repository.client;

import systems.health263.dashboard.model.client.Client;
import systems.health263.dashboard.utility.enums.ClientDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for Encounter entity
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client findFirstByClientDatabase(ClientDatabase clientDatabase);
    Client findFirstByRegNumber(String regNumber);

}