package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.dailyfood.DailyFoodService;
import huh.enterprise.alpha.component.dailyfood.model.DailyFood;
import huh.enterprise.alpha.component.dailyfood.model.DailyFoodQuery;
import huh.enterprise.alpha.component.food.FoodService;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.food.model.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dailyfoods")
public class DailyFoodController implements ApiControllerBase<DailyFoodQuery, DailyFood> {
    private final DailyFoodService dailyFoodService;
    private final FoodService foodService;

    @Autowired
    public DailyFoodController(DailyFoodService dailyFoodService, FoodService foodService) {
        this.dailyFoodService = dailyFoodService;
        this.foodService = foodService;
    }

    @Override
    public DailyFood get(Long id) {
        return dailyFoodService.get(DailyFoodQuery.builder().id(id).build());
    }

    @Override
    public List<DailyFood> getAll() {
        return dailyFoodService.getAll();
    }

    @Override
    public List<DailyFood> search(DailyFoodQuery query) {
        return dailyFoodService.search(query);
    }

    @GetMapping("/categories")
    public List<FoodCategory> getCategories() {
        return Arrays.asList(FoodCategory.values());
    }

    @GetMapping("/foods")
    public List<String> getFoods() {
        return foodService.getAll().stream().map(Food::getName).collect(Collectors.toList());
    }

    @Override
    public DailyFood create(DailyFood record) {
        return dailyFoodService.create(record);
    }

    @Override
    public DailyFood update(Long id, DailyFood record) {
        return dailyFoodService.update(record);
    }

    @Override
    public boolean delete(Long id) {
        return dailyFoodService.delete(DailyFoodQuery.builder().id(id).build());
    }

}
