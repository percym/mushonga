package info.mushonga.search.endpoint.config.app.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to corp24
 * <p>
 * Properties configured in the properties file.
 *
 * @author Percy Mugadza
 */
@Data
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {

    private String secret;

    private long tokenValidityInSeconds;

    private String INTEGRATION_ID;

    private String INTEGRATION_KEY;


    }