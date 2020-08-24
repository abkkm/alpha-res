package huh.enterprise.alpha.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@EnableCaching
@Component
public class CacheConfig implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(asList("oauth2token", CacheNames.PROVIDER_NAMES));
    }

    public static class CacheNames {
        public static final String TOKENS = "oauth2token";
        public static final String PROVIDER_NAMES = "providerNames";
    }
}
