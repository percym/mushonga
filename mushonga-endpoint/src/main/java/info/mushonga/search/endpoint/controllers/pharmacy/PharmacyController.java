package info.mushonga.search.endpoint.controllers.pharmacy;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.iservice.specifications.PharmacyByUserId;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacyDTO;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.repository.pharmarcy.PharmacyRepository;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import info.mushonga.search.service.user.ISystemUserService;
import info.mushonga.search.utility.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The class to manage the pharmacies
 *
 * @author percym
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class PharmacyController {

    private static final String ENTITY_NAME = "pharmacy";

    private final IPharmacyService pharmacyService;
    private final ISystemUserService systemUserService;
    public PharmacyController(IPharmacyService pharmacyService, ISystemUserService systemUserService) {
        this.pharmacyService = pharmacyService;
        this.systemUserService = systemUserService;
    }

    /**
     * POST  /pharmacy : get all pharmacies.
     *
     * @return the ResponseEntity with status 201 (Created) and with all pharmacies
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the pharmacies URI syntax is incorrect
     */
    @GetMapping("/pharmacy")
    @Timed
    public ResponseEntity<List<Pharmacy>> getPharmacies() throws URISyntaxException {
        log.debug("REST request to get pharmacy : {}", "");
        List<Pharmacy> pharmacies = pharmacyService.findAll();

        return ResponseEntity.created(new URI("/api/pharmacy/" + pharmacies.size()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(pharmacies.size())))
                .body(pharmacies);
    }

    /**
     * GET  /pharmacy/{id} : get all pharmacy by id
     *
     * @return the ResponseEntity with status 201 (Created) and with the pharmacy
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the pharmacy URI syntax is incorrect
     */
    @GetMapping("/pharmacy/{id}")
    @Timed
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to get pharmacy : {}", "");
        Pharmacy pharmacy = pharmacyService.findPharmacyById(id);

        return ResponseEntity.created(new URI("/api/pharmacy/" + pharmacy.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(pharmacy.getId())))
                .body(pharmacy);
    }



    /**
     * GET  /pharmacy_user/{id} : get all pharmacy by userId
     *
     * @return the ResponseEntity with status 201 (Created) and with the pharmacy
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the pharmacy URI syntax is incorrect
     */
    @GetMapping("/pharmacy_user/{id}")
    @Timed
    public ResponseEntity<List<Pharmacy>> getPharmacyByUserId(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to get pharmacy : {}", "");
        SystemUser systemUser = systemUserService.getSystemUserById(id);
        PharmacyByUserId pharmacyByUserId = new PharmacyByUserId(systemUser);
        List<Pharmacy> pharmacies = pharmacyService.findAll(pharmacyByUserId);
        return ResponseEntity.created(new URI("/pharmacy_user/" + pharmacies.size()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(pharmacies.size())))
                .body(pharmacies);
    }
    /**
     * POST  /pharmacy : Create a new pharmacy.
     *
     * @param pharmacyDTO the pharmacy to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pharmacy
     * , or with status 400 (Bad Request) if the pharmacy has already an ID
     * @throws URISyntaxException if the pharmacy URI syntax is incorrect
     */
    @PostMapping("/pharmacy")
    @Timed
    public ResponseEntity<Pharmacy> createPharmacy(@Valid @RequestBody PharmacyDTO pharmacyDTO) throws URISyntaxException {
        log.debug("REST request to save pharmacy : {}", pharmacyDTO);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setTradingName(pharmacyDTO.getTradingName());
        pharmacy.setRegisteredName(pharmacyDTO.getRegisteredName());
        pharmacy.setRegNumber(pharmacyDTO.getRegNumber());
        pharmacy.setAddress(pharmacyDTO.getAddress());
        if(pharmacyDTO.getSystemUser().getId()> 0) {

            if (pharmacyDTO.getSystemUser().getUserType() != UserType.PHARMACY){
                throw new BadRequestAlertException("Invalid UserType", ENTITY_NAME, " user can not add pharmacy");
            }

            if (pharmacyDTO.getSystemUser().getPharmacy()!= null){
                throw new BadRequestAlertException("User already has a pharmacy", ENTITY_NAME, "pharmacy exists");
            }

            pharmacy.getSystemUsers().add(pharmacyDTO.getSystemUser());
            pharmacy.setActive(pharmacyDTO.getActive());
        }else {
            throw new BadRequestAlertException("A user needs an id", ENTITY_NAME, " user id error");
        }
        pharmacy = pharmacyService.save(pharmacy);
        SystemUser pharmacyUser = pharmacyDTO.getSystemUser();
        pharmacyUser.setPharmacy(pharmacy);
        systemUserService.saveSystemUser(pharmacyUser);

        return ResponseEntity.created(new URI("/api/pharmacy/" + pharmacy.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, pharmacy.getId().toString()))
                .body(pharmacy);
    }


    /**
     * PUT  /pharmacy: update pharmarcy
     *
     * @return the ResponseEntity with status 201 (Updated) and with the pharmacy
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the pharmacy URI syntax is incorrect
     */
    @PutMapping("/pharmacy")
    @Timed
    public ResponseEntity<Pharmacy> putPharmacy(@Valid @RequestBody Pharmacy pharmacy) throws URISyntaxException {
        log.debug("REST request to update pharmacy : {}", "");

        if (pharmacy.getId()== null){
            throw new BadRequestAlertException("Invalid pharmacy id", ENTITY_NAME, "  pharmacy id null ");
        }

        if (pharmacy.getId()<= 0){
            throw new BadRequestAlertException("Invalid pharmacy id", ENTITY_NAME, "  pharmacy id is 0 ");
        }

        Pharmacy savedPharmacy = pharmacyService.save(pharmacy);

        return ResponseEntity.created(new URI("/pharmacy/"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(pharmacy.getId())))
                .body(savedPharmacy);
    }

    /**
     * POST /pharmacy_product/{pharmId}:  add product to a pharmacy
     *
     * @return the ResponseEntity with status 201 (Updated) and with the pharmacy with updated product
     * , or with status 400 (Bad Request)
     * @throws URISyntaxException if the pharmacy URI syntax is incorrect
     */
    @PostMapping("/pharmacy_product/{pharmId}")
    @Timed
    public ResponseEntity<Pharmacy> putPharmacyProduct(@Valid @RequestBody Product product, @PathVariable Long pharmId) throws URISyntaxException {
        log.debug("REST request to update pharmacy  with a product: {}", "");

        Pharmacy pharmacy = pharmacyService.findPharmacyById(pharmId);

        if (pharmacy.getId()== null){
            throw new BadRequestAlertException("Invalid pharmacy id", ENTITY_NAME, "  pharmacy id null ");
        }

        if (pharmacy.getId()<= 0){
            throw new BadRequestAlertException("Invalid pharmacy id", ENTITY_NAME, "  pharmacy id is 0 ");
        }

        if (product.getId()!= null){
            throw new BadRequestAlertException("Invalid product id", ENTITY_NAME, "  product id null ");
        }

        pharmacy.getProducts().add(product);

        Pharmacy savedPharmacy = pharmacyService.save(pharmacy);

        return ResponseEntity.created(new URI("/pharmacy_product/"+ pharmId))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(pharmacy.getId())))
                .body(savedPharmacy);
    }





}
