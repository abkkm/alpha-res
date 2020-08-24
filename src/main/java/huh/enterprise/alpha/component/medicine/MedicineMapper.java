package huh.enterprise.alpha.component.medicine;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import org.jooq.Record;

import static huh.enterprise.alpha.jooq.tables.Medicine.MEDICINE;

public class MedicineMapper implements RecordMapper<Medicine> {
    @Override
    public Medicine map(Record r) {
        return Medicine.builder()
                .id(r.getValue(MEDICINE.ID))
                .name(r.getValue(MEDICINE.NAME))
                .manufacturer(r.getValue(MEDICINE.MANUFACTURER))
                .dosage(r.getValue(MEDICINE.DOSAGE))
                .description(r.getValue(MEDICINE.DESCRIPTION))
                .link(r.getValue(MEDICINE.LINK))
                .created(r.getValue(MEDICINE.CREATED).toLocalDateTime())
                .createdBy(r.getValue(MEDICINE.CREATED_BY))
                .updated(r.getValue(MEDICINE.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(MEDICINE.UPDATED_BY))
                .build();
    }
}
