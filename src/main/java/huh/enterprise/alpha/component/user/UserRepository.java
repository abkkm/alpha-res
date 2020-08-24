package huh.enterprise.alpha.component.user;

import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.user.model.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.AUTHORITIES;
import static huh.enterprise.alpha.jooq.Tables.USERS;

@Slf4j
@Repository
class UserRepository extends AbstractRepository<UserQuery, User> {
    private final DSLContext dslContext;
    private final UserMapper mapper = new UserMapper();

    @Autowired
    UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected User select(UserQuery query) {
        return dslContext
                .select()
                .from(USERS)
                .join(AUTHORITIES).on(AUTHORITIES.USERNAME.eq(USERS.USERNAME))
                .where(USERS.USERNAME.eq(query.getUsername()))
                .fetchOptional(mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException(query.getUsername()));
    }

    @Override
    protected List<User> selectAll() {
        return dslContext
                .select()
                .from(USERS)
                .join(AUTHORITIES).on(AUTHORITIES.USERNAME.eq(USERS.USERNAME))
                .fetch(mapper::map);
    }

    @Override
    protected List<User> search(UserQuery query) {
        return super.search(query);
    }

    @Override
    protected User insert(User record) {
        dslContext
                .insertInto(USERS,
                        USERS.USERNAME,
                        USERS.PASSWORD,
                        USERS.FIRST_NAME,
                        USERS.LAST_NAME,
                        USERS.GENDER,
                        USERS.ENABLED)
                .values(record.getUsername(),
                        record.getPassword(),
                        record.getFirstName(),
                        record.getLastName(),
                        record.getGender().name(),
                        true)
                .returning(USERS.asterisk())
                .execute();

        dslContext
                .insertInto(AUTHORITIES,
                        AUTHORITIES.USERNAME,
                        AUTHORITIES.AUTHORITY)
                .values(record.getUsername(),
                        "USER")
                .returning(AUTHORITIES.asterisk())
                .execute();
        return select(UserQuery.builder().username(record.getUsername()).build());
    }

    @Override
    protected List<User> insertAll(List<User> records) {
        return super.insertAll(records);
    }

    @Override
    protected User update(User record) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var updated = dslContext
                .update(USERS)
                .set(USERS.FIRST_NAME, record.getFirstName())
                .set(USERS.LAST_NAME, record.getLastName())
                .set(USERS.GENDER, record.getGender().name())
                .set(USERS.HEIGHT, record.getHeight())
                .set(USERS.WEIGHT, record.getWeight())
                .where(USERS.USERNAME.eq(currentUser))
                .execute();
        return select(UserQuery.builder().username(currentUser).build());
    }

    @Override
    protected List<User> updateAll(List<User> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(UserQuery query) {
        return super.delete(query);
    }

    @Override
    protected boolean deleteAll(UserQuery query) {
        return super.deleteAll(query);
    }

}
