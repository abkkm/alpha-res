package huh.enterprise.alpha.component.nootropic;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.nootropic.model.NootropicQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.NOOTROPIC;

@Slf4j
@Repository
class NootropicRepository extends AbstractRepository<NootropicQuery, Nootropic> {
    private final DSLContext dslContext;
    private final NootropicMapper mapper = new NootropicMapper();

    @Autowired
    NootropicRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Nootropic select(NootropicQuery query) {
        return dslContext
                .select()
                .from(NOOTROPIC)
                .where(NOOTROPIC.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Nootropic> selectAll() {
        return dslContext
                .select()
                .from(NOOTROPIC)
                .fetch(mapper::map);
    }

    @Override
    protected List<Nootropic> search(NootropicQuery query) {
        return super.search(query);
    }

    @Override
    protected Nootropic insert(Nootropic record) {
        var created = dslContext
                .insertInto(NOOTROPIC,
                        NOOTROPIC.NAME,
                        NOOTROPIC.MANUFACTURER,
                        NOOTROPIC.INGREDIENTS,
                        NOOTROPIC.LINK)
                .values(record.getName(),
                        record.getManufacturer(),
                        record.getIngredients(),
                        record.getLink())
                .returning(NOOTROPIC.asterisk())
                .fetchOne()
                .into(Nootropic.class);
        return created;
    }

    @Override
    protected List<Nootropic> insertAll(List<Nootropic> records) {
        return super.insertAll(records);
    }

    @Override
    protected Nootropic update(Nootropic record) {
        var updated = dslContext
                .update(NOOTROPIC)
                .set(NOOTROPIC.NAME, record.getName())
                .set(NOOTROPIC.LINK, record.getLink())
                .set(NOOTROPIC.MANUFACTURER, record.getManufacturer())
                .set(NOOTROPIC.INGREDIENTS, record.getIngredients())
                .where(NOOTROPIC.ID.eq(record.getId()))
                .returning(NOOTROPIC.asterisk())
                .fetchOne()
                .into(Nootropic.class);
        return updated;
    }

    @Override
    protected List<Nootropic> updateAll(List<Nootropic> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(NootropicQuery query) {
        int rowsAffected = dslContext
                .delete(NOOTROPIC)
                .where(NOOTROPIC.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(NootropicQuery query) {
        return super.deleteAll(query);
    }

}
