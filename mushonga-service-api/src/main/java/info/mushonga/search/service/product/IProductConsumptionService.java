package info.mushonga.search.service.product;

import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.product.ProductConsumption;

import java.util.List;

/**
 * @author percym
 */
public interface IProductConsumptionService {

    ProductConsumption save(ProductConsumption save);

    void delete(Long productId);

    List<ProductConsumption> saveAll(List<ProductConsumption> products);


}
