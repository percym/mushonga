package info.mushonga.search.endpoint.controllers.search;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.iservice.func.ProductFunctions;
import info.mushonga.search.iservice.hibersearch.HibernateSearchService;
import info.mushonga.search.iservice.specifications.product.PharmacyByProductId;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacySearchDTO;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.product.ProductConsumption;
import info.mushonga.search.model.search.Search;
import info.mushonga.search.model.search.SearchResult;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import info.mushonga.search.service.product.IProductService;
import info.mushonga.search.service.search.ISearchService;
import info.mushonga.search.service.search.ISearchTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final IPharmacyService pharmacyService;

    private final ISearchTransactionService searchTransactionService;

    public SearchController(IProductService productService, ISearchService searchService, IPharmacyService pharmacyService, ISearchTransactionService searchTransactionService) {
        this.productService = productService;
        this.searchService = searchService;
        this.pharmacyService = pharmacyService;
        this.searchTransactionService = searchTransactionService;
    }

    @PostMapping("/search/{searchTerm}/{userId}")
    @Timed
    public ResponseEntity<List<PharmacySearchDTO>> searchProductUserId(@PathVariable String searchTerm, @PathVariable Long userId) throws URISyntaxException {
        log.debug("REST request to search for products : {}", searchTerm);
        Search search = new Search();
        List<PharmacySearchDTO> searchResults = new ArrayList<>();

        List<Product> products = service.fuzzySearch(searchTerm);
        if (!products.isEmpty()) {
            products.forEach(product -> productFunctions.addMetrics(product));
        }

        search.setActive(true);
        search.setSearchTerm(searchTerm);
        search.setUserId(userId);
        for (Product prod : products) {
            ProductConsumption product = new ProductConsumption();
            product.setActive(prod.getActive());
            product.setGenericCode(prod.getGenericCode());
            product.setGenericName(prod.getGenericName());
            product.setItemBalance(prod.getItemBalance());
            product.setItemPrice(prod.getItemPrice());
            product.setTotalSearchedTimes(prod.getTotalSearchedTimes());
            if (!(search.getProducts() == null)) {
                search.getProducts().add(product);
            }
        }

        searchService.save(search);


//        searchTransactionService.save()


        productService.saveAll(products);

        for (Product product: products)
        {
            searchResults.add(getSearchResult(product));
        }


        return ResponseEntity.created(new URI("/search/"+searchTerm +"/"+userId))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(products.size())))
                .body(searchResults);
    }


    @PostMapping("/search/{searchTerm}")
    @Timed
    public ResponseEntity<List<PharmacySearchDTO>> searchProductUserId(@PathVariable String searchTerm) throws URISyntaxException {
        log.debug("REST request to search for products : {}", searchTerm);
        Search search = new Search();
        List<PharmacySearchDTO> searchResults = new ArrayList<>();

        List<Product> products = service.fuzzySearch(searchTerm);
        if (!products.isEmpty()) {
            products.forEach(product -> productFunctions.addMetrics(product));
        }

        search.setActive(true);
        search.setSearchTerm(searchTerm);
        search.setUserId(0L);
        for (Product prod : products) {
            ProductConsumption product = new ProductConsumption();
            product.setActive(prod.getActive());
            product.setGenericCode(prod.getGenericCode());
            product.setGenericName(prod.getGenericName());
            product.setItemBalance(prod.getItemBalance());
            product.setItemPrice(prod.getItemPrice());
            product.setTotalSearchedTimes(prod.getTotalSearchedTimes());
            if (!(search.getProducts() == null)) {
                search.getProducts().add(product);
            }
        }

        searchService.save(search);


//        searchTransactionService.save()


        productService.saveAll(products);

        for (Product product: products)
        {
            searchResults.add(getSearchResult(product));
        }


        return ResponseEntity.created(new URI("/product"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(products.size())))
                .body(searchResults);
    }

    private PharmacySearchDTO getSearchResult(Product product) {
        PharmacyByProductId pharmacyByProductId = new PharmacyByProductId(product.getId());
        Pharmacy pharmacyExists = new Pharmacy();
        PharmacySearchDTO searchResult = new PharmacySearchDTO();
        Optional<Pharmacy> pharmacy = pharmacyService.findOne(pharmacyByProductId);
        if (pharmacy.isPresent()) {
            pharmacyExists = pharmacy.get();
            Pharmacy searchPharmacy = new Pharmacy();
            searchPharmacy.setRegNumber(pharmacyExists.getRegNumber());
            searchPharmacy.setTradingName(pharmacyExists.getTradingName());
            searchPharmacy.setRegisteredName(pharmacyExists.getRegisteredName());
            searchPharmacy.setAddress(pharmacyExists.getAddress());
//            pharmacyExists.getProducts().clear();
//            pharmacyExists.getSystemUsers().clear();
//            pharmacyExists.getProducts().add(product);
            searchResult.setPharmacy(searchPharmacy);
            searchResult.setProduct(product);
            return searchResult;
        }else {
            return null;
        }

    }
}
