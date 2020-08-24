package huh.enterprise.alpha.component.medicine;

import huh.enterprise.alpha.component.medicine.model.Medicine;
import huh.enterprise.alpha.component.medicine.model.MedicineQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine get(MedicineQuery query) {
        return medicineRepository.select(query);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.selectAll();
    }

    @Override
    public List<Medicine> search(MedicineQuery query) {
        return medicineRepository.search(query);
    }

    @Override
    public Medicine create(Medicine record) {
        return medicineRepository.insert(record);
    }

    @Override
    public List<Medicine> createAll(List<Medicine> records) {
        return medicineRepository.insertAll(records);
    }

    @Override
    public Medicine update(Medicine record) {
        return medicineRepository.update(record);
    }

    @Override
    public List<Medicine> updateAll(List<Medicine> records) {
        return medicineRepository.updateAll(records);
    }

    @Override
    public boolean delete(MedicineQuery query) {
        return medicineRepository.delete(query);
    }

    @Override
    public boolean deleteAll(MedicineQuery query) {
        return medicineRepository.deleteAll(query);
    }
}
