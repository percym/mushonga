package com.health263.model.user;

import com.health263.imodel.user.IUser;
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
