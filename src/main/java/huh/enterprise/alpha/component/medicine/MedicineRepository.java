package huh.enterprise.alpha.component.medicine;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.medicine.model.MedicineQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.tables.Medicine.MEDICINE;

@Slf4j
@Repository
class MedicineRepository extends AbstractRepository<MedicineQuery, Medicine> {
    private final DSLContext dslContext;
    private final MedicineMapper mapper = new MedicineMapper();

    @Autowired
    MedicineRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Medicine select(MedicineQuery query) {
        return dslContext
                .select()
                .from(MEDICINE)
                .where(MEDICINE.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Medicine> selectAll() {
        return dslContext
                .select()
                .from(MEDICINE)
                .fetch(mapper::map);
    }

    @Override
    protected List<Medicine> search(MedicineQuery query) {
        return super.search(query);
    }

    @Override
    protected Medicine insert(Medicine record) {
        var created = dslContext
                .insertInto(MEDICINE,
                        MEDICINE.NAME,
                        MEDICINE.MANUFACTURER,
                        MEDICINE.DOSAGE,
                        MEDICINE.DESCRIPTION,
                        MEDICINE.LINK)
                .values(record.getName(),
                        record.getManufacturer(),
                        record.getDosage(),
                        record.getDescription(),
                        record.getLink())
                .returning(MEDICINE.asterisk())
                .fetchOne()
                .into(Medicine.class);
        return created;
    }

    @Override
    protected List<Medicine> insertAll(List<Medicine> records) {
        return super.insertAll(records);
    }

    @Override
    protected Medicine update(Medicine record) {
        var updated = dslContext
                .update(MEDICINE)
                .set(MEDICINE.NAME, record.getName())
                .set(MEDICINE.MANUFACTURER, record.getManufacturer())
                .set(MEDICINE.DOSAGE, record.getDosage())
                .set(MEDICINE.DESCRIPTION, record.getDescription())
                .set(MEDICINE.LINK, record.getLink())
                .where(MEDICINE.ID.eq(record.getId()))
                .returning(MEDICINE.asterisk())
                .fetchOne()
                .into(Medicine.class);
        return updated;
    }

    @Override
    protected List<Medicine> updateAll(List<Medicine> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(MedicineQuery query) {
        int rowsAffected = dslContext
                .delete(MEDICINE)
                .where(MEDICINE.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(MedicineQuery query) {
        return super.deleteAll(query);
    }

}
