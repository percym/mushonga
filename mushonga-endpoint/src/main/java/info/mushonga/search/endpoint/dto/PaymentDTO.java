package info.mushonga.search.endpoint.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * The payment DTO
 *
 * @author percym
 */
@Data
@NoArgsConstructor
public class PaymentDTO {


    @NotNull
    private BigDecimal amount;

    @NotEmpty
    private String additionalinfo;

    @NotEmpty
    private String returnurl;

    @NotEmpty
    private String resulturl;

    @NotEmpty
    private String authemail;

    @NotEmpty
    private String status;

    @NotEmpty
    private String hash;

    @NotEmpty
    String numberToDeductFrom;

    @NotEmpty
    Long accountId;


}
