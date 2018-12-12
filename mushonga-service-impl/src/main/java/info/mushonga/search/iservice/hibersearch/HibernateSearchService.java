package info.mushonga.search.iservice.hibersearch;


import ch.qos.logback.classic.Logger;
import info.mushonga.search.model.product.Product;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class HibernateSearchService  {

	private final Logger logger = (Logger) LoggerFactory.getLogger(HibernateSearchService.class);

	private final EntityManager entityManager;


	@Autowired
	public HibernateSearchService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	public void initializeHibernateSearch() {

		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Transactional
	public List<Product> fuzzySearch(String searchTerm){

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Product.class)
				.get();
		Query luceneQuery = qb.keyword()
				.fuzzy()
				.withEditDistanceUpTo(1)
				.withPrefixLength(1)
				.onFields("genericName", "productDescription")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);

		// execute search

		List<Product> productList = null;
		try {
			productList  = jpaQuery.getResultList();
		} catch (NoResultException nre) {
			logger.warn("No result found");

		}

		return productList;

	
	}
}
