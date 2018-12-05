package info.mushonga.search.endpoint.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import info.mushonga.search.endpoint.config.jwt.JWTConfigurer;
import info.mushonga.search.endpoint.config.jwt.TokenProvider;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .httpBasic() // optional, if you want to access
//                  .and()     // the services from a browser
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/*/api/login").permitAll()
                .antMatchers("/*/api/register").permitAll()
                .antMatchers("/public").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfigurer(this.tokenProvider));
        // @formatter:on
    }

}