package huh.enterprise.alpha.component.food;

import huh.enterprise.alpha.component.food.model.Food;
import huh.enterprise.alpha.component.food.model.FoodQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food get(FoodQuery query) {
        return foodRepository.select(query);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.selectAll();
    }

    @Override
    public List<Food> search(FoodQuery query) {
        return foodRepository.search(query);
    }

    @Override
    public Food create(Food record) {
        return foodRepository.insert(record);
    }

    @Override
    public List<Food> createAll(List<Food> records) {
        return foodRepository.insertAll(records);
    }

    @Override
    public Food update(Food record) {
        return foodRepository.update(record);
    }

    @Override
    public List<Food> updateAll(List<Food> records) {
        return foodRepository.updateAll(records);
    }

    @Override
    public boolean delete(FoodQuery query) {
        return foodRepository.delete(query);
    }

    @Override
    public boolean deleteAll(FoodQuery query) {
        return foodRepository.deleteAll(query);
    }
}
