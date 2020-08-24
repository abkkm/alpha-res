package huh.enterprise.alpha.component.nootropic;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.Tables.NOOTROPIC;

public class NootropicMapper implements RecordMapper<Nootropic> {
    @Override
    public Nootropic map(Record r) {
        return Nootropic.builder()
                .id(r.getValue(NOOTROPIC.ID))
                .name(r.getValue(NOOTROPIC.NAME))
                .manufacturer(r.getValue(NOOTROPIC.MANUFACTURER))
                .ingredients(r.getValue(NOOTROPIC.INGREDIENTS))
                .link(r.getValue(NOOTROPIC.LINK))
                .created(r.getValue(NOOTROPIC.CREATED).toLocalDateTime())
                .createdBy(r.getValue(NOOTROPIC.CREATED_BY))
                .updated(r.getValue(NOOTROPIC.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(NOOTROPIC.UPDATED_BY))
                .build();
    }
}
