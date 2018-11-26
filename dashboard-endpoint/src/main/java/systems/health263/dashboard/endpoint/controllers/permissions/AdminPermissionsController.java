package systems.health263.dashboard.endpoint.controllers.permissions;

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
import systems.health263.dashboard.model.permissions.AdminPermissions;
import systems.health263.dashboard.model.permissions.Permissions;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.permissions.IAdminPermissionsService;
import systems.health263.dashboard.service.permissions.IPermissionsService;

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
public class AdminPermissionsController {

    private static final String ENTITY_NAME = "permissions";

    private final IAdminPermissionsService adminPermissionsService;

    public AdminPermissionsController(IAdminPermissionsService adminPermissionsService) {
        this.adminPermissionsService = adminPermissionsService;
    }

    /**
     * GET  /admin_permissions_paged: get all the  permissions paginated.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("admin_permissions_paged")
    @Timed
    public ResponseEntity<List<AdminPermissions>> getAllPermissionsPaged(Pageable pageable) {
        log.debug("REST request to get a page of IGroup");
        Page<AdminPermissions> page = adminPermissionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/admin_permissions_paged");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /admin_permissions: get all the permissions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("/admin_permissions")
    @Timed
    public ResponseEntity<List<AdminPermissions>> getAllPermissions() {
        log.debug("REST request to get all IPermissions");
        List<AdminPermissions> permissions = adminPermissionsService.findAll();
        return new ResponseEntity<>(permissions, null, HttpStatus.OK);
    }

    /**
     * POST  /permissions: update permissions.
     *@param permissions  the permission to update
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PostMapping("/admin_permissions")
    @Timed
    public ResponseEntity<AdminPermissions> savePermissions(@Valid @RequestBody AdminPermissions permissions) {
        log.debug("REST request to get all IPermissions");
        if (permissions.getId()!= null){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id not empty");
        }

        AdminPermissions permission = adminPermissionsService.save(permissions);
        return new ResponseEntity<>(permission, null, HttpStatus.OK);
    }

    /**
     * PUT  /permissions: update permissions.
     *@param permissions  the permission to update
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PutMapping("/admin_permissions")
    @Timed
    public ResponseEntity<AdminPermissions> updatePermissions(@Valid @RequestBody AdminPermissions permissions) {
        log.debug("REST request to get all IPermissions");
        if (permissions.getId()== null){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id empty");
        }
        if (permissions.getId()== 0){
            throw  new BadRequestAlertException("permission information is invalid", ENTITY_NAME, "id empty");
        }
        AdminPermissions permission = adminPermissionsService.save(permissions);
        return new ResponseEntity<>(permission, null, HttpStatus.OK);
    }


    /**
     * GET  /permissions/:id : get the permissions by "id" .
     *
     * @param id the id of the permissions to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bill, or with status 404 (Not Found)
     */
    @GetMapping("/admin_permissions/{id}")
    @Timed
    public ResponseEntity<AdminPermissions> getPermissions(@PathVariable Long id) {
        log.debug("REST request to get IBill : {}", id);
        AdminPermissions permissions = adminPermissionsService.findOne(id);
         return ResponseUtil.wrapOrNotFound(Optional.ofNullable(permissions));
    }

    /**
     * DELETE  /admin_permissions/:id : delete the permissions by "id".
     *
     * @param id the id of the permissions to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admin_permissions/{id}")
    @Timed
    public ResponseEntity<Void> deletePermissions(@PathVariable Long id) {
        log.debug("REST request to delete IPermissions : {}", id);
        adminPermissionsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admin_permissions?query=:query : search for the bill corresponding
     * to the query.
     *
     * @param query    the query of the permissions search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/admin_permissions")
    @Timed
    public ResponseEntity<List<AdminPermissions>> searchPermissions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IPermissions for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<AdminPermissions> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<AdminPermissions> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<AdminPermissions> page = adminPermissionsService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/admin_permissions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}