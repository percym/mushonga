package systems.health263.dashboard.endpoint.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author percym
 */
@Data
public class DrugNSundryDtO {

    @NotNull
    private Boolean active;

    @NotNull
    private String drugORSundry;

    private String genericCode;

    @NotNull
    private String genericName;

    private String systemId;
}
