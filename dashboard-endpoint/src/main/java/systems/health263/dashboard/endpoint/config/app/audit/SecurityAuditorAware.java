package systems.health263.dashboard.endpoint.config.app.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import systems.health263.dashboard.endpoint.config.security.AppSystemUserDetailService;

import java.util.Optional;


/**
 * @author percym
 */
@Component
public class SecurityAuditorAware implements AuditorAware<AppSystemUserDetailService> {
    @Override
    public Optional<AppSystemUserDetailService> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (Optional<AppSystemUserDetailService>) authentication.getPrincipal();
    }
}
