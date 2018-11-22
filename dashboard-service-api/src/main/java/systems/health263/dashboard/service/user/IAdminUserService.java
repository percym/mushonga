package systems.health263.dashboard.service.user;

import systems.health263.dashboard.model.user.AdminUser;

import java.util.List;

public interface IAdminUserService {

    AdminUser save(AdminUser adminUser);

    void delete(Long userId);

    AdminUser getAdminUserById(Long userId);

    AdminUser getAdminUserByName(String userName);

    AdminUser getAdminUserByEmail(String email);

    List<AdminUser> findAllByLocation_Client_Id(Long clientId);

    List<AdminUser> findAll();
}
