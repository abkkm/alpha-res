package huh.enterprise.alpha.component.dailyfood;

import huh.enterprise.alpha.common.exception.RecordForbidden;
import huh.enterprise.alpha.component.dailyfood.model.DailyFood;
import huh.enterprise.alpha.component.dailyfood.model.DailyFoodQuery;
import huh.enterprise.alpha.component.diary.DiaryService;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.diary.model.DiaryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DailyFoodServiceImpl implements DailyFoodService {
    private final DailyFoodRepository dailyFoodRepository;
    private final DiaryService diaryService;

    @Autowired
    public DailyFoodServiceImpl(DailyFoodRepository dailyFoodRepository, DiaryService diaryService) {
        this.dailyFoodRepository = dailyFoodRepository;
        this.diaryService = diaryService;
    }

    @Override
    public DailyFood get(DailyFoodQuery query) {
        return dailyFoodRepository.select(query);
    }

    @Override
    public List<DailyFood> getAll() {
        return dailyFoodRepository.selectAll();
    }

    @Override
    public List<DailyFood> search(DailyFoodQuery query) {
        return dailyFoodRepository.search(query);
    }

    @Transactional
    @Override
    public DailyFood create(DailyFood record) {
        Diary diary = diaryService.getForDay(DiaryQuery.builder().created(record.getCreated()).build());
        if (isNull(diary)) {
            throw new RecordForbidden(String.format("Diary for %s is missing", record.getCreated()));
        }
        return dailyFoodRepository.insert(record.toBuilder()
                .diaryId(diary.getId())
                .build());
    }

    @Transactional
    @Override
    public DailyFood update(DailyFood record) {
        Diary diary = diaryService.getForDay(DiaryQuery.builder().created(record.getCreated()).build());
        if (isNull(diary)) {
            throw new RecordForbidden(String.format("Diary for %s is missing", record.getCreated()));
        }
        return dailyFoodRepository.update(record.toBuilder()
                .diaryId(diary.getId())
                .build());
    }

    @Transactional
    @Override
    public boolean delete(DailyFoodQuery query) {
        return dailyFoodRepository.delete(query);
    }

}
