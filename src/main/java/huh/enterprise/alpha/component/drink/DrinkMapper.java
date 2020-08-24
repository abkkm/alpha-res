package huh.enterprise.alpha.component.drink;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.drink.model.Drink;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.Tables.DRINK;

public class DrinkMapper implements RecordMapper<Drink> {
    @Override
    public Drink map(Record r) {
        return Drink.builder()
                .id(r.getValue(DRINK.ID))
                .name(r.getValue(DRINK.NAME))
                .caffeine(r.getValue(DRINK.CAFFEINE))
                .ingredients(r.getValue(DRINK.INGREDIENTS))
                .manufacturer(r.getValue(DRINK.MANUFACTURER))
                .size(r.getValue(DRINK.SIZE))
                .created(r.getValue(DRINK.CREATED).toLocalDateTime())
                .createdBy(r.getValue(DRINK.CREATED_BY))
                .updated(r.getValue(DRINK.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(DRINK.UPDATED_BY))
                .build();
    }
}
