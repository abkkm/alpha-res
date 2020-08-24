package huh.enterprise.alpha.component.diary;

import huh.enterprise.alpha.common.exception.RecordNotCreated;
import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.common.AbstractRepository;
import huh.enterprise.alpha.component.common.DateConvertable;
import huh.enterprise.alpha.component.diary.model.Diary;
import huh.enterprise.alpha.component.diary.model.DiaryQuery;
import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.food.FoodMapper;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.medicine.MedicineMapper;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.vitamin.VitaminMapper;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static huh.enterprise.alpha.jooq.Tables.DIARY;
import static huh.enterprise.alpha.jooq.Tables.FOOD;
import static huh.enterprise.alpha.jooq.Tables.MEDICINE;
import static huh.enterprise.alpha.jooq.Tables.VITAMIN;
import static java.util.stream.Collectors.toList;
import static org.jooq.impl.DSL.max;

@Slf4j
@Repository
class DiaryRepository extends AbstractRepository<DiaryQuery, Diary> implements DateConvertable {
    private final DSLContext dslContext;
    private final DiaryMapper diaryMapper = new DiaryMapper();
    private final MedicineMapper medicineMapper = new MedicineMapper();
    private final VitaminMapper vitaminMapper = new VitaminMapper();
    private final FoodMapper foodMapper = new FoodMapper();

