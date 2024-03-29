package info.mushonga.search.iservice.product;

import info.mushonga.search.model.product.Product;
import info.mushonga.search.repository.product.ProductRepository;
import info.mushonga.search.service.product.IProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The IProductService implementation
 * @author percym
 */
@Transactional
@Service
public class IProductServiceImpl  implements IProductService{

    private final ProductRepository productRepository;

    public IProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
