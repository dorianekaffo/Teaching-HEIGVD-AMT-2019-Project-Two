package ch.heigvd.p2.firstapi.model;

import ch.heigvd.p2.firstapi.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "email")
    private User owner;

    // -- Constructeur(s)
    public Role() {}

    public Role(RoleType type, User owner) {
        this.type = type;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
}
