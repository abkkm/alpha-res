package huh.enterprise.alpha.component.food;

import com.google.common.base.Splitter;
import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.food.model.FoodCategory;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.Tables.FOOD;

public class FoodMapper implements RecordMapper<Food> {
    @Override
    public Food map(Record r) {
        return Food.builder()
                .id(r.getValue(FOOD.ID))
                .name(r.getValue(FOOD.NAME))
                .description(r.getValue(FOOD.DESCRIPTION))
                .ingredients(Splitter.on(",").splitToList(r.getValue(FOOD.INGREDIENTS)))
                .category(FoodCategory.valueOf(r.getValue(FOOD.CATEGORY)))
                .allergy(Food.FoodAllergy.valueOf(r.getValue(FOOD.ALLERGY)))
                .created(r.getValue(FOOD.CREATED).toLocalDateTime())
                .createdBy(r.getValue(FOOD.CREATED_BY))
                .updated(r.getValue(FOOD.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(FOOD.UPDATED_BY))
                .build();
    }
}
