package systems.health263.dashboard.serviceimpl.clinical;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systems.health263.dashboard.model.clinical.TariffCode;
import systems.health263.dashboard.repository.clinical.TariffCodeRepository;
import systems.health263.dashboard.service.clinical.TariffCodeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

/**
 * Service implementation for Tariff entity
 *
 * @author percym
 */
@Slf4j
@Service
@Transactional
public class ITariffCodeServiceImpl implements TariffCodeService {

    @PersistenceContext
    private EntityManager em;

    private final TariffCodeRepository tariffCodeRepository;

    public ITariffCodeServiceImpl(TariffCodeRepository tariffCodeRepository) {
        this.tariffCodeRepository = tariffCodeRepository;
    }

    /**
     * Save a tariff.
     *
     * @param tariffCode the entity to save
     * @return the persisted entity
     */
    @Override
    public TariffCode save(TariffCode tariffCode) {
        log.debug("Request to save Tariff : {}", tariffCode);
        return em.merge(tariffCode);
    }

    @Override
    public Page<TariffCode> findAll(Pageable pageable) {
        return tariffCodeRepository.findAll(pageable);
    }

    /**
     * Get all the tariff.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TariffCode> findAll() {
        log.debug("Request to get all Tariffs");
        return tariffCodeRepository.findAll();
    }

    /**
     * Get one tariff by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TariffCode findOne(Long id) {
        log.debug("Request to get Tariffs : {}", id);
        return tariffCodeRepository.getOne(id);
    }

    /**
     * Delete the tariff by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tariffs : {}", id);
        tariffCodeRepository.deleteById(id);
    }

    /**
     * Search for the tariff corresponding to the query.
     *
     * @param criteria the specifications to use for the query
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<TariffCode> findAll(TariffCode criteria, Pageable pageable) {
        log.debug("find by criteria : {}, page: {}", criteria, pageable);
        return tariffCodeRepository.findAll(pageable);
    }

    /**
     * Get all the streams of tariff.
     *
     * @return the list of entities
     */
    @Override
    public Stream<TariffCode> findAllByGenericNameNotNull() {
        return tariffCodeRepository.findAllByGenericNameNotNull();
    }

}