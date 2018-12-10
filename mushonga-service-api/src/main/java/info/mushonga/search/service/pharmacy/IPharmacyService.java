package info.mushonga.search.service.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @author percym
 */
public interface IPharmacyService {

    Pharmacy save(Pharmacy pharmacy);

    List<Pharmacy> findAll();

    Pharmacy findPharmacyById(Long id );

    Pharmacy findPharmacyByRegNumber(String regNumber );

    List<Pharmacy> findAll(Specification specification);

    Optional<Pharmacy> findOne(Specification<Pharmacy> specification);



}
