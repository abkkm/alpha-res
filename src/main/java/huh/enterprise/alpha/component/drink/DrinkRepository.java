package huh.enterprise.alpha.component.drink;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.drink.model.DrinkQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.DRINK;

@Slf4j
@Repository
public class DrinkRepository extends AbstractRepository<DrinkQuery, Drink> {
    private final DSLContext dslContext;
    private final DrinkMapper mapper = new DrinkMapper();

    @Autowired
    public DrinkRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Drink select(DrinkQuery query) {
        return dslContext
                .select()
                .from(DRINK)
                .where(DRINK.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Drink> selectAll() {
        return dslContext
                .select()
                .from(DRINK)
                .fetch(mapper::map);
    }

    @Override
    protected List<Drink> search(DrinkQuery query) {
        return super.search(query);
    }

    @Override
    protected Drink insert(Drink record) {
        var created = dslContext
                .insertInto(DRINK,
                        DRINK.NAME,
                        DRINK.SIZE,
                        DRINK.MANUFACTURER,
                        DRINK.CAFFEINE,
                        DRINK.INGREDIENTS)
                .values(record.getName(),
                        record.getSize(),
                        record.getManufacturer(),
                        record.getCaffeine(),
                        record.getIngredients())
                .returning(DRINK.asterisk())
                .fetchOne()
                .into(Drink.class);
        return created;
    }

    @Override
    protected List<Drink> insertAll(List<Drink> records) {
        return super.insertAll(records);
    }

    @Override
    protected Drink update(Drink record) {
        var updated = dslContext
                .update(DRINK)
                .set(DRINK.NAME, record.getName())
                .set(DRINK.SIZE, record.getSize())
                .set(DRINK.MANUFACTURER, record.getManufacturer())
                .set(DRINK.CAFFEINE, record.getCaffeine())
                .set(DRINK.INGREDIENTS, record.getIngredients())
                .where(DRINK.ID.eq(record.getId()))
                .returning(DRINK.asterisk())
                .fetchOne()
                .into(Drink.class);
        return updated;
    }

    @Override
    protected List<Drink> updateAll(List<Drink> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(DrinkQuery query) {
        int rowsAffected = dslContext
                .delete(DRINK)
                .where(DRINK.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(DrinkQuery query) {
        return super.deleteAll(query);
    }

}
