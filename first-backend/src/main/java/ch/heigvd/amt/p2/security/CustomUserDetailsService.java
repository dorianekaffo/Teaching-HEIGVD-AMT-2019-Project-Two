package ch.heigvd.amt.p2.security;

import ch.heigvd.amt.p2.service.UserService;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.model.User;
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

    public UserDetails loadUserByEmail(String email) throws ResourceNotFoundException {
        User user = userService.get(email);
        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return this.loadUserByEmail(username);
        } catch (ResourceNotFoundException ex){
            return null;
        }
    }
}
