package huh.enterprise.alpha.component.oauth2;

import huh.enterprise.alpha.component.cache.OuraToken;

import java.util.Set;

public interface OAuth2Service {

    Set<OuraToken> getOuraTokens();
}
