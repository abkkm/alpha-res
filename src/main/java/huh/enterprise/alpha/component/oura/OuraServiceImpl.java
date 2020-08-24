package huh.enterprise.alpha.component.oura;

import huh.enterprise.alpha.component.oura.model.OuraQuery;
import huh.enterprise.alpha.component.oura.model.OuraSleepRecords;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static huh.enterprise.alpha.component.oura.api.OuraApiQueryParam.Sleep.ACCESS_TOKEN_PARAM;
import static huh.enterprise.alpha.component.oura.api.OuraApiQueryParam.Sleep.START_PARAM;

@Slf4j
@Service
public class OuraServiceImpl implements OuraService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_PATH = "https://api.ouraring.com/v1";
    private static final String SLEEP_PERIODS_URL = BASE_PATH + "/sleep";
    private static final String ACTIVITY_SUMMARIES_URL = BASE_PATH + "/activity";
    private static final String READINESS_SUMMARIES_URL = BASE_PATH + "/readiness";
    private static final String USER_INFO_URL = BASE_PATH + "/userinfo";

    @Override
    public OuraSleepRecords selectSleepPeriods(OuraQuery query) {
        String uri = UriComponentsBuilder.fromUriString(SLEEP_PERIODS_URL)
                .queryParam(START_PARAM, query.getStart())
                .queryParam(ACCESS_TOKEN_PARAM, query.getAccessToken())
                .build()
                .toUriString();

        return Try.ofSupplier(() -> restTemplate.getForObject(uri, OuraSleepRecords.class))
                .onSuccess(result -> log.info("Select sleepPeriods access_token={}, start={}, response={}", query.getAccessToken(), query.getStart(), result))
                .onFailure(error -> log.error("Select activity summaries failed access_token={}, start={}", query.getAccessToken(), query.getStart(), error))
                .get();
    }

    @Override
    public String selectActivitySummaries(String token) {
        String uri = UriComponentsBuilder.fromUriString(ACTIVITY_SUMMARIES_URL)
                .build()
                .toUriString();

        return Try.ofSupplier(() -> restTemplate.getForObject(uri, String.class))
                .onSuccess(result -> log.info("Select activity summaries token={},json={}", token, result))
                .onFailure(result -> log.error("Select activity summaries failed json={}", result))
                .get();
    }

    @Override
    public String selectUserInfo(String token) {
        String uri = UriComponentsBuilder.fromUriString(USER_INFO_URL)
                .build()
                .toUriString();

        return Try.ofSupplier(() -> restTemplate.getForObject(uri, String.class))
                .onSuccess(result -> log.info("Select user info token={},json={}", token, result))
                .onFailure(result -> log.error("Select user info failed json={}", result))
                .get();
    }

}
