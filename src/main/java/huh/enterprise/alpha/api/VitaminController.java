package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.vitamin.VitaminService;
import huh.enterprise.alpha.component.vitamin.model.Vitamin;
import huh.enterprise.alpha.component.vitamin.model.VitaminQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vitamins")
public class VitaminController implements ApiControllerBase<VitaminQuery, Vitamin> {
    private final VitaminService vitaminService;

    @Autowired
    public VitaminController(VitaminService vitaminService) {
        this.vitaminService = vitaminService;
    }

    @Override
    public Vitamin get(Long id) {
        return vitaminService.get(VitaminQuery.builder().id(id).build());
    }

    @Override
    public List<Vitamin> getAll() {
        return vitaminService.getAll();
    }

    @Override
    public List<Vitamin> search(VitaminQuery query) {
        return vitaminService.search(query);
    }

    @Override
    public Vitamin create(Vitamin record) {
        return vitaminService.create(record);
    }

    @Override
    public List<Vitamin> createAll(List<Vitamin> records) {
        return vitaminService.createAll(records);
    }

    @Override
    public Vitamin update(Long id, Vitamin record) {
        return vitaminService.update(record);
    }

    @Override
    public List<Vitamin> updateAll(Long id, List<Vitamin> records) {
        return vitaminService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return vitaminService.delete(VitaminQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(VitaminQuery query) {
        return vitaminService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
