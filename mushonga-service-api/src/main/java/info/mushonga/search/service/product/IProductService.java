package info.mushonga.search.service.product;

import info.mushonga.search.model.product.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author percym
 */
public interface IProductService  {

    Product save (Product save);

    void delete(Long productId);

    List<Product> saveAll(List<Product> products);


}
