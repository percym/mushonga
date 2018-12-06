package info.mushonga.search.service.user;



import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.utility.enums.UserType;

import java.util.List;

public interface ISystemUserService {

    SystemUser saveSystemUser(SystemUser systemUser);

    void deleteSystemUser(Long userId);

    SystemUser getSystemUserById(Long userId);

    SystemUser getSystemUserByName(String userName);

    SystemUser getSystemUserByEmail(String email);

    List<SystemUser> findAllByActive(Boolean active);

    List<SystemUser>findAllByUserType(UserType userType);

    List<SystemUser> findAll();
}
