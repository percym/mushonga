package info.mushonga.search.endpoint.controllers;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.iservice.func.ProductFunctions;
import info.mushonga.search.iservice.hibersearch.HibernateSearchService;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.repository.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Search endpoint management
 *
 * @author percym
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class SearchController {

    private static String ENTITY_NAME = "search";

    private ProductFunctions productFunctions = new ProductFunctions();

    @Autowired
    HibernateSearchService service;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/search/{searchTerm}")
    @Timed
    public ResponseEntity<List<Product>> putProduct(@PathVariable String  searchTerm) throws URISyntaxException {
        log.debug("REST request to update product : {}", "");

        List<Product> products = service.fuzzySearch(searchTerm);
        if (!products.isEmpty()){
            products.forEach(product -> productFunctions.addMetrics(product));
        }

        productRepository.saveAll(products);



        return ResponseEntity.created(new URI("/product"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(products.size())))
                .body(products);
    }




}
