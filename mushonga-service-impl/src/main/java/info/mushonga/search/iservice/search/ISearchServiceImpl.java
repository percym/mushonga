package info.mushonga.search.iservice.search;

import info.mushonga.search.model.search.Search;
import info.mushonga.search.repository.search.SearchRepository;
import info.mushonga.search.service.search.ISearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author percym
 */
@Service
@Transactional
public class ISearchServiceImpl implements ISearchService{

    private final SearchRepository searchRepository;

    public ISearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public Search save(Search search) {
        return searchRepository.save(search);
    }
}
