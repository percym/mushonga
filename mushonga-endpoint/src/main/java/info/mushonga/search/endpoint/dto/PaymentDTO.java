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

    @NotNull
    private String additionalinfo;

    @NotNull
    private String returnurl;

    @NotNull
    private String resulturl;

    @NotNull
    private String authemail;

    @NotNull
    private String status;

    @NotNull
    private String hash;

    @NotNull
    String numberToDeductFrom;

    @NotNull
    Long accountId;


}
