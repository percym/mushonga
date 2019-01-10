package systems.health263.dashboard.phamarcytest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.mushonga.search.iservice.user.ISystemUserServiceImpl;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacyDTO;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.repository.pharmarcy.PharmacyRepository;
import info.mushonga.search.repository.user.SystemUserRepository;
import info.mushonga.search.service.pharmacy.IPharmacyService;
import info.mushonga.search.service.user.ISystemUserService;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.UserType;
import info.mushonga.search.web.MushongaWebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link IPharmacyService} Pharmacy service
 * @author percym
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MushongaWebApplication.class ,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TestRestTemplate testRestTemplate;

  public HttpHeaders httpHeaders;

  @LocalServerPort
  private int port;

  private String staticURL = "http://localhost:";

  @MockBean
  private SystemUserRepository systemUserRepository;

//  @Bean
//  ISystemUserService systemUserService (){
//    return  new ISystemUserServiceImpl(systemUserRepository);
//  }

  @InjectMocks
  private ISystemUserServiceImpl systemUserService;

  private Pharmacy pharmacy;
  private SystemUser systemUser;

  @Before
  public void setUpPharmacy(){
    systemUser = new SystemUser();
    systemUser.setUserName("Test UserS");
    systemUser.setEmail("damn@email.com");
    systemUser.setPassword("wepass");
    systemUser.setUserType(UserType.PHARMACY);
    systemUser.setAccountType(AccountType.BRONZE);
    systemUser.setActive(true);

    pharmacy = new Pharmacy();
    pharmacy.setRegisteredName("some pham");
    pharmacy.setTradingName("some pham");
    pharmacy.getSystemUsers().add(systemUser);
  }

//  @Test
//  public void save(){
//    Mockito.when(iPharmacyService.save(pharmacy)).thenReturn(pharmacy);
//  }


  @Test
  public void saveUser(){
    String URI = "/api/register";
    String jsonInput="";
    systemUser.setPassword(this.passwordEncoder.encode(systemUser.getPassword()));
    try {
      jsonInput = this.converttoJson(systemUser);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
//    HttpEntity<SystemUser> httpEntity = new HttpEntity<SystemUser>(systemUser,httpHeaders);
//    ResponseEntity<SystemUser> responseEntity = testRestTemplate.exchange(getCompleteEndPoint(URI), HttpMethod.POST, httpEntity, SystemUser.class);
//    SystemUser responseOutput = responseEntity.getBody();
//    assertThat(responseOutput).isEqualTo(jsonInput);
    Mockito.when(systemUserService.saveSystemUser(systemUser)).thenReturn(systemUser);
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
