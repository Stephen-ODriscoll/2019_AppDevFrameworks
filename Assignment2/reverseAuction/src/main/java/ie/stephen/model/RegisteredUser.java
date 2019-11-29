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
    @Email
    private String userEmail;
    private String name;
    private String phoneNo;
    private String password;
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Job> jobs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "bidder", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Bid> bids = new ArrayList<>();

    public RegisteredUser(String userEmail, String name, String phoneNo, String password, boolean enabled) {
        this.userEmail = userEmail;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.enabled = enabled;
    }
}
