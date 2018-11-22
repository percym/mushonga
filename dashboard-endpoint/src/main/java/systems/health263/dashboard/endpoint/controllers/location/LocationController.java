package systems.health263.dashboard.endpoint.controllers.location;

import com.codahale.metrics.annotation.Timed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.location.Location;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.location.ILocationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing groups
 *
 * @author Percy Mugadza
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LocationController {

    private static final String ENTITY_NAME = "location";

    private final ILocationService locationService;

    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * GET  /get: get all the locations paginated.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of locations in body
     */
    @GetMapping("/location_paged")
    @Timed
    public ResponseEntity<List<Location>> getAllLocationPaged(Pageable pageable) {
        log.debug("REST request to get a page of IGroup");
        Page<Location> page = locationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bill_paged");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /location: get all the groups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("/location")
    @Timed
    public ResponseEntity<List<Location>> getAllLocation() {
        log.debug("REST request to get all IGroup");
        List<Location> locations = locationService.findAll();
        return new ResponseEntity<>(locations, null, HttpStatus.OK);
    }


    /**
     * GET  /location/:id : get the location by "id" .
     *
     * @param id the id of the location to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bill, or with status 404 (Not Found)
     */
    @GetMapping("/location/{id}")
    @Timed
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        log.debug("REST request to get IBill : {}", id);
        Location location = locationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(location));
    }
    /**
     * POST  /location: create location.
     *@param location  the permission to create
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PostMapping("/location")
    @Timed
    public ResponseEntity<Location> saveLocation(@Valid @RequestBody Location location) {
        log.debug("REST request to save location");
        if (location.getId()!= null){
            throw  new BadRequestAlertException("location information is invalid", ENTITY_NAME, "id not empty");
        }

        Location savedLocation = locationService.save(location);
        return new ResponseEntity<>(savedLocation, null, HttpStatus.OK);
    }

    /**
     * PUT  /location: update location.
     *@param location  the location to update
     * @return the ResponseEntity with status 200 (OK) and the updated group
     */
    @PutMapping("/location")
    @Timed
    public ResponseEntity<Location> updateLocation(@Valid @RequestBody Location location) {
        log.debug("REST request to update");
        if (location.getId()== null){
            throw  new BadRequestAlertException("location information is invalid", ENTITY_NAME, "id empty");
        }
        if (location.getId()== 0){
            throw  new BadRequestAlertException("location information is invalid", ENTITY_NAME, "id empty");
        }
       Location savedLocation= locationService.save(location);
        return new ResponseEntity<>(savedLocation, null, HttpStatus.OK);
    }

    /**
     * DELETE  /location/:id : delete the group by "id".
     *
     * @param id the id of the location to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/location/{id}")
    @Timed
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        log.debug("REST request to delete ILocation : {}", id);
        locationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/location?query=:query : search for the location corresponding
     * to the query.
     *
     * @param query    the query of the group search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/location")
    @Timed
    public ResponseEntity<List<Location>> searchlocation(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IGroup for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Location> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Location> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<Location> page = locationService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/location");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}