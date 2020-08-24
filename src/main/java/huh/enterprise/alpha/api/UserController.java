package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.user.UserMapper;
import huh.enterprise.alpha.component.user.UserService;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.user.model.UserQuery;
import huh.enterprise.alpha.component.user.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements ApiControllerBase<UserQuery, User> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper = new UserMapper();

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.get(UserQuery.builder().username(username).build());
    }

    @Override
    public User get(Long id) {
        return userService.get(UserQuery.builder().id(id).build());
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public List<User> search(UserQuery query) {
        return userService.search(query);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserRequest record) {
        String newPassword = passwordEncoder.encode(record.getPassword());
        User user = mapper.map(record);
        return userService.create(user.toBuilder()
                .password(newPassword)
                .build());
    }

    @PutMapping("/username/{username}")
    public User update(@PathVariable String username, @RequestBody User record) {
        return userService.update(record);
    }

    @Override
    public User update(Long id, User record) {
        return userService.update(record);
    }

    @Override
    public List<User> updateAll(Long id, List<User> records) {
        return userService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return userService.delete(UserQuery.builder().id(id).build());
    }

}
