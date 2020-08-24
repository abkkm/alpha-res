package huh.enterprise.alpha.component.nootropic.model;

import huh.enterprise.alpha.common.Caffeineable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Nootropic implements Caffeineable {
    private Long id;
    private String name;
    private String manufacturer;
    private int caffeine;
    private String ingredients;
    private String link;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
}
