package systems.health263.dashboard.endpoint.controllers.clinical;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.clinical.DataICD10;
import systems.health263.dashboard.service.clinical.Icd10Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Rest controller for managing Encounters
 *
 * @author Munyaradzi Takayindisa
 *
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class Icd10Controller {

    private static final String ENTITY_NAME = "encounter";

    private final Icd10Service icd10Service;

    public Icd10Controller(Icd10Service icd10Service) {
        this.icd10Service = icd10Service;
    }


    /**
     * GET  /encounter : get all the icd10 items.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of encounter in body
     */
    @GetMapping("/icd10")
    @Timed
    public ResponseEntity<List<DataICD10>> getAllPaged(Pageable pageable) {
        log.debug("REST request to get a page of IEncounter");
        Page<DataICD10> page = icd10Service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/icd10");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /encounter : get all the icd10 items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of encounter in body
     */
    @GetMapping("/icd10All")
    @Timed
    public ResponseEntity<List<DataICD10>> getAll() {
        log.debug("REST request to get a page of IEncounter");
        List<DataICD10> icd10s = icd10Service.findAll();
        return new ResponseEntity<>(icd10s, HttpStatus.OK);
    }


    /**
     * GET  /icd10All/{codeField} : get all the icd10 items.
     * @param  codeField
     *
     * @return the ResponseEntity with status 200 (OK) and the list of encounter in body
     */
    @GetMapping("/icd10All/{codeField}")
    @Timed
    public ResponseEntity<List<DataICD10>> getAllSearchByCodeField(@PathVariable("codeField")String codeField) {
        log.debug("REST request to get a page of IEncounter");
        List<DataICD10> icd10s = icd10Service.findAllByDescriptionFieldContaining(codeField);
        return new ResponseEntity<>(icd10s, HttpStatus.OK);
    }


    /**
     * GET  /icd10All/{descriptionField} : get all the icd10 items which match query.
     * @param descriptionField
     * @return the ResponseEntity with status 200 (OK) and the list of icd10s in body
     */
    @GetMapping("/icd10All/{descriptionField}")
    @Timed
    public ResponseEntity<List<DataICD10>> getAllStream(@PathVariable("descriptionField")String descriptionField) {
        log.debug("REST request to get a page of IEncounter");
        List<DataICD10> icd10s = icd10Service.findAllByDescriptionFieldContaining(descriptionField);
        return new ResponseEntity<>(icd10s, HttpStatus.OK);
    }

    /**
     * GET  /icd10stream : get all the icd10 items as a stream.
     *
     * @return the ResponseEntity with status 200 (OK) and the stream of icd10s
     */
    @Transactional
    @GetMapping("/icd10stream")
    @Timed
    public List<DataICD10> getAllStream() {
        log.debug("REST request to get a stream of ICD10s");
        try(Stream<DataICD10> stream = icd10Service.findAllByCodeFieldNotNull()){
            return   stream.collect(Collectors.toList());
        }
    }

}
