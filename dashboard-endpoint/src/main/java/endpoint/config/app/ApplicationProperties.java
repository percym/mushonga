package endpoint.config.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Properties specific to Dova.
 * <p>
 * Properties configured in the test.properties file.
 *
 * @author Munyaradzi Takayindisa
 */
@Data
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
@Import({springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class})
public class ApplicationProperties {

    private String secret;

    private long tokenValidityInSeconds;

    private Boolean testEnvironment;

    private String memberLookupUrl;

    private String biometricUrl;

    private String apaceClaimUrl;

    private String logosDir;

    private String u;

    private String h;

    private String op;

    private boolean sendSms;

    public void setTestEnvironment(Boolean testEnvironment) {
        this.testEnvironment = testEnvironment;
        if (testEnvironment) {

        } else {

        }
    }

}