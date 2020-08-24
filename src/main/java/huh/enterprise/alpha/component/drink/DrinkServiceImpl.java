package huh.enterprise.alpha.component.drink;

import huh.enterprise.alpha.component.drink.model.Drink;
import huh.enterprise.alpha.component.drink.model.DrinkQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Drink get(DrinkQuery query) {
        return drinkRepository.select(query);
    }

    @Override
    public List<Drink> getAll() {
        return drinkRepository.selectAll();
    }

    @Override
    public List<Drink> search(DrinkQuery query) {
        return drinkRepository.search(query);
    }

    @Override
    public Drink create(Drink record) {
        return drinkRepository.insert(record);
    }

    @Override
    public List<Drink> createAll(List<Drink> records) {
        return drinkRepository.insertAll(records);
    }

    @Override
    public Drink update(Drink record) {
        return drinkRepository.update(record);
    }

    @Override
    public List<Drink> updateAll(List<Drink> records) {
        return drinkRepository.updateAll(records);
    }

    @Override
    public boolean delete(DrinkQuery query) {
        return drinkRepository.delete(query);
    }

    @Override
    public boolean deleteAll(DrinkQuery query) {
        return drinkRepository.deleteAll(query);
    }
}
