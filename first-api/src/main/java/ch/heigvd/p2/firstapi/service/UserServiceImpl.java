package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements EntityService<User, String> {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(String email, User user) {
        if (this.userRepository.existsById(email) && user.getEmail() != null) {
            return this.userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(String email) {
        if (this.userRepository.existsById(email)) {
            this.userRepository.deleteById(email);
        }
    }

    @Override
    public User get(String email) {
        return this.userRepository.findById(email).get();
    }

    @Override
    public Page<User> get(Pageable pgble) {
        return this.userRepository.findAll(pgble);
    }

    public void changeBlockedStatus(String email, boolean willBlock) {
        User user = this.userRepository.findById(email).get();
        user.setBlocked(willBlock);
        this.userRepository.save(user);
    }

    public void forgotPassword(String email) {
        if (this.userRepository.existsById(email)) {
            this.mailService.sendPasswordResetMail(
                    this.get(email));
        }
    }

    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = this.userRepository.findById(email).get();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
        }
    }



}
