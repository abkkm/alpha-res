package huh.enterprise.alpha;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassTest {

    @Test
    public void test() {
        String encoded = new BCryptPasswordEncoder().encode("admin");
        System.out.println(encoded);
    }

}
