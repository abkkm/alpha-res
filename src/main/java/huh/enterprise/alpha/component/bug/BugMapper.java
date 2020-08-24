package huh.enterprise.alpha.component.bug;

import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.common.RecordMapper;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.Tables.BUG;

public class BugMapper implements RecordMapper<Bug> {
    @Override
    public Bug map(Record r) {
        return Bug.builder()
                .id(r.getValue(BUG.ID))
                .name(r.getValue(BUG.NAME))
                .description(r.getValue(BUG.DESCRIPTION))
                .severity(r.getValue(BUG.SEVERITY))
                .reason(r.getValue(BUG.REASON))
                .created(r.getValue(BUG.CREATED).toLocalDateTime())
                .createdBy(r.getValue(BUG.CREATED_BY))
                .updated(r.getValue(BUG.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(BUG.UPDATED_BY))
                .build();
    }
}
