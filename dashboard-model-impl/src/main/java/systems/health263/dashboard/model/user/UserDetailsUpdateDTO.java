package com.health263.model.user;

import com.health263.imodel.user.IUser;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Percy Mugadza
 * <p>
 *DTO for update of user password and email
 */
@Data
@ToString(callSuper = true)
public class UserDetailsUpdateDTO {
    @NotNull
    private String newPassword;

}
