package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.diary.DiaryService;
import huh.enterprise.alpha.component.diary.model.Activity;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.diary.model.DiaryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController implements ApiControllerBase<DiaryQuery, Diary> {
    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Override
    public Diary get(Long id) {
        return diaryService.get(DiaryQuery.builder().id(id).build());
    }

    @GetMapping("/{id}/details")
    public Diary getDetails(@PathVariable Long id) {
        return diaryService.getDetails(DiaryQuery.builder().id(id).build());
    }

    @Override
    public List<Diary> getAll() {
        return diaryService.getAll();
    }

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        return Arrays.asList(Activity.values());
    }

    @Override
    public List<Diary> search(DiaryQuery query) {
        return diaryService.search(query);
    }

    @Override
    public Diary create(Diary record) {
        return diaryService.create(record);
    }

    @Override
    public List<Diary> createAll(List<Diary> records) {
        return diaryService.createAll(records);
    }

    @Override
    public Diary update(Long id, Diary record) {
        return diaryService.update(record);
    }

    @Override
    public List<Diary> updateAll(Long id, List<Diary> records) {
        return diaryService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return diaryService.delete(DiaryQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(DiaryQuery query) {
        return diaryService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
