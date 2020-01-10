package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.enums.Role;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.UnauthorizedAccessException;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.repository.IUserRepository;
import ch.heigvd.amt.p2.security.TokenService;
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

    @Override
    public User create(User user) throws ResourceNotFoundException {

        if (user.getOwner() != null && this.userRepository.existsById(user.getOwner())) {
            return this.userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User", "email", user.getOwner());
        }

    }

    @Override
    public User update(String email, User user) throws ResourceNotFoundException {
        if (this.userRepository.existsById(email) && user.getEmail() != null) {
            if (user.getOwner() != null && this.userRepository.existsById(user.getOwner())) {
                return this.userRepository.save(user);
            } else {
                throw new ResourceNotFoundException("User", "email", user.getOwner());
            }
        } else {
            throw new ResourceNotFoundException("User", "email", email);
        }
    }

    @Override
    public void delete(String email) throws ResourceNotFoundException {
        User user = this.get(email);
        this.userRepository.delete(user);
    }

    @Override
    public User get(String email) throws ResourceNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public Page<User> get(Pageable pgble) { return this.userRepository.findAll(pgble);
    }

    public Page<User> get(String ownerId, Pageable pgble) {
        return this.userRepository.findByOwner(ownerId, pgble);
    }

    public void changeBlockedStatus(String email, boolean willBlock) throws ResourceNotFoundException {
        User user = this.get(email);
        user.setBlocked(willBlock);
        this.userRepository.save(user);
    }

    public void forgotPassword(String email, HttpServletRequest request) throws ResourceNotFoundException {
        if (this.userRepository.existsById(email)) {
            this.mailService.sendPasswordResetMail(this.get(email), request);
        }
    }

    public void changePassword(String email, String oldPassword, String newPassword) throws ResourceNotFoundException {
        User user = this.get(email);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
        }
    }

    public void resetPassword(
            String token, String newPassword
    ) throws ResourceNotFoundException {

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
        } catch(ResourceNotFoundException ex) {}
    }

    public boolean checkCode(String email, String code) {
        try {
            User user = this.get(email);
            return this.codeService.check(user, code);
        } catch (ResourceNotFoundException ex) {
            return false;
        }
    }

    public boolean checkOwner(String ownerId, String userId) throws UnauthorizedAccessException {
        if (this.userRepository.existsByEmailAndOwner(userId, ownerId) ||
            this.isAdmin(ownerId)
        ) {
            return true;
        };
        throw new UnauthorizedAccessException("User", userId);
    }

    public boolean isAdmin(String ownerId) {
        return this.userRepository.existsByEmailAndRole(ownerId, Role.ADMIN);
    }

}
