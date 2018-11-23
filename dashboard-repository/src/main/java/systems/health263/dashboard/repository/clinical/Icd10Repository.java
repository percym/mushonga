package systems.health263.dashboard.repository.clinical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import systems.health263.dashboard.model.clinical.DataICD10;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Percy Mugadza
 * <p>
 * Repository for ICD10 entity
 */
@Repository
public interface Icd10Repository extends JpaRepository<DataICD10,Long>, JpaSpecificationExecutor<DataICD10> {
    List<DataICD10> findAllByCodeFieldContaining(String codeContains);
    List<DataICD10> findAllByDescriptionFieldContaining(String descriptionContains);
    Stream<DataICD10> findAllByCodeFieldNotNull();
}
