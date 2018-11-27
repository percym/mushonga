package systems.health263.dashboard.endpoint.controllers.drugnsundry;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.model.drugnsundry.DrugNSundry;
import systems.health263.dashboard.service.drugnsundry.IDrugNSundryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
}
