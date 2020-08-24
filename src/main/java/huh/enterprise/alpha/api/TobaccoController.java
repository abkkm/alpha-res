package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.tobacco.TobaccoService;
import huh.enterprise.alpha.component.tobacco.model.Tobacco;
import huh.enterprise.alpha.component.tobacco.model.TobaccoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tobaccos")
public class TobaccoController implements ApiControllerBase<TobaccoQuery, Tobacco> {
    private final TobaccoService tobaccoService;

    @Autowired
    public TobaccoController(TobaccoService tobaccoService) {
        this.tobaccoService = tobaccoService;
    }

    @Override
    public Tobacco get(Long id) {
        return tobaccoService.get(TobaccoQuery.builder().id(id).build());
    }

    @Override
    public List<Tobacco> getAll() {
        return tobaccoService.getAll();
    }

    @Override
    public List<Tobacco> search(TobaccoQuery query) {
        return tobaccoService.search(query);
    }

    @Override
    public Tobacco create(Tobacco record) {
        return tobaccoService.create(record);
    }

    @Override
    public List<Tobacco> createAll(List<Tobacco> records) {
        return tobaccoService.createAll(records);
    }

    @Override
    public Tobacco update(Long id, Tobacco record) {
        return tobaccoService.update(record);
    }

    @Override
    public List<Tobacco> updateAll(Long id, List<Tobacco> records) {
        return tobaccoService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return tobaccoService.delete(TobaccoQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(TobaccoQuery query) {
        return tobaccoService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
