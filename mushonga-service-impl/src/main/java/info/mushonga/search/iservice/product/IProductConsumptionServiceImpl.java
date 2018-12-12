package info.mushonga.search.iservice.product;

import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.product.ProductConsumption;
import info.mushonga.search.repository.product.ProductConsumptionRepository;
import info.mushonga.search.repository.product.ProductRepository;
import info.mushonga.search.service.product.IProductConsumptionService;
import info.mushonga.search.service.product.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The IProductService implementation
 *
 * @author percym
 */
@Transactional
@Service
public class IProductConsumptionServiceImpl implements IProductConsumptionService{

    private final ProductConsumptionRepository productConsumptionRepository;

    public IProductConsumptionServiceImpl(ProductConsumptionRepository productConsumptionRepository) {
        this.productConsumptionRepository = productConsumptionRepository;
    }


    @Override
    public ProductConsumption save(ProductConsumption save) {
        return productConsumptionRepository.save(save);
    }

    @Override
    public void delete(Long productId) {
        productConsumptionRepository.deleteById(productId);
    }

    @Override
    public List<ProductConsumption> saveAll(List<ProductConsumption> products) {
        return productConsumptionRepository.saveAll(products);
    }


}
