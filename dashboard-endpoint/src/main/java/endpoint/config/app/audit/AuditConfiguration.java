package endpoint.config.app.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import systems.health263.dashboard.endpoint.config.security.AppSystemUserDetailService;

/**
 * @author percym
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {
    @Bean
    public AuditorAware<AppSystemUserDetailService> auditorProvider() {
        return new SecurityAuditorAware();
    }
}
