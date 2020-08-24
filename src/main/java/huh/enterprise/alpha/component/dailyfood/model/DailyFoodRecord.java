package huh.enterprise.alpha.component.dailyfood.model;

import huh.enterprise.alpha.component.food.model.FoodCategory;
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
public class DailyFoodRecord {
    private Long id;
    private Long diaryId;
    private FoodCategory category;
    @Builder.Default
    private List<String> foods = new ArrayList<>();
    private int totalCalories;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
}
