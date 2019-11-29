package ie.stephen.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue
    private int jobId;
    private String name;
    private String description;
    private LocalDate date;
    private boolean open;

    @ManyToOne
    @JoinColumn(name="userId")
    RegisteredUser creator;

    @OneToMany(mappedBy="job", fetch= FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Bid> bids = new ArrayList<>();

    public Job(String name, String description, RegisteredUser creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.date = LocalDate.now();
        this.open = true;
    }

    public boolean getOpen() {
        return open;
    }
}
