package info.mushonga.search.iservice.hibersearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Configuration
public class HibernateSearchConfiguration {
	private final Logger logger = LoggerFactory.getLogger(HibernateSearchConfiguration.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	HibernateSearchService hibernateSearchService() {
		HibernateSearchService hibernateSearchService = new HibernateSearchService(entityManager);
		return hibernateSearchService;
	}



}
