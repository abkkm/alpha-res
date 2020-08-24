package huh.enterprise.alpha.component.oura.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OuraQuery {

    private LocalDate start;
    private String accessToken;

}
