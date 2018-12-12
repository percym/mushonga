package info.mushonga.search.repository.product;

import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.product.ProductConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author percym
 */
@Repository
public interface ProductConsumptionRepository extends JpaRepository<ProductConsumption, Long> , JpaSpecificationExecutor<ProductConsumption> {

}
