package huh.enterprise.alpha.component.dailyfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class DailyFood {
    private Long id;
    private Long diaryId;
    @Builder.Default
    private List<DailyFoodRecord> records = new ArrayList<>();
    private LocalDateTime created;
}
