package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.food.FoodService;
import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.food.model.FoodQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController implements ApiControllerBase<FoodQuery, Food> {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public Food get(Long id) {
        return foodService.get(FoodQuery.builder().id(id).build());
    }

    @Override
    public List<Food> getAll() {
        return foodService.getAll();
    }

    @Override
    public List<Food> search(FoodQuery query) {
        return foodService.search(query);
    }

    @Override
    public Food create(Food record) {
        return foodService.create(record);
    }

    @Override
    public List<Food> createAll(List<Food> records) {
        return foodService.createAll(records);
    }

    @Override
    public Food update(Long id, Food record) {
        return foodService.update(record);
    }

    @Override
    public List<Food> updateAll(Long id, List<Food> records) {
        return foodService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return foodService.delete(FoodQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(FoodQuery query) {
        return foodService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
