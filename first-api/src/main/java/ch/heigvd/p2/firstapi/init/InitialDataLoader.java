package ch.heigvd.p2.firstapi.init;

import ch.heigvd.p2.firstapi.enums.RoleType;
import ch.heigvd.p2.firstapi.model.Role;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IRoleRepository;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
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
    private IRoleRepository roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String ADMIN_EMAIL;

    @Value("${app.admin.password}")
    private String ADMIN_PASSWORD;

    @Value("${app.admin.firstname}")
    private String ADMIN_FIRSTNAME;

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
                null,
                this.passwordEncoder.encode(ADMIN_PASSWORD)
            );

            admin = this.userService.save(admin);

            Role adminRole = new Role(RoleType.ADMIN, admin);
            this.roleService.save(adminRole);

            Set<Role> roles = this.roleService.findByOwner(admin);
            admin.setRoles(roles);
            this.userService.save(admin);

        }

    }
}
