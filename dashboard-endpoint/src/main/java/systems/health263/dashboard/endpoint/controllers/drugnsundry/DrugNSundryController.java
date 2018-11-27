package systems.health263.dashboard.endpoint.controllers.drugnsundry;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.model.clinical.TariffCode;
import systems.health263.dashboard.model.drugnsundry.DrugNSundry;
import systems.health263.dashboard.service.drugnsundry.IDrugNSundryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author percym
 */
/*
 * Rest controller for managing Drugs and Sundries
 *
 * @author Percy Mugadza
 *
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class DrugNSundryController {
    private static final String ENTITY_NAME = "drugNsundry";

    private final IDrugNSundryService drugNSundryService;

    public DrugNSundryController(IDrugNSundryService drugNSundryService) {
        this.drugNSundryService = drugNSundryService;
    }

    /**
     * GET  /drugnsundry : get all the drugs and sundries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of drugs and sundries in body
     */
    @GetMapping("/drugnsundry")
    @Timed
    public ResponseEntity<List<DrugNSundry>> getAllDrugsNSundry() {
        log.debug("REST request to get a list drugs and sundries");
        List<DrugNSundry> drugNSundries = drugNSundryService.findAll();
        return new ResponseEntity<>(drugNSundries, null, HttpStatus.OK);
    }

    /**
     * GET  /drugnsundry/{id} : get a  drug or sundry.
     *
     * @return the ResponseEntity with status 200 (OK) and a drug or sundry
     */
    @GetMapping("/drugnsundry/{id}")
    @Timed
    public ResponseEntity<DrugNSundry> getAllDrugsNSundryById(@PathVariable Long id) {
        log.debug("REST request to get a sundry");
        DrugNSundry drugNSundries = drugNSundryService.findOne(id);
        return new ResponseEntity<>(drugNSundries, null, HttpStatus.OK);
    }

    /* GET  /drugnsundry_stream : get all the drug n sundry as a stream.
     *
     * @return the ResponseEntity with status 200 (OK) and the stream of drug n sundry
     */
    @Transactional
    @GetMapping("/drugnsundry_stream")
    @Timed
    public List<DrugNSundry> getAllTariffCodeStream() {
        log.debug("REST request to get a stream of TariffCodes");
        try(Stream<DrugNSundry> stream = drugNSundryService.getAllByGenericCodeNotNull()){
            return   stream.collect(Collectors.toList());
        }
    }

    /**
     * POST  /drugnsundry : Create a new drug or sundry.
     *
     * @param drugNSundry the drug or sundry to create
     * @return the ResponseEntity with status 201 (Created) and with body the new drug or sundry
     * , or with status 400 (Bad Request) if the drug or sundry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/drugnsundry")
    @Timed
    public ResponseEntity<DrugNSundry> createDrugNSundry(@Valid @RequestBody DrugNSundry drugNSundry) throws URISyntaxException {
        log.debug("REST request to save ITariff : {}", drugNSundry);
        if (drugNSundry.getId() != null) {
            throw new BadRequestAlertException("A new drugNSundry cannot already have an ID", ENTITY_NAME, "id exists");
        }
        DrugNSundry result = drugNSundryService.save(drugNSundry);
        return ResponseEntity.created(new URI("/api/drugnsundry/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
