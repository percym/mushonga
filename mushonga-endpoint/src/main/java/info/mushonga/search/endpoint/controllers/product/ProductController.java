package info.mushonga.search.endpoint.controllers.product;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.service.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The Product controller
 * @author percym
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class ProductController {
    private static String ENTITY_NAME = "product";

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * PUT  /product: update product
     *
     * @return the ResponseEntity with status 201 (Updated) and with the updated product
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the product URI syntax is incorrect
     */
    @PutMapping("/product")
    @Timed
    public ResponseEntity<Product> putPharmacy(@Valid @RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to update product : {}", "");

        if (product.getId()== null){
            throw new BadRequestAlertException("Invalid product id", ENTITY_NAME, "  product id null ");
        }

        if (product.getId()<= 0){
            throw new BadRequestAlertException("Invalid product id", ENTITY_NAME, "  product id is 0 ");
        }

        Product savedProduct = productService.save(product);

        return ResponseEntity.created(new URI("/product"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(product.getId())))
                .body(savedProduct);
    }

}
