package info.mushonga.search.iservice.specifications.pharmacy;

import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.user.SystemUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Specification to get a pharmacy from user id
 *
 * @author percym
 */

public class PharmacyByUserId implements Specification<Pharmacy> {
    private SystemUser filter;

    public PharmacyByUserId(SystemUser filter) {
        this.filter = filter;
    }


    @Override
    public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filter.getId() != null) {
            CriteriaQuery<SystemUser> systemUserCriteriaQuery = criteriaBuilder.createQuery(SystemUser.class);
            Join<Pharmacy, SystemUser> systemUserJoin = root.join("systemUsers");
            Predicate p = criteriaBuilder.equal(systemUserJoin.get("id"), filter.getId());
            predicates.add(p);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
