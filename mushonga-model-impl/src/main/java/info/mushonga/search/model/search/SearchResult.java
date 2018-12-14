package info.mushonga.search.model.search;

import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.imodel.product.IProduct;
import info.mushonga.search.imodel.search.ISearchResult;
import info.mushonga.search.model.general.TimeActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ISearchResult implementation
 *
 * @author percym
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchResult extends TimeActiveRecord implements ISearchResult{

    private IProduct product;

    private IPharmacy pharmacy;

}
