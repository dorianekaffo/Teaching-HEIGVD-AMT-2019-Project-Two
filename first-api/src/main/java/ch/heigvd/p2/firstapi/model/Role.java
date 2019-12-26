package ch.heigvd.p2.firstapi.model;

import ch.heigvd.p2.firstapi.enums.RoleType;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    private User user;

    // -- Constructeur(s)
    public Role() {}

    public Role(RoleType type, User user) {
        this.type = type;
        this.user = user;
    }

    // -- Getter(s) et Setter(s)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
