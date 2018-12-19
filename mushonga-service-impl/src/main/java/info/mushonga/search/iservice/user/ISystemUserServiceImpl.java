package info.mushonga.search.iservice.user;

import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.repository.user.SystemUserRepository;
import info.mushonga.search.service.user.ISystemUserService;
import info.mushonga.search.utility.enums.UserType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service implementation for Patient entity
 *
 * @author Munyaradzi Takayindisa
 */
@Service
@Transactional
public class ISystemUserServiceImpl implements ISystemUserService {

    @PersistenceContext
    private EntityManager em;

    private SystemUserRepository systemUserRepository;

    public ISystemUserServiceImpl(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public SystemUser saveSystemUser(SystemUser systemUser) {
        return em.merge(systemUser);
    }

    @Override
    public void deleteSystemUser(Long userId) {
        systemUserRepository.deleteById(userId);
    }

    @Override
    public SystemUser getSystemUserById(Long id) {
        return systemUserRepository.getSystemUserById(id);
    }

    @Override
    public SystemUser getSystemUserByName(String userName) {
        return systemUserRepository.getSystemUserByUserName(userName);
    }

    @Override
    public SystemUser getSystemUserByEmail(String email) {
        return systemUserRepository.getSystemUserByEmail(email);
    }

    @Override
    public List<SystemUser> findAllByActive(Boolean active) {
        return systemUserRepository.findAllByActive(active);
    }

    @Override
    public List<SystemUser> findAllByUserType(UserType userType) {
        return systemUserRepository.findAllByUserType(userType);
    }


    @Override
    public List<SystemUser> findAll() {
        return systemUserRepository.findAll();
    }

}
