package info.mushonga.search.iservice.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.service.pharmacy.IPharmacyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author percym
 */
public class IPharmacyServiceImpl implements IPharmacyService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return em.merge(pharmacy);
    }


}
