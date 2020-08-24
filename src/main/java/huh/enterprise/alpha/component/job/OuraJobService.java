package huh.enterprise.alpha.component.job;

import huh.enterprise.alpha.component.diary.DiaryService;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.oauth2.OAuth2Service;
import huh.enterprise.alpha.component.oura.OuraService;
import huh.enterprise.alpha.component.oura.model.OuraQuery;
import huh.enterprise.alpha.component.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class OuraJobService {
    private final OuraService ouraService;
    private final DiaryService diaryService;
    private final OAuth2Service oAuth2Service;
    private static final BigDecimal TOTAL_SECONDS_PER_HOUR = new BigDecimal(3600);

    @Autowired
    public OuraJobService(OuraService ouraService, DiaryService diaryService, OAuth2Service oAuth2Service) {
        this.ouraService = ouraService;
        this.diaryService = diaryService;
        this.oAuth2Service = oAuth2Service;
    }

    // TODO: Need map each token to a user
    // Run at 09:00 AM every day
    @Scheduled(cron = "* * 9 * * ?")
    public void runDaily() {
        var tokens = oAuth2Service.getOuraTokens();
        if (isEmpty(tokens)) {
            return;
        }
        log.info("Start oura job found total_tokens={}", tokens.size());
        for (var token : tokens) {
            var sleepRecords = ouraService.selectSleepPeriods(OuraQuery.builder()
                    .start(LocalDate.now().minusYears(1))
                    .accessToken(token.getToken())
                    .build());
            log.info("Found oura records username={}, total={}", token.getUsername(), sleepRecords.getRecords().size());
            for (var record : sleepRecords.getRecords()) {
                BigDecimal totalHours = BigDecimal.valueOf(record.getTotal()).divide(TOTAL_SECONDS_PER_HOUR, 2, RoundingMode.CEILING);
                var createDiary = Diary.builder()
                        .totalSleepHours(totalHours)
                        .created(record.getSummaryDate().plusDays(1).atStartOfDay())
                        .user(User.builder().username(token.getUsername()).build())
                        .build();
                try {
                    diaryService.insertOrUpdate(createDiary);
                } catch (Exception exist) {
                    log.debug("Ignore record already exist", exist);
                }
            }

        }
    }

}
