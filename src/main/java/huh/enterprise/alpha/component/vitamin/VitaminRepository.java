package huh.enterprise.alpha.component.vitamin;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import huh.enterprise.alpha.component.vitamin.model.VitaminQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.tables.Vitamin.VITAMIN;

@Slf4j
@Repository
class VitaminRepository extends AbstractRepository<VitaminQuery, Vitamin> {
    private final DSLContext dslContext;
    private final VitaminMapper mapper = new VitaminMapper();

    @Autowired
    VitaminRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Vitamin select(VitaminQuery query) {
        return dslContext
                .select()
                .from(VITAMIN)
                .where(VITAMIN.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Vitamin> selectAll() {
        var vitamins = dslContext
                .select()
                .from(VITAMIN)
                .fetch(mapper::map);
        return vitamins;
    }

    @Override
    protected List<Vitamin> search(VitaminQuery query) {
        return super.search(query);
    }

    @Override
    protected Vitamin insert(Vitamin record) {
        var created = dslContext
                .insertInto(VITAMIN,
                        VITAMIN.NAME,
                        VITAMIN.MANUFACTURER,
                        VITAMIN.DOSAGE,
                        VITAMIN.DESCRIPTION,
                        VITAMIN.AMOUNT,
                        VITAMIN.AMOUNT_CY,
                        VITAMIN.LINK)
                .values(record.getName(),
                        record.getManufacturer(),
                        record.getDosage(),
                        record.getDescription(),
                        record.getAmount(),
                        record.getCurrency(),
                        record.getLink())
                .returning(VITAMIN.asterisk())
                .fetchOne()
                .into(Vitamin.class);
        return created;
    }

    @Override
    protected List<Vitamin> insertAll(List<Vitamin> records) {
        return super.insertAll(records);
    }

    @Override
    protected Vitamin update(Vitamin record) {
        var updated = dslContext
                .update(VITAMIN)
                .set(VITAMIN.NAME, record.getName())
                .set(VITAMIN.MANUFACTURER, record.getManufacturer())
                .set(VITAMIN.DOSAGE, record.getDosage())
                .set(VITAMIN.DESCRIPTION, record.getDescription())
                .set(VITAMIN.AMOUNT, record.getAmount())
                .set(VITAMIN.AMOUNT_CY, record.getCurrency())
                .set(VITAMIN.LINK, record.getLink())
                .where(VITAMIN.ID.eq(record.getId()))
                .returning(VITAMIN.asterisk())
                .fetchOne()
                .into(Vitamin.class);
        return updated;
    }

    @Override
    protected List<Vitamin> updateAll(List<Vitamin> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(VitaminQuery query) {
        int rowsAffected = dslContext
                .delete(VITAMIN)
                .where(VITAMIN.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(VitaminQuery query) {
        return super.deleteAll(query);
    }

}
