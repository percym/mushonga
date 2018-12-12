package info.mushonga.search.repository.search;

import info.mushonga.search.model.search.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author percym
 */
public interface SearchRepository extends JpaRepository<Search, Long >, JpaSpecificationExecutor<Search>{

}
