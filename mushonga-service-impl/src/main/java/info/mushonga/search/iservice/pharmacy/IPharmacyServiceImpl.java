package info.mushonga.search.iservice.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.repository.pharmarcy.PharmacyRepository;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        return em.merge(pharmacy);
    }

    @Override
    public List<Pharmacy> findAll() {
        return  pharmacyRepository.findAll();
    }


}
