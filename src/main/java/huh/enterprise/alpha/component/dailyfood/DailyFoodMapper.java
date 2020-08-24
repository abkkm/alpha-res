package huh.enterprise.alpha.component.dailyfood;

import com.google.common.base.Splitter;
import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.dailyfood.model.DailyFood;
import huh.enterprise.alpha.component.dailyfood.model.DailyFoodRecord;
import huh.enterprise.alpha.component.food.model.FoodCategory;
import org.jooq.Record;

import java.util.Collections;

import static huh.enterprise.alpha.jooq.tables.DailyFood.DAILY_FOOD;

public class DailyFoodMapper implements RecordMapper<DailyFood> {
    @Override
    public DailyFood map(Record r) {
        return DailyFood.builder()
                .id(r.getValue(DAILY_FOOD.ID))
                .diaryId(r.getValue(DAILY_FOOD.DIARY_ID))
                .records(Collections.emptyList())
                .build();
    }

    public DailyFoodRecord mapList(Record r) {
        return DailyFoodRecord.builder()
                .id(r.getValue(DAILY_FOOD.ID))
                .diaryId(r.getValue(DAILY_FOOD.DIARY_ID))
                .category(FoodCategory.valueOf(r.getValue(DAILY_FOOD.CATEGORY)))
                .foods(Splitter.on(",").splitToList(r.getValue(DAILY_FOOD.FOODS)))
                .totalCalories(0)
                .created(r.getValue(DAILY_FOOD.CREATED).toLocalDateTime())
                .createdBy(r.get(DAILY_FOOD.CREATED_BY))
                .updated(r.getValue(DAILY_FOOD.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(DAILY_FOOD.UPDATED_BY))
                .build();
    }
}
