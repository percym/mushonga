package info.mushonga.search.model.pharmacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import info.mushonga.search.imodel.address.IAddress;
import info.mushonga.search.imodel.logo.ILogo;
import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.logo.Logo;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.model.user.SystemUserDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author percym
 */
@Data
public class PharmacyDTO {


    @NotNull
    private String tradingName;

    @NotNull
    private String registeredName;

    @NotNull
    private String regNumber;

    @Valid
    @NotNull
    private Address address;

    @Valid
    @NotNull
    private SystemUser systemUser ;

    @NotNull
    Boolean active;

}
