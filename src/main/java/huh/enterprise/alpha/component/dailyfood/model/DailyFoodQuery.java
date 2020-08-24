package huh.enterprise.alpha.component.dailyfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class DailyFoodQuery {
    private Long id;
    private Long diaryId;
}
