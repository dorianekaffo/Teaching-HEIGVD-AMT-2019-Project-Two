package ch.heigvd.amt.p2.init;

import ch.heigvd.amt.p2.enums.Role;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.Set;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private IUserRepository userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String ADMIN_EMAIL;

    @Value("${app.admin.password}")
    private String ADMIN_PASSWORD;

    @Value("${app.admin.firstname}")
    private String ADMIN_FIRSTNAME;

    @Value("${app.admin.lastname}")
    private String ADMIN_LASTNAME;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup)
            return;

        this.createAdmin();
        alreadySetup = true;
    }

    private void createAdmin() {

        if (!this.userService.existsById(ADMIN_EMAIL)) {

            User admin = new User(
                ADMIN_EMAIL,
                ADMIN_FIRSTNAME,
                    ADMIN_LASTNAME,
                this.passwordEncoder.encode(ADMIN_PASSWORD)
            );

            admin.setRole(Role.ADMIN);
            this.userService.save(admin);

        }

    }
}
