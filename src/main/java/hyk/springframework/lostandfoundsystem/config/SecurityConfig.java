package hyk.springframework.lostandfoundsystem.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Htoo Yanant Khin
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PersistentTokenRepository persistentTokenRepository;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("Configure HttpSecurity - Start");
        http
                .authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/h2-console/**").permitAll() // don't use in production
                    .antMatchers("/webjars/**", "/images/**", "/css/**", "/register", "/login").permitAll();
        })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // Override config for default login page
                .formLogin(loginConfigurer -> {
                    loginConfigurer
                            .loginProcessingUrl("/login")
                            .loginPage("/login").permitAll()
                            .successForwardUrl("/")
                            .defaultSuccessUrl("/", true)
                            .failureUrl("/login/?error");
                })
//                .formLogin()
//                .and()
                // Override config for default logout page
                .logout(logoutConfigurer -> {
                    logoutConfigurer
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                            .logoutSuccessUrl("/login/?logout")
                            .permitAll()
                            .deleteCookies("JSESSIONID");
                })

//                .logout()
                .httpBasic()

                // remember me using persistent token
                .and().rememberMe()
                    .tokenValiditySeconds(7 * 24 * 60 * 60) // expiration time: 7 days
                    .tokenRepository(persistentTokenRepository)
                    .userDetailsService(userDetailsService)
                .and().csrf().disable();

                // in-memory remember me config
//                .rememberMe()
//                .key("hyk")
//                .userDetailsService(userDetailsService);

        // h2-console config, h2 use frame and spring security blocks frame
        http.headers().frameOptions().sameOrigin();
        log.debug("Configure HttpSecurity - End");
    }

    // needed for use with Spring Data JPA SPeL
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
