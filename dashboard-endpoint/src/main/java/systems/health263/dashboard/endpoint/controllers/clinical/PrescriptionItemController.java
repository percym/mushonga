package systems.health263.dashboard.endpoint.controllers.clinical;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.clinical.PrescriptionItem;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.clinical.PrescriptionItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Rest controller for managing PrescriptionItems
 *
 * @author Munyaradzi Takayindisa
 *
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class PrescriptionItemController {

    private static final String ENTITY_NAME = "prescription_item";

    private final PrescriptionItemService prescriptionItemService;

    public PrescriptionItemController(PrescriptionItemService prescriptionItemService) {
        this.prescriptionItemService = prescriptionItemService;
    }

    /**
     * POST  /prescription_item : Create a new prescriptionItem.
     *
     * @param prescriptionItem the prescriptionItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prescriptionItem, or with status 400 (Bad Request) if the prescriptionItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prescription_item")
    @Timed
    public ResponseEntity<PrescriptionItem> createPrescriptionItem(@Valid @RequestBody PrescriptionItem prescriptionItem) throws URISyntaxException {
        log.debug("REST request to save IPrescriptionItem : {}", prescriptionItem);
        if (prescriptionItem.getId() != null) {
            throw new BadRequestAlertException("A new prescriptionItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrescriptionItem result = prescriptionItemService.save(prescriptionItem);
        return ResponseEntity.created(new URI("/api/prescription_item/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /prescription_item : Updates an existing prescriptionItem.
     *
     * @param prescriptionItem the prescriptionItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prescriptionItem,
     * or with status 400 (Bad Request) if the prescriptionItem is not valid,
     * or with status 500 (Internal Server Error) if the prescriptionItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prescription_item")
    @Timed
    public ResponseEntity<PrescriptionItem> updatePrescriptionItem(@Valid @RequestBody PrescriptionItem prescriptionItem) throws URISyntaxException {
        log.debug("REST request to update IPrescriptionItem : {}", prescriptionItem);
        if (prescriptionItem.getId() == null) {
            return createPrescriptionItem(prescriptionItem);
        }
        PrescriptionItem result = prescriptionItemService.save(prescriptionItem);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prescriptionItem.getId().toString()))
                .body(result);
    }

    /**
     * GET  /prescription_item : get all the prescriptionItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prescriptionItem in body
     */
    @GetMapping("/prescription_item")
    @Timed
    public ResponseEntity<List<PrescriptionItem>> getAllPrescriptionItems(Pageable pageable) {
        log.debug("REST request to get a page of IPrescriptionItem");
        Page<PrescriptionItem> page = prescriptionItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prescription_item");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    /**
     * GET  /prescription_item : get all the prescriptionItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of prescriptionItem in body
     */
    @GetMapping("/prescription_item_all")
    @Timed
    public ResponseEntity<List<PrescriptionItem>> returnAllPrescriptionItems() {
        log.debug("REST request to get a page of IPrescriptionItem");
        List<PrescriptionItem> prescriptionItems = prescriptionItemService.findAll();
        return new ResponseEntity<>(prescriptionItems, HttpStatus.OK);
    }

    /**
     * GET  /prescription_item/:id : get the "id" prescriptionItem.
     *
     * @param id the id of the prescriptionItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prescriptionItem, or with status 404 (Not Found)
     */
    @GetMapping("/prescription_item/{id}")
    @Timed
    public ResponseEntity<PrescriptionItem> getPrescriptionItem(@PathVariable Long id) {
        log.debug("REST request to get IPrescriptionItem : {}", id);
        PrescriptionItem prescriptionItem = prescriptionItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prescriptionItem));
    }

    /**
     * DELETE  /prescription_item/:id : delete the "id" prescriptionItem.
     *
     * @param id the id of the prescriptionItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prescription_item/{id}")
    @Timed
    public ResponseEntity<Void> deletePrescriptionItems(@PathVariable Long id) {
        log.debug("REST request to delete IPrescriptionItem : {}", id);
        prescriptionItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/prescription_item?query=:query : search for the prescriptionItem corresponding
     * to the query.
     *
     * @param query    the query of the prescriptionItem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/prescription_item")
    @Timed
    public ResponseEntity<List<PrescriptionItem>> searchPrescriptionItems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PrescriptionItem for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<PrescriptionItem> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<PrescriptionItem> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<PrescriptionItem> page = prescriptionItemService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/prescriptionItem");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

     /* GET  /prescription_item_stream : get all the prescription items as a stream.
      *
      * @return the ResponseEntity with status 200 (OK) and the stream of prescription items
      */
    @Transactional
    @GetMapping("/prescription_item_stream")
    @Timed
    public List<PrescriptionItem> getAllPrescriptionStream() {
        log.debug("REST request to get a stream of ICD10s");
        try(Stream<PrescriptionItem> stream = prescriptionItemService.findAllByGenericNameNotNull()){
            return   stream.collect(Collectors.toList());
        }
    }

}