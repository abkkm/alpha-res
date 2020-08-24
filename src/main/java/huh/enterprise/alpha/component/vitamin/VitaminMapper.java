package huh.enterprise.alpha.component.vitamin;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.tables.Vitamin.VITAMIN;

public class VitaminMapper implements RecordMapper<Vitamin> {
    @Override
    public Vitamin map(Record r) {
        return Vitamin.builder()
                .id(r.getValue(VITAMIN.ID))
                .name(r.getValue(VITAMIN.NAME))
                .manufacturer(r.getValue(VITAMIN.MANUFACTURER))
                .dosage(r.getValue(VITAMIN.DOSAGE))
                .description(r.getValue(VITAMIN.DESCRIPTION))
                .amount(r.getValue(VITAMIN.AMOUNT))
                .currency(r.getValue(VITAMIN.AMOUNT_CY))
                .link(r.getValue(VITAMIN.LINK))
                .created(r.getValue(VITAMIN.CREATED).toLocalDateTime())
                .createdBy(r.getValue(VITAMIN.CREATED_BY))
                .updated(r.getValue(VITAMIN.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(VITAMIN.UPDATED_BY))
                .build();
    }
}
