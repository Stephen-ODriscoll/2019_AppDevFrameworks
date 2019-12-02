package ie.stephen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private int roleId;

    @OneToOne
    @JoinColumn(name = "userEmail", nullable = false)
    private RegisteredUser registeredUser;
    private String description;

    public Role(RegisteredUser registeredUser, String description) {
        this.registeredUser = registeredUser;
        this.description = description;
    }
}
