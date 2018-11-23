package systems.health263.dashboard.endpoint.controllers.group;

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
import systems.health263.dashboard.model.group.AdminGroup;
import systems.health263.dashboard.model.group.Group;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.group.IAdminGroupService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing admin groups
 *
 * @author Percy Mugadza
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AdminGroupController {

    private static final String ENTITY_NAME = "admin_group";

    private final IAdminGroupService adminGroupService;

    public AdminGroupController(IAdminGroupService adminGroupService) {
        this.adminGroupService = adminGroupService;
    }

    /**
     * GET  /group: get all the admin groups paginated.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("/admin_group_paged")
    @Timed
    public ResponseEntity<List<AdminGroup>> getAllGroupPaged(Pageable pageable) {
        log.debug("REST request to get a page of IGroup");
        Page<AdminGroup> page = adminGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/admin_group_paged");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /group: get all the admin groups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bill in body
     */
    @GetMapping("/admin_group")
    @Timed
    public ResponseEntity<List<AdminGroup>> getAllGroup() {
        log.debug("REST request to get all IGroup");
        List<AdminGroup> groups = adminGroupService.findAll();
        return new ResponseEntity<>(groups, null, HttpStatus.OK);
    }


    /**
     * GET  /admin_group/:id : get the group by "id" .
     *
     * @param id the id of the group to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bill, or with status 404 (Not Found)
     */
    @GetMapping("/admin_group/{id}")
    @Timed
    public ResponseEntity<AdminGroup> getGroup(@PathVariable Long id) {
        log.debug("REST request to get IBill : {}", id);
        AdminGroup group = adminGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(group));
    }
    /**
     * POST  /admin_group: update group.
     *@param group  the permission to update
     * @return the ResponseEntity with status 200 (OK) and the updated permissions
     */
    @PostMapping("/admin_group")
    @Timed
    public ResponseEntity<AdminGroup> saveGroup(@Valid @RequestBody AdminGroup group) {
        log.debug("REST request to get all IPermissions");
        if (group.getId()!= null){
            throw  new BadRequestAlertException("group information is invalid", ENTITY_NAME, "id not empty");
        }

        AdminGroup savedGroup = adminGroupService.saveGroup(group);
        return new ResponseEntity<>(savedGroup, null, HttpStatus.OK);
    }

    /**
     * PUT  /admin_group: update group.
     *@param group  the group to update
     * @return the ResponseEntity with status 200 (OK) and the updated group
     */
    @PutMapping("/admin_group")
    @Timed
    public ResponseEntity<AdminGroup> updateGroup(@Valid @RequestBody AdminGroup group) {
        log.debug("REST request to get all IPermissions");
        if (group.getId()== null){
            throw  new BadRequestAlertException("group information is invalid", ENTITY_NAME, "id empty");
        }
        if (group.getId()== 0){
            throw  new BadRequestAlertException("group information is invalid", ENTITY_NAME, "id empty");
        }
        AdminGroup savedGroup= adminGroupService.saveGroup(group);
        return new ResponseEntity<>(savedGroup, null, HttpStatus.OK);
    }

    /**
     * DELETE  /group/:id : delete the group by "id".
     *
     * @param id the id of the group to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/admin_group/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        log.debug("REST request to delete IGroup : {}", id);
        adminGroupService.deleteGroup(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/admin_group?query=:query : search for the bill corresponding
     * to the query.
     *
     * @param query    the query of the group search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/admin_group")
    @Timed
    public ResponseEntity<List<AdminGroup>> searchGroups(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of IGroup for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Group> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Group> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<AdminGroup> page = adminGroupService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/admin_group");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}