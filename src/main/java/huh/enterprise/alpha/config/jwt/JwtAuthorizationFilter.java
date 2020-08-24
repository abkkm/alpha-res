package huh.enterprise.alpha.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_HEADER;
import static huh.enterprise.alpha.config.jwt.SecurityConstants.TOKEN_PREFIX;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtConfig jwtConfig;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authentication = getAuthentication(request);
        if (isNull(authentication)) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(TOKEN_HEADER);
        if (isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            try {
                var signingKey = jwtConfig.getJwtSecret().getBytes();

                var parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

                var username = parsedToken
                        .getBody()
                        .getSubject();

                var authorities = ((List<?>) parsedToken.getBody()
                        .get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(toList());

                if (isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (ExpiredJwtException e) {
                log.warn("Expired JWT={} failed={}", token, e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.warn("Unsupported JWT={} failed={}", token, e.getMessage());
            } catch (MalformedJwtException e) {
                log.warn("Invalid JWT={} failed={}", token, e.getMessage());
            } catch (SignatureException e) {
                log.warn("Invalid signature JWT={} failed={}", token, e.getMessage());
            } catch (IllegalArgumentException e) {
                log.warn("Empty or null JWT={} failed={}", token, e.getMessage());
            }
        }
        return null;
    }
}
