package huh.enterprise.alpha.component.diary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.symptom.Symptom;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.user.model.User;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Diary {
    private Long id;
    private int sharpness;
    private int mood;
    private int energy;
    private int productivity;
    private BloodPressure bloodPressure;
    private BigDecimal totalSleepHours;
    @Builder.Default
    private List<Activity> activities = new ArrayList<>();
    @Builder.Default
    private List<Medicine> medicines = new ArrayList<>();
    @Builder.Default
    private List<Vitamin> vitamins = new ArrayList<>();
    @Builder.Default
    private List<Nootropic> nootropics = new ArrayList<>();
    @Builder.Default
    private List<Tobacco> tobaccos = new ArrayList<>();
    @Builder.Default
    private List<Drink> drinks = new ArrayList<>();
    @Builder.Default
    private List<Symptom> symptoms = new ArrayList<>();
    @Builder.Default
    private List<Bug> bugs = new ArrayList<>();
    @Builder.Default
    private List<Food> foods = new ArrayList<>();
    private String comment;
    private User user;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
    @Builder.Default
    private List<String> medicineTags = new ArrayList<>();
    @Builder.Default
    private List<String> vitaminTags = new ArrayList<>();
    @Builder.Default
    private List<String> nootropicTags = new ArrayList<>();
    @Builder.Default
    private List<String> tobaccoTags = new ArrayList<>();
    @Builder.Default
    private List<String> drinkTags = new ArrayList<>();
    @Builder.Default
    private List<String> bugTags = new ArrayList<>();

    @JsonProperty("createdDate")
    public LocalDate createdDate() {
        return isNull(created) ? null : created.toLocalDate();
    }

    @JsonProperty("medicineTags")
    public List<String> medicineTags() {
        return medicines.stream().map(Medicine::getName).collect(Collectors.toList());
    }

    @JsonProperty("vitaminTags")
    public List<String> vitaminTags() {
        return vitamins.stream().map(Vitamin::getName).collect(Collectors.toList());
    }

    @JsonProperty("nootropicTags")
    public List<String> nootropicTags() {
        return nootropics.stream().map(Nootropic::getName).collect(Collectors.toList());
    }

    @JsonProperty("tobaccoTags")
    public List<String> tobaccoTags() {
        return tobaccos.stream().map(Tobacco::getName).collect(Collectors.toList());
    }

    @JsonProperty("drinkTags")
    public List<String> drinkTags() {
        return drinks.stream().map(Drink::getName).collect(Collectors.toList());
    }

    @JsonProperty("bugTags")
    public List<String> bugTags() {
        return bugs.stream().map(Bug::getName).collect(Collectors.toList());
    }
}
