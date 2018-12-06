package info.mushonga.search.model.user;

import lombok.Data;
import lombok.ToString;
import info.mushonga.search.imodel.user.ISystemUser;
/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation of the {@link ISystemUser User} for Login
 */
@Data
@ToString(callSuper = true)
public class LoginSystemUserDTO {

    private String email;

    private String password;

}
