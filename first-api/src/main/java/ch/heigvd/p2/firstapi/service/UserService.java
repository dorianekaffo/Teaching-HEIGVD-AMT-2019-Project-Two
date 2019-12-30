package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.enums.RoleType;
import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.exception.UnauthorizedAccessException;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
import ch.heigvd.p2.firstapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements IEntityService<User, String> {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(String email, User user) throws RessourceNotFoundException {
        if (this.userRepository.existsById(email) && user.getEmail() != null) {
            return this.userRepository.save(user);
        }
        throw new RessourceNotFoundException("User", "email", email);
    }

    @Override
    public void delete(String email) throws RessourceNotFoundException {
        if (this.userRepository.existsById(email)) {
            this.userRepository.deleteById(email);
        }
        throw new RessourceNotFoundException("User", "email", email);
    }

    @Override
    public User get(String email) throws RessourceNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new RessourceNotFoundException("User", "email", email));
    }

    @Override
    public Page<User> get(Pageable pgble) { return this.userRepository.findAll(pgble);
    }

    public Page<User> get(String ownerId, Pageable pgble) {
        return this.userRepository.findByOwnerEmail(ownerId, pgble);
    }

    public void changeBlockedStatus(String email, boolean willBlock) throws RessourceNotFoundException {
        User user = this.get(email);
        user.setBlocked(willBlock);
        this.userRepository.save(user);
    }

    public void forgotPassword(String email, HttpServletRequest request) throws RessourceNotFoundException {
        if (this.userRepository.existsById(email)) {
            this.mailService.sendPasswordResetMail(this.get(email), request);
        }
    }

    public void changePassword(String email, String oldPassword, String newPassword) throws RessourceNotFoundException {
        User user = this.get(email);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
        }
    }

    public void resetPassword(
            String token, String newPassword
    ) throws RessourceNotFoundException {

        User user = this.get(this.tokenService.getUidFromJWT(token));

        if (this.tokenService.validateToken(token)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
        }

    }

    public void getCode(String email) {
        try {
            User user = this.get(email);
            this.mailService.sendAuthCodeMail(user);
        } catch(RessourceNotFoundException ex) {}
    }

    public boolean checkCode(String email, String code) {
        try {
            User user = this.get(email);
            return this.codeService.check(user, code);
        } catch (RessourceNotFoundException ex) {
            return false;
        }
    }

    public boolean checkOwner(String ownerId, String userId) throws UnauthorizedAccessException {
        if (this.userRepository.existsByEmailAndOwnerEmail(userId, ownerId) ||
            this.roleService.isAdmin(ownerId)
        ) {
            return true;
        };
        throw new UnauthorizedAccessException("User", userId);
    }

}
