package info.mushonga.search.service.user;



import info.mushonga.search.model.user.PharmacySystemUser;
import info.mushonga.search.model.user.SystemUser;

import java.util.List;

public interface IPharmarcySystemUserService {

    PharmacySystemUser save(PharmacySystemUser systemUser);

    void delete(Long userId);

    PharmacySystemUser getPharmacySystemUsersById(Long id);

    PharmacySystemUser getPharmacySystemUsersByUserName(String userName);

    PharmacySystemUser getPharmacySystemUsersByEmail(String email);

}
