package huh.enterprise.alpha.component.diary;

import com.google.common.base.Splitter;
import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.common.RecordMapper;
import huh.enterprise.alpha.component.diary.model.Activity;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import org.jooq.Record;

import java.util.List;

import static huh.enterprise.alpha.jooq.Tables.DIARY;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.StringUtils.isEmpty;

public class DiaryMapper implements RecordMapper<Diary> {
    @Override
    public Diary map(Record r) {
        List<String> activities = isBlank(r.getValue(DIARY.ACTIVITIES)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.ACTIVITIES));
        List<String> medicines = isBlank(r.getValue(DIARY.MEDICINES)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.MEDICINES));
        List<String> vitamins = isBlank(r.getValue(DIARY.VITAMINS)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.VITAMINS));
        List<String> nootropics = isBlank(r.getValue(DIARY.NOOTROPICS)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.NOOTROPICS));
        List<String> tobaccos = isBlank(r.getValue(DIARY.TOBACCOS)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.TOBACCOS));
        List<String> drinks = isBlank(r.getValue(DIARY.DRINKS)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.DRINKS));
        List<String> bugs = isBlank(r.getValue(DIARY.BUGS)) ? emptyList() : Splitter.on(",").splitToList(r.getValue(DIARY.BUGS));
        return Diary.builder()
                .id(r.getValue(DIARY.ID))
                .sharpness(r.getValue(DIARY.SHARPNESS))
                .mood(r.getValue(DIARY.MOOD))
                .energy(r.getValue(DIARY.ENERGY))
                .productivity(r.getValue(DIARY.PRODUCTIVITY))
                .totalSleepHours(r.getValue(DIARY.TOTAL_SLEEP_HOURS))
                .activities(isEmpty(activities) ? emptyList() : activities.stream().map(Activity::valueOf).collect(toList()))
                .medicines(isEmpty(medicines) ? emptyList() : medicines.stream().map(m -> Medicine.builder().name(m).build()).collect(toList()))
                .vitamins(isEmpty(vitamins) ? emptyList() : vitamins.stream().map(v -> Vitamin.builder().name(v).build()).collect(toList()))
                .nootropics(isEmpty(nootropics) ? emptyList() : nootropics.stream().map(n -> Nootropic.builder().name(n).build()).collect(toList()))
                .tobaccos(isEmpty(tobaccos) ? emptyList() : tobaccos.stream().map(t -> Tobacco.builder().name(t).build()).collect(toList()))
                .drinks(isEmpty(drinks) ? emptyList() : drinks.stream().map(d -> Drink.builder().name(d).build()).collect(toList()))
                .bugs(isEmpty(bugs) ? emptyList() : bugs.stream().map(b -> Bug.builder().name(b).build()).collect(toList()))
                .comment(r.getValue(DIARY.COMMENT))
                .created(r.getValue(DIARY.CREATED).toLocalDateTime())
                .createdBy(r.getValue(DIARY.CREATED_BY))
                .updated(r.getValue(DIARY.UPDATED).toLocalDateTime())
                .updatedBy(r.getValue(DIARY.UPDATED_BY))
                .build();
    }
}
