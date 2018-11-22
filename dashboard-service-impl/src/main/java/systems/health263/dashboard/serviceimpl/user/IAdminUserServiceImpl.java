package systems.health263.dashboard.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.user.AdminUser;
import systems.health263.dashboard.repository.user.AdminUserRepository;
import systems.health263.dashboard.service.user.IAdminUserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service implementation for Admin user
 *
 * @author percym
 */
@Service
@Transactional
public class IAdminUserServiceImpl implements IAdminUserService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private AdminUserRepository adminUserRepository;

//    public IAdminUserServiceImpl(AdminUserRepository adminUserRepository) {
//        this.adminUserRepository = adminUserRepository;
//    }

    @Override
    public AdminUser save(AdminUser adminUser) {
        return entityManager.merge(adminUser);
    }

    @Override
    public void delete(Long userId) {
        adminUserRepository.delete(userId);
    }

    @Override
    public AdminUser getAdminUserById(Long userId) {
        return adminUserRepository.getAdminUserById(userId);
    }

    @Override
    public AdminUser getAdminUserByName(String userName) {
        return adminUserRepository.getAdminUserByUserName(userName);
    }

    @Override
    public AdminUser getAdminUserByEmail(String email) {
        return adminUserRepository.getAdminUserByEmail(email);
    }

    @Override
    public List<AdminUser> findAllByLocation_Client_Id(Long clientId) {
        return adminUserRepository.findAllByLocation_Client_Id(clientId);
    }

    @Override
    public List<AdminUser> findAll() {
        return adminUserRepository.findAll();
    }


}
