package info.mushonga.search.model.pharmacy;

import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.product.Product;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author percym
 */
@Data
public class PharmacySearchDTO {

    @Valid
    private Pharmacy pharmacy;


    @Valid
    private Product product;

    @NotNull
    Boolean active;

}
