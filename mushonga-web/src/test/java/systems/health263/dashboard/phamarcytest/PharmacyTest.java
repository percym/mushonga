package systems.health263.dashboard.phamarcytest;

import info.mushonga.search.iservice.pharmacy.IPharmacyServiceImpl;
import info.mushonga.search.model.address.Address;
import info.mushonga.search.model.pharmacy.Pharmacy;
import info.mushonga.search.model.pharmacy.PharmacyDTO;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.repository.pharmarcy.PharmacyRepository;
import info.mushonga.search.utility.enums.AccountType;
import info.mushonga.search.utility.enums.AddressType;
import info.mushonga.search.utility.enums.IndicatorISOCountryCode;
import info.mushonga.search.utility.enums.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author percym
 */
@RunWith(MockitoJUnitRunner.class)
public class PharmacyTest {

    @Mock
    PharmacyRepository repository;

    @InjectMocks
    IPharmacyServiceImpl pharmacyService ;
    SystemUser systemUser;

    private Address pharmAddress;
    private PharmacyDTO pharmacyDTO;
    private Pharmacy pharmacy;

    @Before
    public void initialise() {
        systemUser = new SystemUser();
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
        pharmacy.setId(1L);
//        pharmacy.setTradingName(pharmacyDTO.getTradingName());
//        pharmacy.setRegisteredName(pharmacyDTO.getRegisteredName());
        pharmacy.setRegNumber(pharmacyDTO.getRegNumber());
        pharmacy.setAddress(pharmacyDTO.getAddress());

    }
    @Test
    public void findPharmarcy(){

//        Pharmacy mockPharmacy = mock(Pharmacy.class);
        Long id = new Long(1);
        when(pharmacyService.getOne(id)).thenReturn(pharmacy);
        assertEquals(pharmacy, pharmacyService.getOne(1L));

    }


    @Test
    public void savePharmarcy(){
        when(pharmacyService.save(pharmacy)).thenReturn(pharmacy);
        assertEquals(pharmacy, pharmacyService.save(pharmacy));
    }

    //controller Tests

}
