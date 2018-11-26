package systems.health263.dashboard.repository.clinical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.clinical.PrescriptionItem;

import java.util.stream.Stream;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for PrescriptionItem entity
 */
@Repository
public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, Long>, JpaSpecificationExecutor<PrescriptionItem> {
    Stream<PrescriptionItem>findAllByGenericNameNotNull();

}