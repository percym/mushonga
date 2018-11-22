package endpoint.config.app;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import systems.health263.dashboard.utility.enums.ClientDatabase;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.ordinalIndexOf;

/**
 * Filters incoming and sets the DataSource key to be used.
 *
 * @author Munyaradzi Takayindisa
 */
@Slf4j
@WebFilter("/*")
public class HttpRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequestWrapper httpServletRequest = (HttpServletRequestWrapper) servletRequest;
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            response.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

            String path = httpServletRequest.getRequestURI();
            if (!path.contains("/v2/api-docs") && !path.contains("/swagger-resources") &&
                    !path.matches(".*\\.(css|gif|ico|jpg|jpeg|js|mp3|png|svg|woff|woff2|html)")) {
                int indexLast = ordinalIndexOf(path, "/", 2);
                if (indexLast != -1) {
                    String jndiSwitch = path.substring(1, indexLast);
                    ClientDatabase clientDatabase = ClientDatabase.fromISO3Code(jndiSwitch);
                    ClientDatabaseContextHolder.setJndi(clientDatabase);
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                | SignatureException | UsernameNotFoundException e) {
            log.info("Security exception {}", e.getMessage());
            ((HttpServletResponse) servletResponse)
                    .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        // nothing to do here, but required by interface
    }

    @SuppressWarnings("unused")
    @Override
    public void destroy() {

        // nothing to do here, but required by interface
    }

}