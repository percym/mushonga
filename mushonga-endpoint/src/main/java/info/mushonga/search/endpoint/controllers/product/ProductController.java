package info.mushonga.search.endpoint.controllers.product;

import com.codahale.metrics.annotation.Timed;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.service.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
     * @return the ResponseEntity with status 200 (Successful   ) and with the updated product
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the product URI syntax is incorrect
     */
    @PutMapping("/product")
    @Timed
    public ResponseEntity<Product> putProduct(@Valid @RequestBody Product product) throws URISyntaxException {
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

    /**
     * DELETE  /product/{id} : delete product by id
     *
     * @return the ResponseEntity with status 200 (Successful)
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the delete URI syntax is incorrect
     */
    @DeleteMapping("/product/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to get delete product : {}", id);
        productService.delete(id);
        return ResponseEntity.status(200).headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /client : get all the Product.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of Product in body
     */
    @GetMapping("/product")
    @Timed
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("REST request to get a page of Product");
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }

}
