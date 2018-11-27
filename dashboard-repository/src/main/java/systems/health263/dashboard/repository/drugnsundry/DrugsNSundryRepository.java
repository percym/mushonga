package systems.health263.dashboard.repository.drugnsundry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import systems.health263.dashboard.model.drugnsundry.DrugNSundry;

import java.util.stream.Stream;

/**
 * @author percym
 */
public interface DrugsNSundryRepository extends JpaRepository<DrugNSundry,Long>,JpaSpecificationExecutor<DrugNSundry> {
    Stream<DrugNSundry> getAllByGenericCodeNotNull();
}
