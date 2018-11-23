package systems.health263.dashboard.endpoint.controllers.clinical;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.clinical.TariffCode;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.clinical.TariffCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

/**
 * Rest controller for managing Tariffs
 *
 * @author Munyaradzi Takayindisa
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class TariffCodeController {

    private static final String ENTITY_NAME = "tariffcode";

    private final TariffCodeService tariffCodeService;

    public TariffCodeController(TariffCodeService tariffCodeService) {
        this.tariffCodeService = tariffCodeService;
    }

    /**
     * POST  /tariffcode : Create a new tariff.
     *
     * @param tariffCode the tariff to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tariff, or with status 400 (Bad Request) if the tariff has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tariffcode")
    @Timed
    public ResponseEntity<TariffCode> createTariffCode(@Valid @RequestBody TariffCode tariffCode) throws URISyntaxException {
        log.debug("REST request to save ITariff : {}", tariffCode);
        if (tariffCode.getId() != null) {
            throw new BadRequestAlertException("A new tariff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TariffCode result = tariffCodeService.save(tariffCode);
        return ResponseEntity.created(new URI("/api/tariffcode/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /tariff : Updates an existing tariff.
     *
     * @param tariffCode the tariff to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tariff,
     * or with status 400 (Bad Request) if the tariff is not valid,
     * or with status 500 (Internal Server Error) if the tariff couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tariffcode")
    @Timed
    public ResponseEntity<TariffCode> updateTariff(@Valid @RequestBody TariffCode tariffCode) throws URISyntaxException {
        log.debug("REST request to update ITariff : {}", tariffCode);
        if (tariffCode.getId() == null) {
            return createTariffCode(tariffCode);
        }
        TariffCode result = tariffCodeService.save(tariffCode);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tariffCode.getId().toString()))
                .body(result);
    }

    /**
     * GET  /tariff : get all the tariffs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tariff in body
     */
    @GetMapping("/tariffcode")
    @Timed
    public ResponseEntity<List<TariffCode>> getAllTariffs() {
        log.debug("REST request to get a page of ITariff");
        List<TariffCode> page = tariffCodeService.findAll();
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * GET  /tariff/:id : get the "id" tariff.
     *
     * @param id the id of the tariff to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tariff, or with status 404 (Not Found)
     */
    @GetMapping("/tariffcode/{id}")
    @Timed
    public ResponseEntity<TariffCode> getTariffCode(@PathVariable Long id) {
        log.debug("REST request to get ITariff : {}", id);
        TariffCode tariff = tariffCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tariff));
    }

    /**
     * DELETE  /tariff/:id : delete the "id" tariff.
     *
     * @param id the id of the tariff to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tariffcode/{id}")
    @Timed
    public ResponseEntity<Void> deleteTariffs(@PathVariable Long id) {
        log.debug("REST request to delete ITariff : {}", id);
        tariffCodeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tariff?query=:query : search for the tariff corresponding
     * to the query.
     *
     * @param query    the query of the tariff search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/tariffcode")
    @Timed
    public ResponseEntity<List<TariffCode>> searchTariffs(@RequestParam TariffCode tariffCode, Pageable pageable) {
        log.debug("REST request to search for a page of Tariff for query {}", tariffCode);
        CriteriaParser parser = new CriteriaParser();
//        GenericSpecificationsBuilder<TariffCode> specBuilder = new GenericSpecificationsBuilder<>();
//        Specification<TariffCode> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<TariffCode> page = tariffCodeService.findAll( pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(null, page, "/api/_search/tariffcode");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /* GET  /tariffcode_stream : get all the tariff codes items as a stream.
     *
     * @return the ResponseEntity with status 200 (OK) and the stream of tariff codes
     */
    @Transactional
    @GetMapping("/tariffcode_stream")
    @Timed
    public List<TariffCode> getAllTariffCodeStream() {
        log.debug("REST request to get a stream of TariffCodes");
        try(Stream<TariffCode> stream = tariffCodeService.findAllByGenericNameNotNull()){
            return   stream.collect(Collectors.toList());
        }
    }


}