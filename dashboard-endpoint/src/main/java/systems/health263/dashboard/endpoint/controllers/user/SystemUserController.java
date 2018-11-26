package systems.health263.dashboard.endpoint.controllers.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.jwt.JWTConfigurer;
import systems.health263.dashboard.endpoint.config.jwt.TokenProvider;
import systems.health263.dashboard.imodel.user.ISystemUser;
import systems.health263.dashboard.model.user.LoginSystemUserDTO;
import systems.health263.dashboard.model.user.SystemUser;
import systems.health263.dashboard.model.user.UserDetailsUpdateDTO;
import systems.health263.dashboard.service.client.IClientService;
import systems.health263.dashboard.service.user.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class SystemUserController {
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final ISystemUserService systemUserService;

    private final IClientService clientService;

    public SystemUserController(TokenProvider tokenProvider, PasswordEncoder passwordEncoder, ISystemUserService systemUserService, IClientService clientService) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.systemUserService = systemUserService;
        this.clientService = clientService;
    }

    @SuppressWarnings("unused")
    @PostMapping("/register")
    public ResponseEntity<ISystemUser> register(@Valid @RequestBody SystemUser systemUser,
                                                HttpServletResponse response) {
        systemUser.setPassword(this.passwordEncoder.encode(systemUser.getPassword()));
        LocalDateTime now = LocalDateTime.now();
        systemUser.setStartDate(now);

        ISystemUser systemUserSaved = this.systemUserService.saveSystemUser(systemUser);
        return new ResponseEntity<>(systemUserSaved, HttpStatus.OK);
    }

    @PostMapping("/update_password/{userId}")
    public ResponseEntity<SystemUser> updateUserPassword(@PathVariable("userId")Long userId, @Valid @RequestBody UserDetailsUpdateDTO detailsUpdateDTO,
                                                         HttpServletResponse response) {
        SystemUser systemUser = systemUserService.getSystemUserById(userId);
        if (systemUser.getId() == null) {
            throw new BadRequestAlertException("invalid user details", "Invalid user", "id exists");
        }
        if (ObjectUtils.isEmpty(systemUser)) {
            throw new BadRequestAlertException("invalid user details empty", "Invalid user", "user empty");
        }
        String encryptedPassword = this.passwordEncoder.encode(detailsUpdateDTO.getNewPassword());
        systemUser.setPassword(encryptedPassword);
        SystemUser savedSystemUser = (SystemUser) systemUserService.saveSystemUser(systemUser);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), systemUser.getId().toString()))
                .body(savedSystemUser);
         }

    @PutMapping("/update_user")
    public ResponseEntity<SystemUser> updateSystemUser(@Valid @RequestBody SystemUser systemUser,
                                                       HttpServletResponse response) {
        if (systemUser.getId() == null) {
            throw new BadRequestAlertException("invalid user details", "Invalid user", "id exists");
        }
        if (ObjectUtils.isEmpty(systemUser)) {
            throw new BadRequestAlertException("invalid user details ", "Invalid user", "id exists");
        }
        String encryptedPassword = this.passwordEncoder.encode(systemUser.getPassword());
        systemUser.setPassword(encryptedPassword);
        SystemUser savedSystemUser = (SystemUser) systemUserService.saveSystemUser(systemUser);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), systemUser.getId().toString()))
                .body(savedSystemUser);
    }

    @GetMapping("/users/{clientId}")
    public ResponseEntity<List<SystemUser>> getAllUsersByClientId(@PathVariable("clientId")Long clientId,
                                                                  HttpServletResponse response) {
        List<SystemUser> systemUser = systemUserService.findAllByLocation_Client_Id(clientId);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), String.valueOf(systemUser.size())))
                .body(systemUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<SystemUser>> getAllUsers(HttpServletResponse response) {
        List<SystemUser> systemUser = systemUserService.findAll();
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), String.valueOf(systemUser.size())))
                .body(systemUser);
    }

}
