package huh.enterprise.alpha.component.oura.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OuraSleepRecord {
    private Long id;

    @JsonProperty("awake")
    private int awake;

    @JsonProperty("hr_lowest")
    private int hrLowest;

    @JsonProperty("onset_latency")
    private int onsetLatency;

    @JsonProperty("bedtime_start")
    private ZonedDateTime bedtimeStart;

    // Date when the sleep period ended.
    @JsonProperty("summary_date")
    private LocalDate summaryDate;

    @JsonProperty("is_longest")
    private int isLongest;

    @JsonProperty("bedtime_start_delta")
    private int bedtimeStartDelta;

    @JsonProperty("score_latency")
    private int scoreLatency;

    // Restlessness of the sleep time, i.e. percentage of sleep time when the user was moving.
    @JsonProperty("restless")
    private int restless;

    // Sleep score represents overall sleep quality during the sleep period. It is calculated as a weighted average of sleep score contributors that represent one aspect of sleep quality each. The sleep score contributor values are also available as separate parameters.
    @JsonProperty("score")
    private int score;

    @JsonProperty("hypnogram_5min")
    private String hypnogramFiveMinutes;

    @JsonProperty("hr_average")
    private double hrAverage;

    // Index of the sleep period among sleep periods with the same summary_date, where 0 = first sleep period of the day.
    @JsonProperty("period_id")
    private int periodId;

    @JsonProperty("rmssd_5min")
    private List<Integer> rmssdFiveMinutes;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("temperature_trend_deviation")
    private double temperatureTrendDeviation;

    @JsonProperty("bedtime_end_delta")
    private int bedtimeEndDelta;

    // Represents total sleep time's (see sleep.total) contribution for sleep quality. The value depends on age of the user - the younger, the more sleep is needed for good score. The weight of sleep.score_total in sleep score calculation is 0.35.
    @JsonProperty("score_total")
    private int scoreTotal;

    @JsonProperty("score_efficiency")
    private int scoreEfficiency;

    @JsonProperty("score_deep")
    private int scoreDeep;

    @JsonProperty("score_disturbances")
    private int scoreDisturbance;

    @JsonProperty("midpoint_time")
    private int midpointTime;

    @JsonProperty("deep")
    private int deep;

    @JsonProperty("score_alignment")
    private int scoreAlignment;

    // Timezone offset from UTC as minutes. For example, EEST (Eastern European Summer Time, +3h) is 180. PST (Pacific Standard Time, -8h) is -480. Note that timezone information is also available in the datetime values themselves, see for example.bedtime_start
    @JsonProperty("timezone")
    private int timezone;

    // Average respiratory rate.
    @JsonProperty("breath_average")
    private float breathAverage;

    @JsonProperty("midpoint_at_delta")
    private int midpointAtDelta;

    @JsonProperty("bedtime_end")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private ZonedDateTime bedtimeEnd;

    @JsonProperty("rem")
    private int rem;

    @JsonProperty("rmssd")
    private int rmssd;

    // Skin temperature deviation from the long-term temperature average.
    @JsonProperty("temperature_delta")
    private float temperatureDelta;

    @JsonProperty("score_rem")
    private int scoreRem;

    @JsonProperty("efficiency")
    private int efficiency;

    /**
     * Type: Int
     * Unit: seconds
     *
     * Total amount of sleep registered during the sleep period
     * (sleep.total = sleep.rem + sleep.light + sleep.deep).
     */
    @JsonProperty("total")
    private int total;

    @JsonProperty("hr_5min")
    private List<Integer> hrFiveMinutes;

    @JsonProperty("light")
    private int light;

    @JsonProperty("temperature_deviation")
    private double temperatureDeviation;
}
