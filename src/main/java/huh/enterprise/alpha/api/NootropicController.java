package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.nootropic.NootropicService;
import huh.enterprise.alpha.component.nootropic.model.Nootropic;
import huh.enterprise.alpha.component.nootropic.model.NootropicQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nootropics")
public class NootropicController implements ApiControllerBase<NootropicQuery, Nootropic> {
    private final NootropicService nootropicService;

    @Autowired
    public NootropicController(NootropicService nootropicService) {
        this.nootropicService = nootropicService;
    }

    @Override
    public Nootropic get(Long id) {
        return nootropicService.get(NootropicQuery.builder().id(id).build());
    }

    @Override
    public List<Nootropic> getAll() {
        return nootropicService.getAll();
    }

    @Override
    public List<Nootropic> search(NootropicQuery query) {
        return nootropicService.search(query);
    }

    @Override
    public Nootropic create(Nootropic record) {
        return nootropicService.create(record);
    }

    @Override
    public List<Nootropic> createAll(List<Nootropic> records) {
        return nootropicService.createAll(records);
    }

    @Override
    public Nootropic update(Long id, Nootropic record) {
        return nootropicService.update(record);
    }

    @Override
    public List<Nootropic> updateAll(Long id, List<Nootropic> records) {
        return nootropicService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return nootropicService.delete(NootropicQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(NootropicQuery query) {
        return nootropicService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
