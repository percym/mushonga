package systems.health263.dashboard.endpoint.controllers.permissions;

import com.codahale.metrics.annotation.Timed;
import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.model.permissions.Permissions;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.permissions.IPermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing permissions
 *
 * @author Percy Mugadza
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PermissionsController {

    private static final String ENTITY_NAME = "permissions";

    private final IPermissionsService permissionsService;

    public PermissionsController(IPermissionsService permissionsService) {
        this.permissionsService = permissionsService;
    }

    /**
     * GET  /group: get all the  permissions paginated.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("permissions_paged")
    @Timed
    public ResponseEntity<List<Permissions>> getAllPermissionsPaged(Pageable pageable) {
        log.debug("REST request to get a page of IGroup");
        Page<Permissions> page = permissionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bill_paged");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /permissions: get all the permissions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("/permissions")
    @Timed
    public ResponseEntity<List<Permissions>> getAllPermissions() {
        log.debug("REST request to get all IPermissions");
        List<Permissions> permissions = permissionsService.findAll();
        return new ResponseEntity<>(permissions, null, HttpStatus.OK);
    }

    /**
     * POST  /permissions: update permissions.
     *@param permissions  the permission to update
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PostMapping("/permissions")
    @Timed
    public ResponseEntity<Permissions> savePermissions(@Valid @RequestBody Permissions permissions) {
        log.debug("REST request to get all IPermissions");
        if (permissions.getId()!= null){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id not empty");
        }

        Permissions permission = permissionsService.save(permissions);
        return new ResponseEntity<>(permission, null, HttpStatus.OK);
    }

    /**
     * PUT  /permissions: update permissions.
     *@param permissions  the permission to update
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PutMapping("/permissions")
    @Timed
    public ResponseEntity<Permissions> updatePermissions(@Valid @RequestBody Permissions permissions) {
        log.debug("REST request to get all IPermissions");
        if (permissions.getId()== null){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id empty");
        }
        if (permissions.getId()== 0){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id empty");
        }
        Permissions permission = permissionsService.save(permissions);
        return new ResponseEntity<>(permission, null, HttpStatus.OK);
    }


    /**
     * GET  /permissions/:id : get the permissions by "id" .
     *
     * @param id the id of the permissions to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bill, or with status 404 (Not Found)
     */
    @GetMapping("/permissions/{id}")
    @Timed
    public ResponseEntity<Permissions> getPermissions(@PathVariable Long id) {
        log.debug("REST request to get IBill : {}", id);
        Permissions permissions = permissionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(permissions));
    }

    /**
     * DELETE  /permissions/:id : delete the permissions by "id".
     *
     * @param id the id of the permissions to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/permissions/{id}")
    @Timed
    public ResponseEntity<Void> deletePermissions(@PathVariable Long id) {
        log.debug("REST request to delete IPermissions : {}", id);
        permissionsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/permissions?query=:query : search for the bill corresponding
     * to the query.
     *
     * @param query    the query of the permissions search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/permissions")
    @Timed
    public ResponseEntity<List<Permissions>> searchPermissions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IPermissions for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Permissions> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Permissions> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<Permissions> page = permissionsService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/bill");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}