package info.mushonga.search.endpoint.controllers.search;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.iservice.func.ProductFunctions;
import info.mushonga.search.iservice.hibersearch.HibernateSearchService;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.product.ProductConsumption;
import info.mushonga.search.model.search.Search;
import info.mushonga.search.service.product.IProductService;
import info.mushonga.search.service.search.ISearchService;
import info.mushonga.search.service.search.ISearchTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    private final IProductService productService;

    private final ISearchService searchService;

    private final ISearchTransactionService searchTransactionService;

    public SearchController(IProductService productService, ISearchService searchService, ISearchTransactionService searchTransactionService) {
        this.productService = productService;
        this.searchService = searchService;
        this.searchTransactionService = searchTransactionService;
    }

    @PostMapping("/search/{searchTerm}/{userId}")
    @Timed
    public ResponseEntity<List<Product>> putProduct(@PathVariable String  searchTerm , @PathVariable Long userId) throws URISyntaxException {
        log.debug("REST request to search for products : {}", searchTerm);
        Search search = new Search();

        List<Product> products = service.fuzzySearch(searchTerm);
        if (!products.isEmpty()){
            products.forEach(product -> productFunctions.addMetrics(product));
        }

        search.setActive(true);
        search.setSearchTerm(searchTerm);
        search.setUserId(userId);
        for (Product prod:products){
            ProductConsumption product = new ProductConsumption();
            product.setActive(prod.getActive());
            product.setGenericCode(prod.getGenericCode());
            product.setGenericName(prod.getGenericName());
            product.setItemBalance(prod.getItemBalance());
            product.setItemPrice(prod.getItemPrice());
            product.setTotalSearchedTimes(prod.getTotalSearchedTimes());
            if(!(search.getProducts()==null)) {
             search.getProducts().add(product);
            }
        }

        searchService.save(search);


//        searchTransactionService.save()


        productService.saveAll(products);



        return ResponseEntity.created(new URI("/product"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(products.size())))
                .body(products);
    }




}
