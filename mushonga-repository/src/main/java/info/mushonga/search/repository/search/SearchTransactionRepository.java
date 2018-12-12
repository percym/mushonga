package info.mushonga.search.repository.search;

import info.mushonga.search.model.search.SearchTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author percym
 */
@Repository
public interface SearchTransactionRepository extends JpaRepository<SearchTransaction, Long>,JpaSpecificationExecutor<SearchTransaction> {
}
