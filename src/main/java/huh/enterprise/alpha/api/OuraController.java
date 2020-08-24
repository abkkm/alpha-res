package huh.enterprise.alpha.api;

import huh.enterprise.alpha.component.oura.OuraService;
import huh.enterprise.alpha.component.oura.model.OuraQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/oura")
public class OuraController {

    private final OuraService ouraService;

    @Autowired
    public OuraController(OuraService ouraService) {
        this.ouraService = ouraService;
    }

    @GetMapping("/sleep")
    public ResponseEntity get(@RegisteredOAuth2AuthorizedClient("oura") OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken token = authorizedClient.getAccessToken();
        var json = ouraService.selectSleepPeriods(OuraQuery.builder().accessToken(token.getTokenValue()).build());
        return ResponseEntity.ok(json);
    }

}
