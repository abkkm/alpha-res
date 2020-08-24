package huh.enterprise.alpha.api;

import huh.enterprise.alpha.component.cache.OuraToken;
import huh.enterprise.alpha.component.job.OuraJobService;
import huh.enterprise.alpha.config.oauth2.OAuthClientRegistrationId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static huh.enterprise.alpha.config.CacheConfig.CacheNames.TOKENS;

@RestController
@RequestMapping("/external/oauth2")
public class OAuth2Controller {
    private final CacheManager cacheManager;
    private final DefaultOAuth2UserService defaultOAuth2UserService;
    private final OuraJobService ouraJobService;

    @Autowired
    public OAuth2Controller(CacheManager cacheManager, DefaultOAuth2UserService defaultOAuth2UserService, OuraJobService ouraJobService) {
        this.cacheManager = cacheManager;
        this.defaultOAuth2UserService = defaultOAuth2UserService;
        this.ouraJobService = ouraJobService;
    }

    // Should we store token in Map<username, token> or Set<OuraToken>
    @SuppressWarnings("unchecked")
    @GetMapping("")
    public ResponseEntity<?> loadCache(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        Optional<Cache> cacheHolder = Optional.ofNullable(cacheManager.getCache(TOKENS));
        cacheHolder.ifPresent(cache -> {
            String registrationId = authorizedClient.getClientRegistration().getRegistrationId().toLowerCase();
            var oauth2User = defaultOAuth2UserService.loadUser(new OAuth2UserRequest(authorizedClient.getClientRegistration(), authorizedClient.getAccessToken()));
            var username = (String) oauth2User.getAttributes().get("email");
            var token = authorizedClient.getAccessToken().getTokenValue();
            Set<OuraToken> tokens = Optional.ofNullable(cache.get(registrationId, Set.class))
                    .orElseGet(HashSet::new);
            tokens.add(OuraToken.builder()
                    .token(token)
                    .username(username)
                    .build());
            cache.put(registrationId, tokens);
        });
        // Refresh daily oura job
        if (authorizedClient.getClientRegistration().getRegistrationId().toLowerCase().equalsIgnoreCase(OAuthClientRegistrationId.OURA)) {
            ouraJobService.runDaily();
        }
        return ResponseEntity.ok(authorizedClient.getAccessToken());
    }
}
