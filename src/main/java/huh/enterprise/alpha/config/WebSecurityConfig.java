package huh.enterprise.alpha.config;

import huh.enterprise.alpha.component.user.UserService;
import huh.enterprise.alpha.config.jwt.JwtAuthenticationFilter;
import huh.enterprise.alpha.config.jwt.JwtAuthorizationFilter;
import huh.enterprise.alpha.config.jwt.JwtConfig;
import huh.enterprise.alpha.config.login.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * - Order(1) means it has first priority
 */
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Order(1)
    @Configuration
    public static class ApiWebConfiguration extends WebSecurityConfigurerAdapter {
        private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
        private final UserService userService;
        private final JwtConfig jwtConfig;

        @Autowired
        public ApiWebConfiguration(RestAuthenticationEntryPoint restAuthenticationEntryPoint, UserService userService, JwtConfig jwtConfig) {
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
            this.userService = userService;
            this.jwtConfig = jwtConfig;
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/", "/webjars/**", "/api/public**", "/api/users**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            // (1) This HttpSecurity will only be applicable to URLs that start with /api/
            http.antMatcher("/api/**")
                .cors().and().csrf().disable()
                .authorizeRequests()
                    .anyRequest().authenticated().and()
                .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint)
                    .and()
                    .logout()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtConfig))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // @formatter:on
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService);
        }

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Order(2)
    @Configuration
    public static class OAuth2WebConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.cors().and().csrf().disable()
            .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/external/oauth2");
            // @formatter:on
        }

        @Bean
        public DefaultOAuth2UserService defaultOAuth2UserService() {
            return new DefaultOAuth2UserService();
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return source;
    }



}
