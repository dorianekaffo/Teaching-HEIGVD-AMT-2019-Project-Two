package ch.heigvd.p2.firstapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Token {

    @Id
    private Long id;

    private String token;

    private boolean expired = false;

    @ManyToOne
    private User user;

    // -- Constructeur(s)
    public Token() {}

    public Token(User user, String token) {
        this.token = token;
        this.user = user;
    }

    // -- Getter(s) et setter(s)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
