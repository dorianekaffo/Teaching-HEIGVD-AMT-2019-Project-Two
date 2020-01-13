package ch.heigvd.amt.p2.helper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collection;

public class AuthHelper {

    public static String getUsername() {
        UsernamePasswordAuthenticationToken token = getAuthentication();
        return (String)token.getPrincipal();
    }

    public static Collection<GrantedAuthority> getAuthorities() {
        UsernamePasswordAuthenticationToken token = getAuthentication();
        return token.getAuthorities();
    }

    private static UsernamePasswordAuthenticationToken getAuthentication() {
        return (UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication();
    }

    public static boolean isAdmin() {
        Collection<GrantedAuthority> authorities = getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
