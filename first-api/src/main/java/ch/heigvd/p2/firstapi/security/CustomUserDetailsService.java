package ch.heigvd.p2.firstapi.security;

import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IUserRepository utilisateurRepository;

    public UserDetails loadUserByEmail(String email)
            throws UsernameNotFoundException {

        // Let people login with either username or email
        User user = utilisateurRepository.findById(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Utilisateur ayant ce pseudonyme ou adresse email introuvable : " + email)
                );

        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.loadUserByEmail(username);
    }
}
