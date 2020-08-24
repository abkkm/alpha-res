package huh.enterprise.alpha.component.dailyfood;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.dailyfood.model.DailyFood;
import huh.enterprise.alpha.component.dailyfood.model.DailyFoodQuery;
import huh.enterprise.alpha.component.dailyfood.model.DailyFoodRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.tables.DailyFood.DAILY_FOOD;

@Slf4j
@Repository
public class DailyFoodRepository extends AbstractRepository<DailyFoodQuery, DailyFood> {
    private final DSLContext dslContext;
    private final DailyFoodMapper mapper = new DailyFoodMapper();

    @Autowired
    public DailyFoodRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected DailyFood select(DailyFoodQuery query) {
        List<DailyFoodRecord> records = dslContext
                .select()
                .from(DAILY_FOOD)
                .where(DAILY_FOOD.ID.eq(query.getId()))
                .or(DAILY_FOOD.DIARY_ID.eq(query.getDiaryId()))
                .fetch(mapper::mapList);
        return DailyFood.builder()
                .records(records)
                .build();
    }

    @Override
    protected List<DailyFood> selectAll() {
        return dslContext
                .select()
                .from(DAILY_FOOD)
                .fetch(mapper::map);
    }

    @Override
    protected List<DailyFood> search(DailyFoodQuery query) {
        return super.search(query);
    }

    @Override
    protected DailyFood insert(DailyFood record) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        for (DailyFoodRecord foodRecord : record.getRecords()) {
            var created = dslContext
                    .insertInto(DAILY_FOOD,
                            DAILY_FOOD.DIARY_ID,
                            DAILY_FOOD.CATEGORY,
                            DAILY_FOOD.FOODS,
                            DAILY_FOOD.USERNAME)
                    .values(record.getDiaryId(),
                            foodRecord.getCategory().name(),
                            String.join(",", foodRecord.getFoods()),
                            currentUser)
                    .execute();
        }
        return select(DailyFoodQuery.builder().diaryId(record.getDiaryId()).build());
    }

    @Override
    protected DailyFood update(DailyFood record) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        for (DailyFoodRecord foodRecord : record.getRecords()) {
            var updated = dslContext
                    .update(DAILY_FOOD)
                    .set(DAILY_FOOD.CATEGORY, foodRecord.getCategory().name())
                    .set(DAILY_FOOD.FOODS, String.join(",", foodRecord.getFoods()))
                    .where(DAILY_FOOD.ID.eq(record.getId()))
                    .and(DAILY_FOOD.USERNAME.eq(currentUser))
                    .execute();
        }
        return select(DailyFoodQuery.builder().diaryId(record.getDiaryId()).build());
    }

    @Override
    protected boolean delete(DailyFoodQuery query) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        int rowsAffected = dslContext
                .delete(DAILY_FOOD)
                .where(DAILY_FOOD.ID.eq(query.getId()))
                .and(DAILY_FOOD.USERNAME.eq(currentUser))
                .execute();
        return rowsAffected == 1;
    }

}
