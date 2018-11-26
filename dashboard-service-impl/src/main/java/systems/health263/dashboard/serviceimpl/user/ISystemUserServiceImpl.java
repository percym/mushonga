package com.health263.serviceimpl.user;

import com.health263.imodel.user.ISystemUser;
import com.health263.model.user.SystemUser;
import com.health263.repository.user.SystemUserRepository;
import com.health263.service.user.ISystemUserService;
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
        systemUserRepository.delete(userId);
    }

    @Override
    public SystemUser getSystemUserById(Long id) {
        return systemUserRepository.findOne(id);
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
    public List<SystemUser> findAllByLocation_Client_Id(Long clientId) {
        return systemUserRepository.findAllByLocation_Client_Id(clientId);
    }

    @Override
    public List<SystemUser> findAll() {
        return systemUserRepository.findAll();
    }

}
