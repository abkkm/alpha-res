package huh.enterprise.alpha.component.bug;

import huh.enterprise.alpha.component.bug.model.Bug;
import huh.enterprise.alpha.component.bug.model.BugQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {
    private final BugRepository bugRepository;

    @Autowired
    public BugServiceImpl(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug get(BugQuery query) {
        return bugRepository.select(query);
    }

    @Override
    public List<Bug> getAll() {
        return bugRepository.selectAll();
    }

    @Override
    public List<Bug> search(BugQuery query) {
        return bugRepository.search(query);
    }

    @Override
    public Bug create(Bug record) {
        return bugRepository.insert(record);
    }

    @Override
    public List<Bug> createAll(List<Bug> records) {
        return bugRepository.insertAll(records);
    }

    @Override
    public Bug update(Bug record) {
        return bugRepository.update(record);
    }

    @Override
    public List<Bug> updateAll(List<Bug> records) {
        return bugRepository.updateAll(records);
    }

    @Override
    public boolean delete(BugQuery query) {
        return bugRepository.delete(query);
    }

    @Override
    public boolean deleteAll(BugQuery query) {
        return bugRepository.deleteAll(query);
    }
}
