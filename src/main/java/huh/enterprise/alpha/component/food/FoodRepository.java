package huh.enterprise.alpha.component.food;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.food.model.FoodQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.FOOD;

@Slf4j
@Repository
class FoodRepository extends AbstractRepository<FoodQuery, Food> {
    private final DSLContext dslContext;
    private final FoodMapper mapper = new FoodMapper();

    @Autowired
    FoodRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Food select(FoodQuery query) {
        return dslContext
                .select()
                .from(FOOD)
                .where(FOOD.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Food> selectAll() {
        return dslContext
                .select()
                .from(FOOD)
                .fetch(mapper::map);
    }

    @Override
    protected List<Food> search(FoodQuery query) {
        return super.search(query);
    }

    @Override
    protected Food insert(Food record) {
        var created = dslContext
                .insertInto(FOOD,
                        FOOD.NAME,
                        FOOD.DESCRIPTION,
                        FOOD.INGREDIENTS,
                        FOOD.CATEGORY,
                        FOOD.ALLERGY)
                .values(record.getName(),
                        record.getDescription(),
                        StringUtils.join(record.getIngredients(), ','),
                        record.getCategory().name(),
                        record.getAllergy().name())
                .returning(FOOD.asterisk())
                .fetchOne()
                .into(Food.class);
        return created;
    }

    @Override
    protected List<Food> insertAll(List<Food> records) {
        return super.insertAll(records);
    }

    @Override
    protected Food update(Food record) {
        return super.update(record);
    }

    @Override
    protected List<Food> updateAll(List<Food> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(FoodQuery query) {
        int rowsAffected = dslContext
                .delete(FOOD)
                .where(FOOD.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(FoodQuery query) {
        return super.deleteAll(query);
    }

}
