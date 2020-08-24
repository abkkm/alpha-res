package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.bug.BugService;
import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.bug.model.BugQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController implements ApiControllerBase<BugQuery, Bug> {
    private final BugService bugService;

    @Autowired
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @Override
    public Bug get(Long id) {
        return bugService.get(BugQuery.builder().id(id).build());
    }

    @Override
    public List<Bug> getAll() {
        return bugService.getAll();
    }

    @Override
    public List<Bug> search(BugQuery query) {
        return bugService.search(query);
    }

    @Override
    public Bug create(Bug record) {
        return bugService.create(record);
    }

    @Override
    public List<Bug> createAll(List<Bug> records) {
        return bugService.createAll(records);
    }

    @Override
    public Bug update(Long id, Bug record) {
        return bugService.update(record);
    }

    @Override
    public List<Bug> updateAll(Long id, List<Bug> records) {
        return bugService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return bugService.delete(BugQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(BugQuery query) {
        return bugService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
