package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.api.dto.UserDTO;
import ch.heigvd.amt.p2.enums.Role;
import ch.heigvd.amt.p2.exception.PasswordMismatchException;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
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
public class UserService implements IUserService<String, User> {

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
    public User create(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) throws PasswordMismatchException, ResourceNotFoundException {
        User user = this.get(email);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
        } else {
            throw new PasswordMismatchException();
        }
    }

    @Override
    public void toggleBlock(String userId, boolean willBlock) throws ResourceNotFoundException {
        User user = this.get(userId);
        user.setBlocked(willBlock);
        this.userRepository.save(user);
    }

    @Override
    public void sendCode(String userId) throws ResourceNotFoundException {
        User user = this.get(userId);
        this.mailService.sendAuthCodeMail(user);
    }

    @Override
    public void checkCode(String userId, long code) throws ResourceNotFoundException {
        User user = this.get(userId);
        this.mailService.sendAuthCodeMail(user);
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

    private User get(String email) throws ResourceNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));
    }

    public void forgotPassword(String email, HttpServletRequest request) throws ResourceNotFoundException {
        if (this.userRepository.existsById(email)) {
            this.mailService.sendPasswordResetMail(this.get(email), request);
        }
    }

    public boolean isAdmin(String ownerId) {
        return this.userRepository.existsByEmailAndRole(ownerId, Role.ADMIN);
    }

    public UserDTO convertToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole().toString());
        userDto.setBlocked(user.getBlocked());
        return userDto;
    }

    public User convertToEntity(UserDTO userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.valueOf(userDto.getRole()));
        user.setBlocked(userDto.getBlocked());
        return user;
    }

}
