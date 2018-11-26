package systems.health263.dashboard.model.user;

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
