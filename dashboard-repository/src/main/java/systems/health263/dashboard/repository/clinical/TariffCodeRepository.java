package systems.health263.dashboard.repository.clinical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.clinical.TariffCode;

import java.util.stream.Stream;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for Bill entity
 */
@Repository
public interface TariffCodeRepository extends JpaRepository<TariffCode, Long>, JpaSpecificationExecutor<TariffCode> {
    Stream<TariffCode> findAllByGenericNameNotNull();

}