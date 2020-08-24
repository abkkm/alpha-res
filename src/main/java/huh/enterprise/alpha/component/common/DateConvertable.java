package huh.enterprise.alpha.component.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public interface DateConvertable {

    default Timestamp getTimestamp(LocalDateTime date) {
        return Timestamp.valueOf(date);
    }

    default Timestamp getTimestampStartOfDay(LocalDateTime date) {
        return Timestamp.valueOf(date.toLocalDate().atStartOfDay());
    }

    default Timestamp getTimestampEndOfDay(LocalDateTime date) {
        return Timestamp.valueOf(date.with(LocalTime.MAX).minus(1, ChronoUnit.HOURS));
    }

    default Timestamp parseTimestampDefaultNow(LocalDateTime date) {
        return Optional.ofNullable(date)
                .map(d -> Timestamp.valueOf(d.toLocalDate().atStartOfDay()))
                .orElse(Timestamp.valueOf(LocalDateTime.now().toLocalDate().atStartOfDay()));
    }

}
