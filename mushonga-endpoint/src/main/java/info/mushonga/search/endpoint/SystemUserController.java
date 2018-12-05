package info.mushonga.search.endpoint;

import info.mushonga.search.endpoint.config.app.errors.BadRequestAlertException;
import info.mushonga.search.endpoint.config.app.util.HeaderUtil;
import info.mushonga.search.endpoint.config.jwt.TokenProvider;
import info.mushonga.search.imodel.user.ISystemUser;
import info.mushonga.search.model.account.Account;
import info.mushonga.search.model.user.SystemUser;
import info.mushonga.search.model.user.UserDetailsUpdateDTO;
import info.mushonga.search.service.user.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    public SystemUserController(TokenProvider tokenProvider, PasswordEncoder passwordEncoder, ISystemUserService systemUserService) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.systemUserService = systemUserService;
    }

    @SuppressWarnings("unused")
    @PostMapping("/register")
    public ResponseEntity<ISystemUser> register(@Valid @RequestBody SystemUser systemUser,
                                                HttpServletResponse response) {
        systemUser.setPassword(this.passwordEncoder.encode(systemUser.getPassword()));
        LocalDateTime now = LocalDateTime.now();
        systemUser.setStartDate(now);

        ISystemUser systemUserSaved = this.systemUserService.saveSystemUser(systemUser);
        Account account = new Account();
        account.setAccountNumber(systemUserSaved.getId().toString());
        systemUserSaved.setAccount(account);
        ISystemUser systemUserWithAcc  = this.systemUserService.saveSystemUser((SystemUser)systemUserSaved);
        return new ResponseEntity<>(systemUserWithAcc, HttpStatus.OK);
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


    @GetMapping("/users")
    public ResponseEntity<List<SystemUser>> getAllUsers(HttpServletResponse response) {
        List<SystemUser> systemUser = systemUserService.findAll();
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(SystemUser.class.getCanonicalName(), String.valueOf(systemUser.size())))
                .body(systemUser);
    }

}