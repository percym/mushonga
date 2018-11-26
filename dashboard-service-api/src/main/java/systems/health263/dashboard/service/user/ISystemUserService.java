package systems.health263.dashboard.service.user;


import systems.health263.dashboard.model.user.SystemUser;

import java.util.List;

public interface ISystemUserService {

    SystemUser saveSystemUser(SystemUser systemUser);

    void deleteSystemUser(Long userId);

    SystemUser getSystemUserById(Long userId);

    SystemUser getSystemUserByName(String userName);

    SystemUser getSystemUserByEmail(String email);

    List<SystemUser> findAllByLocation_Client_Id(Long clientId);

    List<SystemUser> findAll();
}
