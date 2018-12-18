package info.mushonga.search.endpoint.Payment;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.util.ApplicationProperties;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.endpoint.dto.PaymentDTO;
import info.mushonga.search.endpoint.dto.ResponseDTO;
import info.mushonga.search.model.account.Payment;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.utility.enums.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * The controller for managing payments
 *
 * @author percym
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class PaymentsController {

    public static final String ENTITY_NAME = "payment";

    private final ApplicationProperties applicationProperties;

    public PaymentsController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/payment/ecocash")
    @Timed
    public ResponseEntity<ResponseDTO> payElectronic(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException {
        log.debug("REST request to update product : {}", "");
        ResponseDTO responseDTO = new ResponseDTO();


        String intergrationID = applicationProperties.getINTEGRATION_ID();
        String reference= LocalDateTime.now().toString();
        BigDecimal amount = (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0)?BigDecimal.ZERO : paymentDTO.getAmount();
        String additionalinfo =((paymentDTO.getAdditionalinfo()== null)? " " : paymentDTO.getAdditionalinfo());
        String returnurl =((paymentDTO.getReturnurl()== null)? " " : paymentDTO.getReturnurl());
        String resulturl =((paymentDTO.getResulturl()== null)? " " : paymentDTO.getResulturl());
        String authemail =((paymentDTO.getAuthemail()== null)? " " : paymentDTO.getAuthemail());
        String status =((paymentDTO.getStatus()== null)? " " : paymentDTO.getStatus());
        String hash =((paymentDTO.getHash()== null)? " " : paymentDTO.getHash());
        String phone =((paymentDTO.getNumberToDeductFrom()== null)? " " : paymentDTO.getNumberToDeductFrom());
        Long account =((paymentDTO.getAccountId())); // account to pay


        String paynowTransactionUrl = "https://www.paynow.co.zw/interface/remotetransaction";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(paynowTransactionUrl)
                .queryParam("id", intergrationID)
                .queryParam("reference", reference)
                .queryParam("amount", amount)
                .queryParam("additionalinfo", additionalinfo)
                .queryParam("returnurl", returnurl)
                .queryParam("resulturl", resulturl)
                .queryParam("authemail", authemail)
                .queryParam("status", status)
                .queryParam("hash", hash)
                .queryParam("phone", phone)
                .queryParam("method", "ecocash");

        Map<String, String> vars = new HashMap<String, String>();
        vars.put("id", intergrationID);
        vars.put("reference", reference);
        vars.put("amount", amount.toString());
        vars.put("additionalinfo", additionalinfo);
        vars.put("returnurl", returnurl);
        vars.put("resulturl", resulturl);
        vars.put("authemail", authemail);
        vars.put("status", status);
        vars.put("hash", hash);
        vars.put("phone", "0774957702");
        vars.put("method", "ecocash");

        RestTemplate restTemplate = new RestTemplate();
        String response =restTemplate.postForObject(builder.toUriString(),null,String.class);
//        String response =restTemplate.postForObject(builder.toUriString(),null,String.class,vars);
          String respStatus = response.substring(0,10);
          if (respStatus.contains("Err")){
              //error has happened
              responseDTO.setMessage("Error occurred");
              responseDTO.setSuccess(FALSE);

          }else{
              //error has not happened

              Payment payment = new Payment();
              payment.setAmountBalance(amount);
              payment.setPaymentMethod(PaymentMethod.ECOCASH);

              responseDTO.setMessage("Payment done");
              responseDTO.setSuccess(TRUE);
          }


        return ResponseEntity.created(new URI("/payment/electronic"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(response)))
                .body(responseDTO);
    }



}
