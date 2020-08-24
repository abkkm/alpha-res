package huh.enterprise.alpha.component.vitamin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Vitamin {
    private Long id;
    private String name;
    private String manufacturer;
    private String dosage;
    private String description;
    private List<VitaminInstruction> instructions;
    private BigDecimal amount;
    private String currency;
    private String link;
    private int count;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
}
