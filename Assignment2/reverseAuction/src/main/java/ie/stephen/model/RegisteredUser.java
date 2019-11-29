package ie.stephen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUser {

    @Id
    @GeneratedValue
    private int userId;
    private String name;

    @Email
    private String userEmail;
    private String phoneNo;
    private String password;
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Job> jobs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "bidder", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Bid> bids = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "roleEmail", nullable = false)
    private Role role;

    public RegisteredUser(String name, String userEmail, String phoneNo, String password, boolean enabled, Role role) {
        this.name = name;
        this.userEmail = userEmail;
        this.phoneNo = phoneNo;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
