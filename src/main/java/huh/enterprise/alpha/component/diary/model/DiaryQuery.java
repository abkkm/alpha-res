package huh.enterprise.alpha.component.diary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class DiaryQuery {
    private Long id;
    private LocalDateTime created;
    private String username;
}
