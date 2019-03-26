package info.mushonga.search.iservice.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.repository.pharmarcy.PharmacyRepository;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author percym
 */
@Service
@Transactional
public class IPharmacyServiceImpl implements IPharmacyService {

    @PersistenceContext
    private EntityManager em;

    private final PharmacyRepository pharmacyRepository;

    public IPharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public List<Pharmacy> findAll() {
        return  pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy findPharmacyById(Long id) {
        return pharmacyRepository.findPharmacyById(id);
    }

    @Override
    public Pharmacy findPharmacyByRegNumber(String regNumber) {
        return pharmacyRepository.findPharmacyByRegNumber(regNumber);
    }

    @Override
    public List<Pharmacy> findAll(Specification specification) {
        return pharmacyRepository.findAll(specification);
    }

    @Override
    public Optional<Pharmacy> findOne(Specification<Pharmacy> specification) {
        return pharmacyRepository.findOne(specification);
    }

    @Override
    public Pharmacy getOne(Long pharmId) {
        return pharmacyRepository.getOne(pharmId);
    }


}
