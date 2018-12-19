package info.mushonga.search.endpoint.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    String numberToDeductFrom;

    @NotNull
    Long accountId;


}
