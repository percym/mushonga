package com.health263.repository.user;


import com.health263.imodel.user.ISystemUser;
import com.health263.imodel.user.IUser;
import com.health263.model.user.SystemUser;
import com.health263.model.user.User;
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

    List<SystemUser> findAllByLocation_Client_Id(Long clientId);

    List<SystemUser> findAll();

}