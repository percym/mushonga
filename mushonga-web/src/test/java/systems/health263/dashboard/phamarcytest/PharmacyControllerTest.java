package systems.health263.dashboard.phamarcytest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacyDTO;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.AddressType;
import info.mushonga.search.utility.enums.IndicatorISOCountryCode;
import info.mushonga.search.utility.enums.UserType;
import info.mushonga.search.web.MushongaWebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link info.mushonga.search.endpoint.controllers.pharmacy.PharmacyController} Pharmacy service
 *
 * @author percym
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = MushongaWebApplication.class ,  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PharmacyControllerTest {

    private String staticURL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    public HttpHeaders httpHeaders;

    private Pharmacy pharmacy;
    private PharmacyDTO pharmacyDTO;
    private SystemUser systemUser;
    private Address pharmAddress;

    @Before
    public void setUp() throws Exception {
        httpHeaders = new HttpHeaders();
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("Test User");
        systemUser.setEmail("damn@email.com");
        systemUser.setPassword("wepass");
        systemUser.setUserType(UserType.PHARMACY);
        systemUser.setAccountType(AccountType.BRONZE);

        pharmAddress = new Address();

        pharmAddress.setCountryCode(IndicatorISOCountryCode.ZW);
        pharmAddress.setLine1("14 Code");
        pharmAddress.setLine2("Street");
        pharmAddress.setLine3("Some Burb");
        pharmAddress.setCity("Harare");
        pharmAddress.setType(AddressType.WORK);

//        pharmacy = new Pharmacy();
//        pharmacy.setRegisteredName("some pham");
//        pharmacy.setTradingName("some pham");
//        pharmacy.getSystemUsers().add(systemUser);

        pharmacyDTO = new PharmacyDTO();
        pharmacyDTO.setRegisteredName("some pham");
        pharmacyDTO.setTradingName("some pham");
        pharmacyDTO.setSystemUser(systemUser);
        pharmacyDTO.setRegNumber("234556");
        pharmacyDTO.setAddress(pharmAddress);
        pharmacyDTO.setActive(TRUE);

        pharmacy = new Pharmacy();
        pharmacy.setTradingName(pharmacyDTO.getTradingName());
        pharmacy.setRegisteredName(pharmacyDTO.getRegisteredName());
        pharmacy.setRegNumber(pharmacyDTO.getRegNumber());
        pharmacy.setAddress(pharmacyDTO.getAddress());

    }

    @Test
    public void testSavePharmacy(){
        String URI = "/pharmacy";
        String jsonInput="";
        try {
          jsonInput = this.converttoJson(pharmacy);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<PharmacyDTO> httpEntity = new HttpEntity<PharmacyDTO>(pharmacyDTO,httpHeaders);
        ResponseEntity<Pharmacy> responseEntity = testRestTemplate.exchange(getCompleteEndPoint(URI), HttpMethod.POST, httpEntity, Pharmacy.class);
        assertThat(responseEntity).isEqualTo(jsonInput);
    }

    public String getCompleteEndPoint(String URI){
        System.out.println("Complete URL--->" + (staticURL + port + URI));
        return staticURL + port + URI;
    }

    public String converttoJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
