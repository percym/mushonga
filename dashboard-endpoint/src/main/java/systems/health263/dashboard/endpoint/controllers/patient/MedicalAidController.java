package systems.health263.dashboard.endpoint.controllers.patient;

/*
 *
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */

import com.codahale.metrics.annotation.Timed;

import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.patient.MedicalAid;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.patient.IMedicalAidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Rest controller for medical aids
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class MedicalAidController {

    private static final String ENTITY_NAME = "medical_aid";

    private final IMedicalAidService medicalAidService;

    public MedicalAidController(IMedicalAidService medicalAidService) {
        this.medicalAidService = medicalAidService;
    }

    /**
     * POST  /medical_aid : Create a new medical aid.
     *
     * @param medicalAid the medical aid to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicalAid, or with status 400 (Bad Request) if the medicalAid has already an ID
     * @throws URISyntaxException           if the Location URI syntax is incorrect
     * @throws ConstraintViolationException if the constraints doesn't meet the required
     */
    @PostMapping("/medical_aid")
    @Timed
    public ResponseEntity<MedicalAid> createMedicalAid(@Valid @RequestBody MedicalAid medicalAid) throws URISyntaxException, ConstraintViolationException {
        log.debug("REST request to save MedicalAid : {}", medicalAid);
        if (medicalAid.getId() != null) {
            throw new BadRequestAlertException("A new medicalAid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        medicalAid.setCreatedBy("mtakayindisa");
        LocalDateTime now = LocalDateTime.now();
        medicalAid.setStartDate(now);
        MedicalAid result = medicalAidService.save(medicalAid);
        return ResponseEntity.created(new URI("/api/medical_aid/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /medical_aid : Updates an existing medicalAid.
     *
     * @param medicalAid the medical aid to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicalAid,
     * or with status 400 (Bad Request) if the medicalAid is not valid,
     * or with status 500 (Internal Server Error) if the medicalAid couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medical_aid")
    @Timed
    public ResponseEntity<MedicalAid> updateMedicalAid(@Valid @RequestBody MedicalAid medicalAid) throws URISyntaxException {
        log.debug("REST request to update MedicalAid : {}", medicalAid);
        if (medicalAid.getId() == null) {
            return createMedicalAid(medicalAid);
        }
        MedicalAid result = medicalAidService.save(medicalAid);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicalAid.getId().toString()))
                .body(result);
    }

    /**
     * GET  /medical_aid : get all the medical aids.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of medicalAid in body
     */
    @GetMapping("/medical_aid")
    @Timed
    public ResponseEntity<List<MedicalAid>> getAllMedicalAids(Pageable pageable) {
        log.debug("REST request to get a page of MedicalAid");
        Page<MedicalAid> page = medicalAidService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/medical_aid");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /medical_aid/:id : get the "id" medical aid.
     *
     * @param id the id of the medicalAid to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicalAid, or with status 404 (Not Found)
     */
    @GetMapping("/medical_aid/{id}")
    @Timed
    public ResponseEntity<MedicalAid> getMedicalAid(@PathVariable Long id) {
        log.debug("REST request to get MedicalAid : {}", id);
        MedicalAid medicalAid = medicalAidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(medicalAid));
    }

    /**
     * DELETE  /medical_aid/:id : delete the "id" medical aid.
     *
     * @param id the id of the medicalAid to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medical_aid/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicalAids(@PathVariable Long id) {
        log.debug("REST request to delete MedicalAid : {}", id);
        medicalAidService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/medicalAid?query=:query : search for the medicalAid corresponding
     * to the query.
     *
     * @param query    the query of the medicalAid search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/medicalAid")
    @Timed
    public ResponseEntity<List<MedicalAid>> searchMedicalAids(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of MedicalAid for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<MedicalAid> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<MedicalAid> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<MedicalAid> page = medicalAidService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/medicalAid");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}