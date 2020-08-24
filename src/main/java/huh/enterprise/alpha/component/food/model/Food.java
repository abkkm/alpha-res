package huh.enterprise.alpha.component.food.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Food {
    private Long id;
    private String name;
    private String description;
    private List<String> ingredients;
    private FoodCategory category;
    private FoodAllergy allergy;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;

    public enum FoodAllergy {
        GLUTEN,
        GLUTEN_FREE,
        LACTOSE,
        LACTOSE_FREE,
        VEGAN,
        VEGETARIAN,
    }
}
