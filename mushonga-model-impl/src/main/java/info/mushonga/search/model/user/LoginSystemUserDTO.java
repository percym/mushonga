package info.mushonga.search.model.user;

import lombok.Data;
import lombok.ToString;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation of the {@link IUser User} for Login
 */
@Data
@ToString(callSuper = true)
public class LoginSystemUserDTO {

    private String email;

    private String password;

}
