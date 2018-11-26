package systems.health263.dashboard.endpoint.controllers.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import systems.health263.dashboard.endpoint.config.app.errors.BadRequestAlertException;
import systems.health263.dashboard.endpoint.config.app.util.HeaderUtil;
import systems.health263.dashboard.endpoint.config.jwt.JWTConfigurer;
import systems.health263.dashboard.endpoint.config.jwt.TokenProvider;
import systems.health263.dashboard.imodel.user.ISystemUser;
import systems.health263.dashboard.model.user.AdminUser;
import systems.health263.dashboard.model.user.LoginSystemUserDTO;
import systems.health263.dashboard.model.user.SystemUser;
import systems.health263.dashboard.model.user.UserDetailsUpdateDTO;
import systems.health263.dashboard.service.client.IClientService;
import systems.health263.dashboard.service.user.IAdminUserService;
import systems.health263.dashboard.service.user.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
@RequestMapping("*/api")
public class AdminUserController {
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final IAdminUserService adminUserService;

    private final IClientService clientService;

    public AdminUserController(TokenProvider tokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, IAdminUserService adminUserService, IClientService clientService) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.adminUserService = adminUserService;
        this.clientService = clientService;
    }

    @SuppressWarnings("unused")
    @PostMapping("/register")
    public ResponseEntity<ISystemUser> register(@Valid @RequestBody AdminUser systemUser,
                                                HttpServletResponse response) {
        systemUser.setPassword(this.passwordEncoder.encode(systemUser.getPassword()));
        LocalDateTime now = LocalDateTime.now();
        systemUser.setStartDate(now);

        ISystemUser systemUserSaved = this.adminUserService.save(systemUser);
        return new ResponseEntity<>(systemUserSaved, HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<SystemUserWithJWTToken> authorize(@Valid @RequestBody LoginSystemUserDTO loginUserDTO,
                                                            HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUserDTO.getEmail(), loginUserDTO.getPassword());

        try {
            this.authenticationManager.authenticate(authenticationToken);
            String jwt = this.tokenProvider.createToken(loginUserDTO.getEmail());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            ISystemUser systemUser = adminUserService.getAdminUserByEmail(loginUserDTO.getEmail());
            return new ResponseEntity<>(new SystemUserWithJWTToken(jwt, (AdminUser) systemUser), httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.info("Security exception {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PostMapping("/update_password/{userId}")
    public ResponseEntity<AdminUser> updateUserPassword(@PathVariable("userId")Long userId, @Valid @RequestBody UserDetailsUpdateDTO detailsUpdateDTO,
                                                         HttpServletResponse response) {
        AdminUser systemUser = adminUserService.getAdminUserById(userId);
        if (systemUser.getId() == null) {
            throw new BadRequestAlertException("invalid user details", "Invalid user", "id exists");
        }
        if (ObjectUtils.isEmpty(systemUser)) {
            throw new BadRequestAlertException("invalid user details empty", "Invalid user", "user empty");
        }
        String encryptedPassword = this.passwordEncoder.encode(detailsUpdateDTO.getNewPassword());
        systemUser.setPassword(encryptedPassword);
        AdminUser savedSystemUser = (AdminUser) adminUserService.save(systemUser);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(AdminUser.class.getCanonicalName(), systemUser.getId().toString()))
                .body(savedSystemUser);
         }

    @PutMapping("/update_user")
    public ResponseEntity<AdminUser> updateSystemUser(@Valid @RequestBody AdminUser systemUser,
                                                       HttpServletResponse response) {
        if (systemUser.getId() == null) {
            throw new BadRequestAlertException("invalid user details", "Invalid user", "id exists");
        }
        if (ObjectUtils.isEmpty(systemUser)) {
            throw new BadRequestAlertException("invalid user details ", "Invalid user", "id exists");
        }
        String encryptedPassword = this.passwordEncoder.encode(systemUser.getPassword());
        systemUser.setPassword(encryptedPassword);
        AdminUser savedSystemUser = (AdminUser) adminUserService.save(systemUser);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(AdminUser.class.getCanonicalName(), systemUser.getId().toString()))
                .body(savedSystemUser);
    }

    @GetMapping("/users/{clientId}")
    public ResponseEntity<List<AdminUser>> getAllUsersByClientId(@PathVariable("clientId")Long clientId,
                                                                  HttpServletResponse response) {
        List<AdminUser> systemUser = adminUserService.findAllByLocation_Client_Id(clientId);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(AdminUser.class.getCanonicalName(), String.valueOf(systemUser.size())))
                .body(systemUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUser>> getAllUsers(HttpServletResponse response) {
        List<AdminUser> systemUser = adminUserService.findAll();
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), String.valueOf(systemUser.size())))
                .body(systemUser);
    }
    /**
     * Object to return as body in JWT Authentication.
     */
    static class SystemUserWithJWTToken {

        private String idToken;

        private AdminUser systemUser;

        SystemUserWithJWTToken(String idToken , AdminUser systemUser) {
            this.idToken = idToken;
            this.systemUser = systemUser;
        }

        @JsonProperty("token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        public AdminUser getSystemUser() {
            return systemUser;
        }
        @JsonProperty("systemUser")
        public void setSystemUser(AdminUser systemUser) {
            this.systemUser = systemUser;
        }
    }

}
