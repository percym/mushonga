package info.mushonga.search.repository.account;

import info.mushonga.search.model.account.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author percym
 */
@Repository
public interface PaymentsRepository extends JpaRepository<Payment,Long> ,JpaSpecificationExecutor<Payment> {
}
