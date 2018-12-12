package info.mushonga.search.iservice.search;

import info.mushonga.search.model.search.SearchTransaction;
import info.mushonga.search.repository.search.SearchTransactionRepository;
import info.mushonga.search.service.search.ISearchTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author percym
 */
@Service
@Transactional
public class ISearchTransactionServiceImpl implements ISearchTransactionService{

    @Autowired
    SearchTransactionRepository searchTransactionRepository;

    @Override
    public SearchTransaction save(SearchTransaction searchTransaction) {
        return searchTransactionRepository.save(searchTransaction);
    }
}
