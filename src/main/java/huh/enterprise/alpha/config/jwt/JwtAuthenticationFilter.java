package huh.enterprise.alpha.config.jwt;

import huh.enterprise.alpha.component.user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_AUDIENCE;
import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_HEADER;
import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_ISSUER;
import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_PREFIX;
import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_TYPE;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.util.stream.Collectors.toList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    private static final int TOKEN_DAYS_EXPIRATION = 5;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var username = request.getParameter(USERNAME_PARAM);
        var password = request.getParameter(PASSWORD_PARAM);
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        var user = ((User) authentication.getPrincipal());

        var roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        var signingKey = jwtConfig.getJwtSecret().getBytes();

        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(Date.from(Instant.now().plus(TOKEN_DAYS_EXPIRATION, ChronoUnit.DAYS)))
                .claim("rol", roles)
                .compact();

        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
    }
}
