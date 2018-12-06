package info.mushonga.search.repository.pharmarcy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author percym
 */
@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Long>, JpaSpecificationExecutor<Pharmacy>{

    Pharmacy findPharmacyById(Long id );

    Pharmacy findPharmacyByRegNumber(String regNumber);


}
