package info.mushonga.search.iservice.specifications.product;

import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.user.SystemUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Specification to get a pharmacy from product id
 *
 * @author percym
 */

public class PharmacyByProductId implements Specification<Pharmacy> {
    private Long filter;

    public PharmacyByProductId(Long filter) {
        this.filter = filter;
    }


    @Override
    public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filter != null) {
//            CriteriaQuery<SystemUser> systemUserCriteriaQuery = criteriaBuilder.createQuery(SystemUser.class);
            Join<Pharmacy, Product> systemUserJoin = root.join("products");
            Predicate p = criteriaBuilder.equal(systemUserJoin.get("id"), filter);
            predicates.add(p);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
