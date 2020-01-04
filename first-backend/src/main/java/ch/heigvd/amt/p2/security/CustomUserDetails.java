package ch.heigvd.amt.p2.security;

import ch.heigvd.amt.p2.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private boolean blocked;
    private Collection<? extends GrantedAuthority> authorities;

    // -- Constructeur(s)
    public CustomUserDetails(
            String email,
            String firstname,
            String lastname,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.email = email;
        this.firstname= firstname;
        this.lastname = lastname;
        this.password = password;
        this.authorities = authorities;
    }

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.blocked = user.getBlocked();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream().map(
                (role) -> new SimpleGrantedAuthority("ROLE_" + role.getType().toString())).collect(Collectors.toList());
    }
    // -- Getter(s) et setter(s)
    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.email);
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", blooked=" + blocked +
                ", authorities=" + authorities +
                '}';
    }
}