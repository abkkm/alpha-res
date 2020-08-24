package huh.enterprise.alpha.component.relation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Relation {
    private Long diaryId;
    private Long relationId;
    private RelationType relationType;
}
