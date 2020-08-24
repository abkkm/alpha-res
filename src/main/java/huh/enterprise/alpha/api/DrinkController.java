package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.drink.DrinkService;
import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.drink.model.DrinkQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController implements ApiControllerBase<DrinkQuery, Drink> {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public Drink get(Long id) {
        return drinkService.get(DrinkQuery.builder().id(id).build());
    }

    @Override
    public List<Drink> getAll() {
        return drinkService.getAll();
    }

    @Override
    public List<Drink> search(DrinkQuery query) {
        return drinkService.search(query);
    }

    @Override
    public Drink create(Drink record) {
        return drinkService.create(record);
    }

    @Override
    public List<Drink> createAll(List<Drink> records) {
        return drinkService.createAll(records);
    }

    @Override
    public Drink update(Long id, Drink record) {
        return drinkService.update(record);
    }

    @Override
    public List<Drink> updateAll(Long id, List<Drink> records) {
        return drinkService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return drinkService.delete(DrinkQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(DrinkQuery query) {
        return drinkService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
