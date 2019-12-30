package ch.heigvd.p2.firstapi.security;

import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
import ch.heigvd.p2.firstapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private UserService userService;

    public UserDetails loadUserByEmail(String email) throws RessourceNotFoundException {
        User user = userService.get(email);
        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return this.loadUserByEmail(username);
        } catch (RessourceNotFoundException ex){
            return null;
        }
    }
}
