package huh.enterprise.alpha.component.user;

import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.user.model.UserAuthority;
import huh.enterprise.alpha.component.user.model.UserRequest;
import org.jooq.Record;

import java.util.Collections;
import java.util.Optional;

import static huh.enterprise.alpha.jooq.Tables.AUTHORITIES;
import static huh.enterprise.alpha.jooq.Tables.USERS;

public class UserMapper implements RecordMapper<User> {
    @Override
    public User map(Record r) {
        return User.builder()
                .username(r.getValue(USERS.USERNAME))
                .password(r.getValue(USERS.PASSWORD))
                .enabled(r.getValue(USERS.ENABLED))
                .firstName(r.getValue(USERS.FIRST_NAME))
                .lastName(r.getValue(USERS.LAST_NAME))
                .authorities(Collections.singletonList(UserAuthority.builder().authority(r.getValue(AUTHORITIES.AUTHORITY)).build()))
                .gender(Optional.ofNullable(r.getValue(USERS.GENDER)).map(User.Gender::valueOf).orElse(null))
                .weight(r.getValue(USERS.WEIGHT))
                .height(r.getValue(USERS.HEIGHT))
                .created(r.getValue(USERS.CREATED).toLocalDateTime())
                .createdBy(r.getValue(USERS.CREATED_BY))
                .updated(r.getValue(USERS.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(USERS.UPDATED_BY))
                .build();
    }

    public User map(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .build();
    }
}