    @Autowired
    DiaryRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    protected Diary select(DiaryQuery query) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return dslContext
                .select()
                .from(DIARY)
                .where(DIARY.ID.eq(query.getId()))
                .and(DIARY.USERNAME.eq(currentUser))
                .fetchOptional(diaryMapper::map)
                .orElse(null);
    }

    protected Optional<Diary> selectLatestForUser(DiaryQuery query) {
        return dslContext
                .select()
                .from(DIARY)
                .where(DIARY.USERNAME.eq(query.getUsername()))
                .orderBy(DIARY.CREATED)
                .limit(1)
                .fetchOptional(diaryMapper::map);
    }

    protected Diary selectDetails(Diary diary) {
        List<String> medicineNames = diary.getMedicines().stream().map(Medicine::getName).collect(toList());
        List<Medicine> medicines = dslContext
                .select()
                .from(MEDICINE)
                .where(MEDICINE.NAME.in(medicineNames))
                .fetch(medicineMapper::map);

        List<String> vitaminNames = diary.getVitamins().stream().map(Vitamin::getName).collect(toList());
        List<Vitamin> vitamins = dslContext
                .select()
                .from(VITAMIN)
                .where(VITAMIN.NAME.in(vitaminNames))
                .fetch(vitaminMapper::map);

        List<String> foodNames = diary.getFoods().stream().map(Food::getName).collect(toList());
        List<Food> foods = dslContext
                .select()
                .from(FOOD)
                .where(FOOD.NAME.in(foodNames))
                .fetch(foodMapper::map);

        return diary.toBuilder()
                .medicines(medicines)
                .vitamins(vitamins)
                .foods(foods)
                .build();
    }

    @Override
    protected List<Diary> selectAll() {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var diaries = dslContext
                .select()
                .from(DIARY)
                .where(DIARY.USERNAME.eq(currentUser))
                .orderBy(DIARY.CREATED.desc())
                .fetch(diaryMapper::map);
        return diaries;
    }

    protected Optional<Diary> selectDay(DiaryQuery query) {
        var currentUser = Optional.ofNullable(query).map(DiaryQuery::getUsername).orElseGet(() -> SecurityContextHolder.getContext().getAuthentication().getName());
        var start = getTimestampStartOfDay(query.getCreated());
        var end = getTimestampEndOfDay(query.getCreated());
        return dslContext
                .select()
                .from(DIARY)
                .where(DIARY.CREATED.between(start, end))
                .and(DIARY.USERNAME.eq(currentUser))
                .fetchOptional(diaryMapper::map);
    }

    protected Diary selectToday() {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var start = Timestamp.valueOf(ZonedDateTime.now().toLocalDate().atStartOfDay());
        var end = Timestamp.valueOf(LocalDateTime.now());
        return dslContext
                .select()
                .from(DIARY)
                .where(DIARY.CREATED.between(start, end))
                .and(DIARY.USERNAME.eq(currentUser))
                .fetchOptional(diaryMapper::map)
                .orElse(null);
    }

    @Override
    protected List<Diary> search(DiaryQuery query) {
        var diaries = dslContext
                .select()
                .from(DIARY)
                .where(DIARY.USERNAME.eq(query.getUsername()))
                .orderBy(DIARY.CREATED.desc())
                .fetch(diaryMapper::map);
        return diaries;
    }

    @Override
    protected Diary insert(Diary record) {
        var currentUser = Optional.ofNullable(record.getUser()).map(User::getUsername).orElseGet(() -> SecurityContextHolder.getContext().getAuthentication().getName());
        var createdDate = parseTimestampDefaultNow(record.getCreated());
        var created = dslContext
                .insertInto(DIARY,
                        DIARY.SHARPNESS,
                        DIARY.MOOD,
                        DIARY.ENERGY,
                        DIARY.PRODUCTIVITY,
                        DIARY.TOTAL_SLEEP_HOURS,
                        DIARY.ACTIVITIES,
                        DIARY.MEDICINES,
                        DIARY.VITAMINS,
                        DIARY.NOOTROPICS,
                        DIARY.TOBACCOS,
                        DIARY.DRINKS,
                        DIARY.BUGS,
                        DIARY.COMMENT,
                        DIARY.USERNAME,
                        DIARY.CREATED)
                .values(record.getSharpness(),
                        record.getMood(),
                        record.getEnergy(),
                        record.getProductivity(),
                        record.getTotalSleepHours(),
                        record.getActivities().stream().map(Enum::name).collect(Collectors.joining(",")),
                        record.getMedicines().stream().map(Medicine::getName).collect(Collectors.joining(",")),
                        record.getVitamins().stream().map(Vitamin::getName).collect(Collectors.joining(",")),
                        record.getNootropics().stream().map(Nootropic::getName).collect(Collectors.joining(",")),
                        record.getTobaccos().stream().map(Tobacco::getName).collect(Collectors.joining(",")),
                        record.getDrinks().stream().map(Drink::getName).collect(Collectors.joining(",")),
                        record.getBugs().stream().map(Bug::getName).collect(Collectors.joining(",")),
                        record.getComment(),
                        currentUser,
                        createdDate)
                .returning(DIARY.asterisk())
                .execute();

        return selectDay(DiaryQuery.builder().username(currentUser).created(createdDate.toLocalDateTime()).build())
                .orElseThrow(RecordNotCreated::new);
    }

    @Override
    protected List<Diary> insertAll(List<Diary> records) {
        return super.insertAll(records);
    }

    @Override
    protected Diary update(Diary record) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var updated = dslContext
                .update(DIARY)
                .set(DIARY.SHARPNESS, record.getSharpness())
                .set(DIARY.PRODUCTIVITY, record.getProductivity())
                .set(DIARY.MOOD, record.getMood())
                .set(DIARY.ENERGY, record.getEnergy())
                .set(DIARY.COMMENT, record.getComment())
                .set(DIARY.TOTAL_SLEEP_HOURS, record.getTotalSleepHours())
                .set(DIARY.ACTIVITIES, record.getActivities().stream().map(Enum::name).collect(Collectors.joining(",")))
                .set(DIARY.MEDICINES, record.getMedicineTags().stream().collect(Collectors.joining(",")))
                .set(DIARY.VITAMINS, record.getVitaminTags().stream().collect(Collectors.joining(",")))
                .set(DIARY.NOOTROPICS, record.getNootropicTags().stream().collect(Collectors.joining(",")))
                .set(DIARY.TOBACCOS, record.getTobaccoTags().stream().collect(Collectors.joining(",")))
                .set(DIARY.DRINKS, record.getDrinkTags().stream().collect(Collectors.joining(",")))
                .set(DIARY.BUGS, record.getBugTags().stream().collect(Collectors.joining(",")))
                .where(DIARY.ID.eq(record.getId()))
                .and(DIARY.USERNAME.eq(currentUser))
                .execute();
        return select(DiaryQuery.builder().id(record.getId()).build());
    }

    @Override
    protected List<Diary> updateAll(List<Diary> records) {
        return super.updateAll(records);
    }

    @Override
    protected boolean delete(DiaryQuery query) {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        int rowsAffected = dslContext
                .delete(DIARY)
                .where(DIARY.ID.eq(query.getId()))
                .and(DIARY.USERNAME.eq(currentUser))
                .execute();
        return rowsAffected == 1;
    }

    @Override
    protected boolean deleteAll(DiaryQuery query) {
        return super.deleteAll(query);
    }

}
