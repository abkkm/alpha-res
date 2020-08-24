package huh.enterprise.alpha.component.tobacco;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.tobacco.model.TobaccoQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.TOBACCO;

@Slf4j
@Repository
public class TobaccoRepository extends AbstractRepository<TobaccoQuery, Tobacco> {
    private final DSLContext dslContext;
    private final TobaccoMapper mapper = new TobaccoMapper();

    @Autowired
    public TobaccoRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Tobacco select(TobaccoQuery query) {
        return dslContext
                .select()
                .from(TOBACCO)
                .where(TOBACCO.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Tobacco> selectAll() {
        return dslContext
                .select()
                .from(TOBACCO)
                .fetch(mapper::map);
    }

    @Override
    protected List<Tobacco> search(TobaccoQuery query) {
        return super.search(query);
    }

    @Override
    protected Tobacco insert(Tobacco record) {
        var created = dslContext
                .insertInto(TOBACCO,
                        TOBACCO.NAME,
                        TOBACCO.MANUFACTURER,
                        TOBACCO.NICOTINE)
                .values(record.getName(),
                        record.getManufacturer(),
                        record.getNicotine())
                .returning(TOBACCO.asterisk())
                .fetchOne()
                .into(Tobacco.class);
        return created;
    }

    @Override
    protected List<Tobacco> insertAll(List<Tobacco> records) {
        return super.insertAll(records);
    }

    @Override
    protected Tobacco update(Tobacco record) {
        return super.update(record);
    }

    @Override
    protected List<Tobacco> updateAll(List<Tobacco> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(TobaccoQuery query) {
        int rowsAffected = dslContext
                .deleteFrom(TOBACCO)
                .where(TOBACCO.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(TobaccoQuery query) {
        return super.deleteAll(query);
    }

}
