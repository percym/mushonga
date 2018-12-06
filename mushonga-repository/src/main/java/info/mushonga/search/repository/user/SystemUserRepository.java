package info.mushonga.search.repository.user;


import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.utility.enums.UserType;
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
public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, JpaSpecificationExecutor<SystemUser> {

    SystemUser getSystemUserByUserName(String userName);

    SystemUser getSystemUserByEmail(String email);

    List<SystemUser> findAll();

    List<SystemUser> findAllByActive(Boolean active);

    List<SystemUser>findAllByUserType(UserType userType);

}