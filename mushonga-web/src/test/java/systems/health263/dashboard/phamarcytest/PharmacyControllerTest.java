package systems.health263.dashboard.phamarcytest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.mushonga.search.endpoint.controllers.pharmacy.PharmacyController;
import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacyDTO;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import info.mushonga.search.service.user.ISystemUserService;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.AddressType;
import info.mushonga.search.utility.enums.IndicatorISOCountryCode;
import info.mushonga.search.utility.enums.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvc unit Test
 *
 * Test class for the {@link info.mushonga.search.endpoint.controllers.pharmacy.PharmacyController} Pharmacy service
 *
 * @author percym
 */
@RunWith(SpringRunner.class)
@WebMvcTest( PharmacyController.class)
public class PharmacyControllerTest {

//    private String staticURL = "http://localhost:";
//
////    @LocalServerPort
////    private int port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;

    @Mock
    IPharmacyService pharmacyServiceMock;

    @Mock
    ISystemUserService systemUserServiceMock;

    @Autowired
    MockMvc mockMvc;

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

        mockMvc = MockMvcBuilders
                .standaloneSetup(new PharmacyController(pharmacyServiceMock , systemUserServiceMock))
                .build();

    }

    @Test
    public void getPharmacy() throws Exception {
        String URI = "/api/pharmacy/1";
        String jsonInput="";
        try {
          jsonInput = this.converttoJson(pharmacy);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        HttpEntity<PharmacyDTO> httpEntity = new HttpEntity<PharmacyDTO>(pharmacyDTO,httpHeaders);
//        ResponseEntity<Pharmacy> responseEntity = testRestTemplate.exchange(getCompleteEndPoint(URI), HttpMethod.POST, httpEntity, Pharmacy.class);
//        assertThat(responseEntity).isEqualTo(jsonInput);
//        when(pharmacyServiceMock.getOne(1L)).thenReturn(pharmacy);

                mockMvc
               .perform(MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(pharmacy.toString()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void savePharmacy() throws Exception {
        String URI = "/api/pharmacy";
        String jsonInput="";
        try {
            jsonInput = this.converttoJson(pharmacy);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Mockito.when(pharmacyServiceMock.save(pharmacy)).thenReturn(pharmacy);

        mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(jsonInput));


    }


//    public String getCompleteEndPoint(String URI){
//        System.out.println("Complete URL--->" + (staticURL + port + URI));
//        return staticURL + port + URI;
//    }

    public String converttoJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
