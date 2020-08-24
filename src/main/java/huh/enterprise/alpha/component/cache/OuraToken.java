package huh.enterprise.alpha.component.cache;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OuraToken {
    private String token;
    private String username;
}
