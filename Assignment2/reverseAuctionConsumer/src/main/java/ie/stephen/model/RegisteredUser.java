package ie.stephen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String email;
    private String phoneNo;

    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "bidder", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Bid> bids = new ArrayList<>();

    public RegisteredUser(String email, String name, String phoneNo) {
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
