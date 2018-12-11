package info.mushonga.search.endpoint.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Search endpoint management
 *
 * @author percym
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class SearchController {
    private static String ENTITY_NAME = "product";

}
