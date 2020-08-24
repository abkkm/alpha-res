package huh.enterprise.alpha.component.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Medicine {
    private Long id;
    private String name;
    private String manufacturer;
    private String dosage;
    private String description;
    private String link;
    private List<SuggestedUse> suggestedUses;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
}
