package huh.enterprise.alpha.component.user;

import huh.enterprise.alpha.component.common.ServiceBase;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.user.model.UserQuery;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends ServiceBase<UserQuery, User>, UserDetailsService {
}
