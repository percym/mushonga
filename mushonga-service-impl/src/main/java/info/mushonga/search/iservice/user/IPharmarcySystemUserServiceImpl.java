package info.mushonga.search.iservice.user;

import info.mushonga.search.model.user.PharmacySystemUser;
import info.mushonga.search.repository.user.PharmarcySystemUserRepository;
import info.mushonga.search.service.user.IPharmarcySystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service implementation for Patient entity
 *
 * @author Munyaradzi Takayindisa
 */
@Service
@Transactional
public class IPharmarcySystemUserServiceImpl implements IPharmarcySystemUserService {

    @PersistenceContext
    private EntityManager em;

    private final PharmarcySystemUserRepository pharmarcySystemUserRepository;

    public IPharmarcySystemUserServiceImpl(PharmarcySystemUserRepository pharmarcySystemUserRepository) {
        this.pharmarcySystemUserRepository = pharmarcySystemUserRepository;
    }


    @Override
    public PharmacySystemUser save(PharmacySystemUser systemUser) {
        return em.merge(systemUser);
    }

    @Override
    public void delete(Long userId) {
        pharmarcySystemUserRepository.deleteById(userId);

    }

    @Override
    public PharmacySystemUser getPharmacySystemUsersById(Long id) {
        return pharmarcySystemUserRepository.getPharmacySystemUsersById(id);
    }

    @Override
    public PharmacySystemUser getPharmacySystemUsersByUserName(String userName) {
        return pharmarcySystemUserRepository.getPharmacySystemUsersByUserName(userName);
    }

    @Override
    public PharmacySystemUser getPharmacySystemUsersByEmail(String email) {
        return pharmarcySystemUserRepository.getPharmacySystemUsersByEmail(email);
    }
}
