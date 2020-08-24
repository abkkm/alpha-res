package huh.enterprise.alpha.component.tobacco;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.Tables.TOBACCO;

public class TobaccoMapper implements RecordMapper<Tobacco> {
    @Override
    public Tobacco map(Record r) {
        return Tobacco.builder()
                .id(r.getValue(TOBACCO.ID))
                .name(r.getValue(TOBACCO.NAME))
                .manufacturer(r.getValue(TOBACCO.MANUFACTURER))
                .nicotine(r.getValue(TOBACCO.NICOTINE))
                .size(r.getValue(TOBACCO.SIZE))
                .created(r.getValue(TOBACCO.CREATED).toLocalDateTime())
                .createdBy(r.getValue(TOBACCO.CREATED_BY))
                .updated(r.getValue(TOBACCO.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(TOBACCO.UPDATED_BY))
                .build();
    }
}
