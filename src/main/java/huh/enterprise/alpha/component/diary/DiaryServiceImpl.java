package huh.enterprise.alpha.component.diary;

import huh.enterprise.alpha.common.exception.RecordAlreadyExist;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.diary.model.DiaryQuery;
import huh.enterprise.alpha.component.user.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override
    public Diary get(DiaryQuery query) {
        return diaryRepository.select(query);
    }

    @Override
    public Diary getDetails(DiaryQuery query) {
        Diary diary = diaryRepository.select(query);
        return diaryRepository.selectDetails(diary);
    }

    @Override
    public Diary getForDay(DiaryQuery query) {
        return diaryRepository.selectDay(query).get();
    }

    @Override
    public List<Diary> getForUser(DiaryQuery query) {
        return diaryRepository.search(query);
    }

    @Override
    public Optional<Diary> getLatestForUser(DiaryQuery query) {
        return diaryRepository.selectLatestForUser(query);
    }

    @Override
    public List<Diary> getAll() {
        return diaryRepository.selectAll();
    }

    @Override
    public List<Diary> search(DiaryQuery query) {
        return diaryRepository.search(query);
    }

    @Transactional
    @Override
    public Diary create(Diary record) {
        return isNull(record.getCreated()) ? createForToday(record) : createForDate(record);
    }

    @Transactional
    @Override
    public Diary insertOrUpdate(Diary diary) {
        var existing = diaryRepository.selectDay(DiaryQuery.builder()
                .username(diary.getUser().getUsername())
                .created(diary.getCreated())
                .build());
        if (existing.isPresent()) {
            return diaryRepository.update(existing.get()
                    .toBuilder()
                    .totalSleepHours(diary.getTotalSleepHours())
                    .build());
        }
        return diaryRepository.insert(diary);
    }

    private Diary createForDate(Diary record) {
        if (record.getCreated().isAfter(LocalDateTime.now())) {
            throw new RecordAlreadyExist("Diary cannot be created for future " + record.getCreated());
        }
        String username = Optional.ofNullable(record.getUser()).map(User::getUsername).orElse(null);
        var exist = diaryRepository.selectDay(DiaryQuery.builder().username(username).created(record.getCreated()).build());
        if (exist.isEmpty()) {
            throw new RecordAlreadyExist("Diary already exist for " + record.getCreated());
        }
        return diaryRepository.insert(record);
    }

    private Diary createForToday(Diary record) {
        Diary diaryToday = diaryRepository.selectToday();
        if (nonNull(diaryToday)) {
            throw new RecordAlreadyExist("Diary already exist for today");
        }
        return diaryRepository.insert(record);
    }

    @Override
    public List<Diary> createAll(List<Diary> records) {
        return diaryRepository.insertAll(records);
    }

    @Override
    public Diary update(Diary record) {
        return diaryRepository.update(record);
    }

    @Override
    public List<Diary> updateAll(List<Diary> records) {
        return diaryRepository.updateAll(records);
    }

    @Override
    public boolean delete(DiaryQuery query) {
        return diaryRepository.delete(query);
    }

    @Override
    public boolean deleteAll(DiaryQuery query) {
        return diaryRepository.deleteAll(query);
    }

}
