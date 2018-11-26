package systems.health263.dashboard.endpoint.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import systems.health263.dashboard.endpoint.config.app.util.ApplicationProperties;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * TokenProvider for authentication into the app
 *
 * @author Munyaradzi Takayindisa
 */
@Component
public class TokenProvider {

    private final String secretKey;

    private final long tokenValidityInMilliseconds;

    private final UserDetailsService userService;

//    @Value("${app.jwtSecret}")
    private String secret ;


    public TokenProvider(ApplicationProperties properties, UserDetailsService userService) {
        this.secretKey = Base64.getEncoder().encodeToString(properties.getSecret().getBytes(Charset.forName("UTF-8")));
        this.tokenValidityInMilliseconds = (properties.getTokenValidityInSeconds()*1000 );
        this.userService = userService;
    }

    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(username)
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token)
                .getBody().getSubject();
        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

}
