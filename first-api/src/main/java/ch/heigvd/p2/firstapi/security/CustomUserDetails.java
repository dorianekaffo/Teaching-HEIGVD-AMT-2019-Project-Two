package ch.heigvd.p2.firstapi.security;

import ch.heigvd.p2.firstapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private boolean blooked;
    private String token;
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
        this.lastname=lastname;
        this.authorities = authorities;
    }

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.firstname= user.getFirstname();
        this.lastname=user.getLastname();
        // this.authorities = user.get;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", blooked=" + blooked +
                ", token='" + token + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}