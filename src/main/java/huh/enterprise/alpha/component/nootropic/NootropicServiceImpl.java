package huh.enterprise.alpha.component.nootropic;

import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.nootropic.model.NootropicQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NootropicServiceImpl implements NootropicService {
    private final NootropicRepository nootropicRepository;

    @Autowired
    public NootropicServiceImpl(NootropicRepository nootropicRepository) {
        this.nootropicRepository = nootropicRepository;
    }

    @Override
    public Nootropic get(NootropicQuery query) {
        return nootropicRepository.select(query);
    }

    @Override
    public List<Nootropic> getAll() {
        return nootropicRepository.selectAll();
    }

    @Override
    public List<Nootropic> search(NootropicQuery query) {
        return nootropicRepository.search(query);
    }

    @Override
    public Nootropic create(Nootropic record) {
        return nootropicRepository.insert(record);
    }

    @Override
    public List<Nootropic> createAll(List<Nootropic> records) {
        return nootropicRepository.insertAll(records);
    }

    @Override
    public Nootropic update(Nootropic record) {
        return nootropicRepository.update(record);
    }

    @Override
    public List<Nootropic> updateAll(List<Nootropic> records) {
        return nootropicRepository.updateAll(records);
    }

    @Override
    public boolean delete(NootropicQuery query) {
        return nootropicRepository.delete(query);
    }

    @Override
    public boolean deleteAll(NootropicQuery query) {
        return nootropicRepository.deleteAll(query);
    }
}
