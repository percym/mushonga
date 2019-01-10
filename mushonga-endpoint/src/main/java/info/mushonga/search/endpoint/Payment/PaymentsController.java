package info.mushonga.search.endpoint.Payment;

import com.codahale.metrics.annotation.Timed;
import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.ApplicationProperties;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.endpoint.dto.PaymentDTO;
import info.mushonga.search.model.account.Account;
import info.mushonga.search.model.account.Payment;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.service.user.ISystemUserService;
import info.mushonga.search.utility.enums.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import webdev.core.InitResponse;
import webdev.payments.Paynow;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private final ISystemUserService systemUserService;
    public PaymentsController(ApplicationProperties applicationProperties, ISystemUserService systemUserService) {
        this.applicationProperties = applicationProperties;
        this.systemUserService = systemUserService;
    }


    @PostMapping("/payment/paynow/ecocash")
    @Timed
    public ResponseEntity<InitResponse> payElectronicData(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException {

        String intergrationID = applicationProperties.getINTEGRATION_ID();
        String intergrationKey = applicationProperties.getINTEGRATION_KEY();
        String reference=  generateStampFromTime();
        BigDecimal amount = (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0)?BigDecimal.ZERO : paymentDTO.getAmount();
        String phone =((paymentDTO.getNumberToDeductFrom()== null)? " " : paymentDTO.getNumberToDeductFrom());
        Long userId =((paymentDTO.getUserId())); // account to pay

        SystemUser systemUser = systemUserService.getSystemUserById(userId);

        Paynow paynow = new Paynow(intergrationID, intergrationKey);

        paynow.setResultUrl("http://example.com/gateways/paynow/update");
        paynow.setReturnUrl("http://example.com/return?gateway=paynow");

        webdev.payments.Payment payment = paynow.createPayment(reference,"percymdev@gmail.com");

        payment.add("AccountFees", 1.0);

        if (ObjectUtils.isEmpty(systemUser)){
            throw  new BadRequestAlertException("User not found", ENTITY_NAME, "user account  error");
        }

        if (!phone.matches("07([7,8])((\\1=7)[1-9]|[2-5])\\d{6}")){
            throw  new BadRequestAlertException("Invalid phone number", ENTITY_NAME, "phone number error");
        }

        InitResponse response = paynow.sendMobile(payment,phone,"ecocash");

        if(response.success())
        {
            // Get the url to redirect the user to so they can make payment
            String link = response.redirectLink();

            // Get the poll url of the transaction
            String pollUrl = response.pollUrl();

            Payment accountPayment = new Payment();
                    accountPayment.setPaymentMethod(PaymentMethod.ECOCASH);
                    //set back to zero after test
                    accountPayment.setAmountBalance(BigDecimal.valueOf(1.0));
                    accountPayment.setBalanceBought(BigDecimal.valueOf(15L));
                    accountPayment.setSearchBalance(BigDecimal.valueOf(15L));
                    accountPayment.setExpiryDate(LocalDate.now().plusMonths(1));

            Account currentAccount= (Account) systemUser.getAccount();
            currentAccount.getPayments().add(accountPayment);
            currentAccount.setAmountBalance((currentAccount.getAmountBalance()==null)?BigDecimal.ZERO.add(accountPayment.getAmountPaid()):currentAccount.getAmountBalance().add(accountPayment.getAmountPaid()));
            currentAccount.setSearchBalance((currentAccount.getSearchBalance()==null)?BigDecimal.ZERO.add(accountPayment.getBalanceBought()):currentAccount.getSearchBalance().add(accountPayment.getBalanceBought()));
            currentAccount.setExpiryDate(currentAccount.getExpiryDate().plusMonths(1));
            systemUser.setAccount(currentAccount);
            systemUserService.saveSystemUser(systemUser);

        }

        return ResponseEntity.created(new URI("/payment/paynow/ecocash"))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(response)))
                .body(response);

    }

    private String generateStampFromTime(){

        LocalDateTime currentDateAndTime = LocalDateTime.now();
        int year = currentDateAndTime.getYear();
        int month = currentDateAndTime.getMonth().getValue();
        int day = currentDateAndTime.getDayOfMonth();
        int hour = currentDateAndTime.getHour();
        int minute = currentDateAndTime.getMinute();
        int second = currentDateAndTime.getSecond();
        int nano = currentDateAndTime.getNano();

        String formatted = "M"+String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+String.valueOf(hour)+String.valueOf(minute)+String.valueOf(second)+String.valueOf(nano);

        return formatted;
    }


}
