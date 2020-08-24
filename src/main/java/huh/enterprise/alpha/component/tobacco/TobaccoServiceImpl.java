package huh.enterprise.alpha.component.tobacco;

import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.tobacco.model.TobaccoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TobaccoServiceImpl implements TobaccoService {
    private final TobaccoRepository tobaccoRepository;

    @Autowired
    public TobaccoServiceImpl(TobaccoRepository tobaccoRepository) {
        this.tobaccoRepository = tobaccoRepository;
    }

    @Override
    public Tobacco get(TobaccoQuery query) {
        return tobaccoRepository.select(query);
    }

    @Override
    public List<Tobacco> getAll() {
        return tobaccoRepository.selectAll();
    }

    @Override
    public List<Tobacco> search(TobaccoQuery query) {
        return tobaccoRepository.search(query);
    }

    @Override
    public Tobacco create(Tobacco record) {
        return tobaccoRepository.insert(record);
    }

    @Override
    public List<Tobacco> createAll(List<Tobacco> records) {
        return tobaccoRepository.insertAll(records);
    }

    @Override
    public Tobacco update(Tobacco record) {
        return tobaccoRepository.update(record);
    }

    @Override
    public List<Tobacco> updateAll(List<Tobacco> records) {
        return tobaccoRepository.updateAll(records);
    }

    @Override
    public boolean delete(TobaccoQuery query) {
        return tobaccoRepository.delete(query);
    }

    @Override
    public boolean deleteAll(TobaccoQuery query) {
        return tobaccoRepository.deleteAll(query);
    }
}
