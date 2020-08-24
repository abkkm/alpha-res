package huh.enterprise.alpha.api;

import huh.enterprise.alpha.api.common.ApiControllerBase;
import huh.enterprise.alpha.component.medicine.MedicineService;
import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.medicine.model.MedicineQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController implements ApiControllerBase<MedicineQuery, Medicine> {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public Medicine get(Long id) {
        return medicineService.get(MedicineQuery.builder().id(id).build());
    }

    @Override
    public List<Medicine> getAll() {
        return medicineService.getAll();
    }

    @Override
    public List<Medicine> search(MedicineQuery query) {
        return medicineService.search(query);
    }

    @Override
    public Medicine create(Medicine record) {
        return medicineService.create(record);
    }

    @Override
    public List<Medicine> createAll(List<Medicine> records) {
        return medicineService.createAll(records);
    }

    @Override
    public Medicine update(Long id, Medicine record) {
        return medicineService.update(record);
    }

    @Override
    public List<Medicine> updateAll(Long id, List<Medicine> records) {
        return medicineService.updateAll(records);
    }

    @Override
    public boolean delete(Long id) {
        return medicineService.delete(MedicineQuery.builder().id(id).build());
    }

    @Override
    public boolean deleteAllByQuery(MedicineQuery query) {
        return medicineService.deleteAll(query);
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
