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

//    @PostMapping("/payment/ecocash")
//    @Timed
//    public ResponseEntity<ResponseDTO> payElectronic(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException, IOException {
//        log.debug("REST request to update product : {}", "");
//        ResponseDTO responseDTO = new ResponseDTO();
//
//        String intergrationID = applicationProperties.getINTEGRATION_ID();
//        String intergrationKey = applicationProperties.getINTEGRATION_KEY();
//        String reference=  "six";//LocalDateTime.now().toString();
//        BigDecimal amount = (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0)?BigDecimal.ZERO : paymentDTO.getAmount();
//        String additionalinfo =((paymentDTO.getAdditionalinfo()== null)? " " : paymentDTO.getAdditionalinfo());
//        String returnurl =((paymentDTO.getReturnurl()== null)? " " : paymentDTO.getReturnurl());
//        String resulturl =((paymentDTO.getResulturl()== null)? " " : paymentDTO.getResulturl());
//        String authemail =((paymentDTO.getAuthemail()== null)? " " : paymentDTO.getAuthemail());
//        String status =((paymentDTO.getStatus()== null)? " " : paymentDTO.getStatus());
//        String phone =((paymentDTO.getNumberToDeductFrom()== null)? " " : paymentDTO.getNumberToDeductFrom());
//        Long account =((paymentDTO.getAccountId())); // account to pay
//        String method = "ecocash";
//
//        String allValues = intergrationID+reference+amount+additionalinfo+phone+method+status+returnurl+resulturl+authemail+intergrationKey;
//
//        String hash = encryptThisString(allValues).toUpperCase();
//
//        String paynowTransactionUrl = "https://www.paynow.co.zw/interface/remotetransaction";
//        UriComponentsBuilder builder = UriComponentsBuilder
//                .fromUriString(paynowTransactionUrl)
//                .queryParam("id", intergrationID)
//                .queryParam("reference", reference)
//                .queryParam("amount", amount)
//                .queryParam("additionalinfo", additionalinfo)
//                .queryParam("returnurl", returnurl)
//                .queryParam("resulturl", resulturl)
//                .queryParam("authemail", authemail)
//                .queryParam("status", status)
//                .queryParam("hash", hash)
//                .queryParam("phone", phone)
//                .queryParam("method", "ecocash");
//
//        Map<String, String> vars = new HashMap<String, String>();
//        vars.put("id", intergrationID);
//        vars.put("reference", reference);
//        vars.put("amount", amount.toString());
//        vars.put("additionalinfo", additionalinfo);
//        vars.put("returnurl", returnurl);
//        vars.put("resulturl", resulturl);
//        vars.put("authemail", authemail);
//        vars.put("status", status);
//        vars.put("hash", hash);
//        vars.put("phone", "0774957702");
//        vars.put("method", "ecocash");
//
////        RestTemplate restTemplate = new RestTemplate();
//////        String response =restTemplate.postForObject(builder.toUriString(),null,String.class);
////        String response =restTemplate.postForObject(builder.toUriString(),null,String.class,vars);
////          String respStatus = response.substring(0,10);
//
////          if (respStatus.contains("Err")){
////              //error has happened
////              responseDTO.setMessage("Error occurred");
////              responseDTO.setSuccess(FALSE);
////
////          }else{
////              //error has not happened
////
////              Payment payment = new Payment();
////              payment.setAmountBalance(amount);
////              payment.setPaymentMethod(PaymentMethod.ECOCASH);
////
////              responseDTO.setMessage("Payment done");
////              responseDTO.setSuccess(TRUE);
////          }
//
//
//        return ResponseEntity.created(new URI("/payment/electronic"))
//                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(responseDTO)))
//                .body(responseDTO);
//    }


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

        String allValues = intergrationID.toString()+reference+amount+additionalinfo+returnurl+resulturl+authemail+status+phone;

        String hash = encryptThisString(allValues);

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

    public static String encryptThisString(String allTheValues )
    {


        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");



            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(allTheValues.getBytes(Charset.forName("UTF-8")));

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
