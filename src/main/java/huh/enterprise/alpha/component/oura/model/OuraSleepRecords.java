package huh.enterprise.alpha.component.oura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class OuraSleepRecords {
    @JsonProperty("sleep")
    private List<OuraSleepRecord> records;
}
