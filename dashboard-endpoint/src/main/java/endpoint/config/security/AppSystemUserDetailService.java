package endpoint.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import systems.health263.dashboard.model.user.AdminUser;
import systems.health263.dashboard.service.user.IAdminUserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Component
public class AppSystemUserDetailService implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private  IAdminUserService systemUserService;

//    public AppSystemUserDetailService(IAdminUserService systemUserService) {
//        this.systemUserService = systemUserService;
//    }

    @Override
    public final UserDetails loadUserByUsername(String username) {
        final AdminUser user = this.systemUserService.getAdminUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword()).authorities(Collections.emptyList())
                .accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).build();
    }

}