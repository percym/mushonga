package systems.health263.dashboard.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.patient.MedicalAid;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Repository for Medical Aid entity
 */
@Repository
public interface MedicalAidRepository extends JpaRepository<MedicalAid, Long>, JpaSpecificationExecutor<MedicalAid> {

}