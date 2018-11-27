package systems.health263.dashboard.endpoint.config.app.audit;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import systems.health263.dashboard.endpoint.config.security.AppSystemUserDetailService;

/**
 * @author percym
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SecurityAuditorAware();
    }
}
