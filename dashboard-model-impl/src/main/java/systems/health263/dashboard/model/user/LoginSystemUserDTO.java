package systems.health263.dashboard.model.user;

import systems.health263.dashboard.imodel.user.ISystemUser;
import lombok.Data;
import lombok.ToString;

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
