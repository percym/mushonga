package info.mushonga.search.endpoint.Payment;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.util.ApplicationProperties;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.endpoint.dto.PaymentDTO;
import info.mushonga.search.endpoint.dto.ResponseDTO;
import info.mushonga.search.model.account.Payment;
import info.mushonga.search.utility.enums.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import webdev.core.InitResponse;
import webdev.payments.Paynow;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

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


    @PostMapping("/payment/paynow/ecocash/")
    @Timed
    public ResponseEntity<ResponseDTO> payElectronicData(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException {

        String intergrationID = applicationProperties.getINTEGRATION_ID();
        String intergrationKey = applicationProperties.getINTEGRATION_KEY();
        String reference= LocalDateTime.now().toString();
        BigDecimal amount = (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0)?BigDecimal.ZERO : paymentDTO.getAmount();
        String additionalinfo =((paymentDTO.getAdditionalinfo()== null)? " " : paymentDTO.getAdditionalinfo());
        String returnurl =((paymentDTO.getReturnurl()== null)? " " : paymentDTO.getReturnurl());
        String resulturl =((paymentDTO.getResulturl()== null)? " " : paymentDTO.getResulturl());
        String authemail =((paymentDTO.getAuthemail()== null)? " " : paymentDTO.getAuthemail());
        String status =((paymentDTO.getStatus()== null)? " " : paymentDTO.getStatus());
        String phone =((paymentDTO.getNumberToDeductFrom()== null)? " " : paymentDTO.getNumberToDeductFrom());
        Long account =((paymentDTO.getAccountId())); // account to pay


        Paynow paynow = new Paynow(intergrationID, intergrationKey);

        paynow.setResultUrl("http://example.com/gateways/paynow/update");
        paynow.setReturnUrl("http://example.com/return?gateway=paynow");

        webdev.payments.Payment payment = paynow.createPayment(reference,"percymdev@gmail.com");

        payment.add("Bananas", 2.5);


        InitResponse response = paynow.sendMobile(payment,"0773473381","ecocash");

        if(response.success())
        {
            // Get the url to redirect the user to so they can make payment
            String link = response.redirectLink();

            // Get the poll url of the transaction
            String pollUrl = response.pollUrl();
        }
        else
        {
            // Something went wrong
            System.out.println(response);
        }

        return ResponseEntity.created(new URI("/payment/electronic"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(response)))
                .body(new ResponseDTO());

    }


}
