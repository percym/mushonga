package info.mushonga.search.service.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;

import java.util.List;

/**
 * @author percym
 */
public interface IPharmacyService {

    Pharmacy save(Pharmacy pharmacy);

    List<Pharmacy> findAll();

    Pharmacy findPharmacyById(Long id );

    Pharmacy findPharmacyByRegNumber(String regNumber );


}
