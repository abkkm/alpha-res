package huh.enterprise.alpha.component.diary;

import huh.enterprise.alpha.component.common.ServiceBase;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.diary.model.DiaryQuery;

import java.util.List;
import java.util.Optional;

public interface DiaryService extends ServiceBase<DiaryQuery, Diary> {
   Diary getDetails(DiaryQuery query);
   Diary getForDay(DiaryQuery query);
   List<Diary> getForUser(DiaryQuery query);
   Optional<Diary> getLatestForUser(DiaryQuery query);

   Diary insertOrUpdate(Diary diary);
}
