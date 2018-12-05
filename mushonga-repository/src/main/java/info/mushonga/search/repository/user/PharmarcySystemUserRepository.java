package info.mushonga.search.repository.user;


import info.mushonga.search.model.user.PharmacySystemUser;
import info.mushonga.search.model.user.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for User entity
 */
@Repository
public interface PharmarcySystemUserRepository extends JpaRepository<PharmacySystemUser, Long>, JpaSpecificationExecutor<SystemUser> {

    PharmacySystemUser getPharmacySystemUsersByUserName(String userName);

    PharmacySystemUser getPharmacySystemUsersByEmail(String email);

    PharmacySystemUser getPharmacySystemUsersById(Long id);


}