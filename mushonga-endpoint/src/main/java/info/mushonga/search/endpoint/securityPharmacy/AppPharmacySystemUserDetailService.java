package info.mushonga.search.endpoint.securityPharmacy;

import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.service.user.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Component
public class AppPharmacySystemUserDetailService implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ISystemUserService systemUserService;

//    public AppSystemUserDetailService(IAdminUserService systemUserService) {
//        this.systemUserService = systemUserService;
//    }

    @Override
    public final UserDetails loadUserByUsername(String username) {
        final SystemUser user = this.systemUserService.getSystemUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword()).authorities(Collections.emptyList())
                .accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).build();
    }

}