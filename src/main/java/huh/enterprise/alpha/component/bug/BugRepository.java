package huh.enterprise.alpha.component.bug;

import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.bug.model.BugQuery;
import huh.enterprise.alpha.component.common.AbstractRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.BUG;

@Slf4j
@Repository
class BugRepository extends AbstractRepository<BugQuery, Bug> {
    private final DSLContext dslContext;
    private final BugMapper mapper = new BugMapper();

    @Autowired
    BugRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Bug select(BugQuery query) {
        return dslContext
                .select()
                .from(BUG)
                .where(BUG.ID.eq(query.getId()))
                .fetchOptional(mapper::map)
                .orElse(null);
    }

    @Override
    protected List<Bug> selectAll() {
        return dslContext
                .select()
                .from(BUG)
                .fetch(mapper::map);
    }

    @Override
    protected List<Bug> search(BugQuery query) {
        return super.search(query);
    }

    @Override
    protected Bug insert(Bug record) {
        var created = dslContext
                .insertInto(BUG,
                        BUG.NAME,
                        BUG.DESCRIPTION,
                        BUG.SEVERITY,
                        BUG.REASON)
                .values(record.getName(),
                        record.getDescription(),
                        record.getSeverity(),
                        record.getReason())
                .returning(BUG.asterisk())
                .fetchOne()
                .into(Bug.class);
        return created;
    }

    @Override
    protected List<Bug> insertAll(List<Bug> records) {
        return super.insertAll(records);
    }

    @Override
    protected Bug update(Bug record) {
        var updated = dslContext
                .update(BUG)
                .set(BUG.NAME, record.getName())
                .set(BUG.DESCRIPTION, record.getDescription())
                .set(BUG.SEVERITY, record.getSeverity())
                .set(BUG.REASON, record.getReason())
                .where(BUG.ID.eq(record.getId()))
                .returning(BUG.asterisk())
                .fetchOne()
                .into(Bug.class);

        return updated;
    }

    @Override
    protected List<Bug> updateAll(List<Bug> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(BugQuery query) {
        int rowsAffected = dslContext
                .delete(BUG)
                .where(BUG.ID.eq(query.getId()))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(BugQuery query) {
        return super.deleteAll(query);
    }

}
