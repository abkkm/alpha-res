package huh.enterprise.alpha.component.user;

import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.user.model.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(UserQuery query) {
        return userRepository.select(query);
    }

    @Override
    public List<User> getAll() {
        return userRepository.selectAll();
    }

    @Override
    public List<User> search(UserQuery query) {
        return userRepository.search(query);
    }

    @Override
    public User create(User record) {
        return userRepository.insert(record);
    }

    @Override
    public List<User> createAll(List<User> records) {
        return userRepository.insertAll(records);
    }

    @Override
    public User update(User record) {
        return userRepository.update(record);
    }

    @Override
    public List<User> updateAll(List<User> records) {
        return userRepository.updateAll(records);
    }

    @Override
    public boolean delete(UserQuery query) {
        return userRepository.delete(query);
    }

    @Override
    public boolean deleteAll(UserQuery query) {
        return userRepository.deleteAll(query);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.select(UserQuery.builder().username(username).build());
        return user;
    }
}
