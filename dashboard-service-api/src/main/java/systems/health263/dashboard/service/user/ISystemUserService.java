package com.health263.service.user;

import com.health263.imodel.user.ISystemUser;
import com.health263.imodel.user.IUser;
import com.health263.model.user.SystemUser;
import com.health263.model.user.User;

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
