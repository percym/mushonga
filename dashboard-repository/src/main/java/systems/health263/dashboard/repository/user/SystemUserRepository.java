package systems.health263.dashboard.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.user.SystemUser;

import java.util.List;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for User entity
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, JpaSpecificationExecutor<SystemUser> {

    SystemUser getSystemUserByUserName(String userName);

    SystemUser getSystemUserByEmail(String email);

    List<SystemUser> findAllByLocation_Client_Id(Long clientId);

    List<SystemUser> findAll();

}