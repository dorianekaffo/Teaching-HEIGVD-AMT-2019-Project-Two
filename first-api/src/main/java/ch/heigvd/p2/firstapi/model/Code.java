package ch.heigvd.p2.firstapi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Random;

public class Code {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private Date expiryDate;

    private boolean expired = false;

    @ManyToOne
    private User user;

    // -- Constructeur
    public Code() { }

    public Code(User user, Date expiryDate) {
        this.code = this.generateCode();
        this.user = user;
        this.expiryDate = expiryDate;
    }

    // -- Getter(s) et Setter(s)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    // -- Méthode(s) propre(s)
    private String generateCode() {
        Random rand = new Random();
        Integer code = rand.nextInt(900000) + 100000;
        return code.toString();
    }
}
