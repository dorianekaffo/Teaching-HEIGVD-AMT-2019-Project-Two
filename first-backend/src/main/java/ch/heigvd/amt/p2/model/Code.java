package ch.heigvd.amt.p2.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Date expiryDate;

    private boolean expired = false;

    @ManyToOne
    private User owner;

    // -- Constructeur
    public Code() { }

    public Code(User owner, Date expiryDate) {
        this.code = this.generateCode();
        this.owner = owner;
        this.expiryDate = expiryDate;
    }

    public Code(User owner, int validityPeriodInMs) {
        this.code = this.generateCode();
        this.owner = owner;
        Calendar cExpiryDate = Calendar.getInstance();
        cExpiryDate.add(Calendar.MILLISECOND, validityPeriodInMs);
        this.expiryDate = cExpiryDate.getTime();
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
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
