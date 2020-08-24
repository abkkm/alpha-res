package huh.enterprise.alpha.component.vitamin;

import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import huh.enterprise.alpha.component.vitamin.model.VitaminQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitaminServiceImpl implements VitaminService {
    private final VitaminRepository vitaminRepository;

    @Autowired
    public VitaminServiceImpl(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    @Override
    public Vitamin get(VitaminQuery query) {
        return vitaminRepository.select(query);
    }

    @Override
    public List<Vitamin> getAll() {
        return vitaminRepository.selectAll();
    }

    @Override
    public List<Vitamin> search(VitaminQuery query) {
        return vitaminRepository.search(query);
    }

    @Override
    public Vitamin create(Vitamin record) {
        return vitaminRepository.insert(record);
    }

    @Override
    public List<Vitamin> createAll(List<Vitamin> records) {
        return vitaminRepository.insertAll(records);
    }

    @Override
    public Vitamin update(Vitamin record) {
        return vitaminRepository.update(record);
    }

    @Override
    public List<Vitamin> updateAll(List<Vitamin> records) {
        return vitaminRepository.updateAll(records);
    }

    @Override
    public boolean delete(VitaminQuery query) {
        return vitaminRepository.delete(query);
    }

    @Override
    public boolean deleteAll(VitaminQuery query) {
        return vitaminRepository.deleteAll(query);
    }
}
