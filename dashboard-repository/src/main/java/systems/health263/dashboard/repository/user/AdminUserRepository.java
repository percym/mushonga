package systems.health263.dashboard.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.user.AdminUser;

import java.util.List;

/**
 * @author percym
 */
@Repository
public interface AdminUserRepository  extends JpaRepository<AdminUser,Long>,JpaSpecificationExecutor<AdminUser>{
    AdminUser getAdminUserByUserName(String userName);

    AdminUser getAdminUserById(Long id);

    AdminUser getAdminUserByEmail(String email);

    List<AdminUser> findAllByLocation_Client_Id(Long clientId);

    List<AdminUser> findAll();

}
