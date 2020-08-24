package huh.enterprise.alpha.component.oauth2;

import huh.enterprise.alpha.component.cache.OuraToken;
import huh.enterprise.alpha.config.oauth2.OAuthClientRegistrationId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static huh.enterprise.alpha.config.CacheConfig.CacheNames.TOKENS;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {
    private final CacheManager cacheManager;

    @Autowired
    public OAuth2ServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<OuraToken> getOuraTokens() {
        Optional<Cache> cacheHolder = Optional.ofNullable(cacheManager.getCache(TOKENS));
        Set<OuraToken> tokens = cacheHolder
                .map(cache -> cache.get(OAuthClientRegistrationId.OURA, Set.class))
                .orElseGet(HashSet::new);
        return tokens;
    }

}
