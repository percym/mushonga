package systems.health263.dashboard.endpoint.controllers.client;

import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import systems.health263.dashboard.endpoint.config.app.ResponseUtil;
import systems.health263.dashboard.endpoint.config.app.StorageProperties;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.app.util.PaginationUtil;
import systems.health263.dashboard.endpoint.fileUpload.FileStorageService;
import systems.health263.dashboard.model.client.Client;
import systems.health263.dashboard.model.client.Logo;
import systems.health263.dashboard.service.CriteriaParser;
import systems.health263.dashboard.service.GenericSpecificationsBuilder;
import systems.health263.dashboard.service.ModelSpecification;
import systems.health263.dashboard.service.client.IClientService;
import systems.health263.dashboard.serviceimpl.fileUpload.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/*
 * Rest controller for managing Clients
 *
 * @author Munyaradzi Takayindisa
 *
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("*/api")
public class ClientController {

    private static final String ENTITY_NAME = "client";

    private final IClientService clientService;
    private final StorageService storageService;

    private final FileStorageService fileStorageService;
    private final ResourceLoader resourceLoader;
    private final StorageProperties storageProperties;

    public ClientController(IClientService clientService, StorageService storageService, FileStorageService fileStorageService, ResourceLoader resourceLoader, StorageProperties storageProperties) {
        this.clientService = clientService;
        this.storageService = storageService;
        this.fileStorageService = fileStorageService;
        this.resourceLoader = resourceLoader;
        this.storageProperties = storageProperties;
    }

    /**
     * POST  /client : Create a new client.
     *
     * @param client the client to create
     * @return the ResponseEntity with status 201 (Created) and with body the new client, or with status 400 (Bad Request) if the client has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/client")
    @Timed
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) throws URISyntaxException {
        log.debug("REST request to save IClient : {}", client);
        if (client.getId() != null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Client result = clientService.save(client);
        return ResponseEntity.created(new URI("/api/client/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /client : Updates an existing client.
     *
     * @param client the client to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated client,
     * or with status 400 (Bad Request) if the client is not valid,
     * or with status 500 (Internal Server Error) if the client couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/client")
    @Timed
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) throws URISyntaxException {
        log.debug("REST request to update IClient : {}", client);
        if (client.getId() == null) {
            return createClient(client);
        }
        Client result = clientService.save(client);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, client.getId().toString()))
                .body(result);
    }

    /**
     * GET  /client : get all the clients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of client in body
     */
    @GetMapping("/client")
    @Timed
    public ResponseEntity<List<Client>> getAllClients(Pageable pageable) {
        log.debug("REST request to get a page of IClient");
        Page<Client> page = clientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/client");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /client/:id : get the "id" client.
     *
     * @param id the id of the client to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the client, or with status 404 (Not Found)
     */
    @GetMapping("/client/{id}")
    @Timed
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        log.debug("REST request to get IClient : {}", id);
        Client client = clientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(client));
    }

    /**
     * DELETE  /client/:id : delete the "id" client.
     *
     * @param id the id of the client to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/client/{id}")
    @Timed
    public ResponseEntity<Void> deleteClients(@PathVariable Long id) {
        log.debug("REST request to delete IClient : {}", id);
        clientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/client?query=:query : search for the client corresponding
     * to the query.
     *
     * @param query    the query of the client search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/client")
    @Timed
    public ResponseEntity<List<Client>> searchClients(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Client for query {}", query);
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Client> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Client> spec = specBuilder.build(parser.parse(query), ModelSpecification::new);
        Page<Client> page = clientService.findAll(spec, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/client");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/client_logo/{clientId}")
    @Timed
    public ResponseEntity<Client> handleFileUpload(@RequestParam("file") MultipartFile file,
                                               RedirectAttributes redirectAttributes, @PathVariable("clientId")Long clientId) {
        Client client =clientService.findOne(clientId);
        Logo logo = new Logo();
//        storageService.store(file);
        String fileName = fileStorageService.storeFile(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dova/api/logos/")
                .path(fileName)
                .toUriString();
        logo.setFileName(fileName);
        logo.setFileDownloadUri(fileDownloadUri);
        logo.setSize(file.getSize());
        logo.setFileType(file.getContentType());
//
//        if (file.getContentType().contentEquals("image/png")||file.getContentType().contentEquals("image/jpeg")){
//            System.out.println(file.getContentType() + "true");
//        }else{
//            System.out.println(file.getContentType() + "false");
//        }
        client.setLogo(logo);
        Client result = clientService.save(client);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, client.getId().toString()))
                .body(result);
    }


    @GetMapping(value="/logos/{filename:.+}")
    public @ResponseBody void affichimage(@PathVariable String filename
            , HttpServletResponse response, HttpServletRequest request) throws
            IOException,NullPointerException
    {
        //database
        Resource file = storageService.loadAsResource(filename);
        File imageFile = new File(file.getURI());
        response.setContentType("image/jpeg");
        InputStream in=new FileInputStream(imageFile);
        IOUtils.copy(in, response.getOutputStream());
    }

}