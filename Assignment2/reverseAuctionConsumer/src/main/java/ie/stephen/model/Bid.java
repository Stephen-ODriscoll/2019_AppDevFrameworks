package ie.stephen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue
    private int bidId;
    private double amount;

    @ManyToOne
    @JoinColumn(name="jobId")
    Job job;

    @ManyToOne
    @JoinColumn(name="userId")
    RegisteredUser bidder;

    public Bid(int amount, Job job, RegisteredUser bidder) {
        this.amount = amount;
        this.job = job;
        this.bidder = bidder;
    }
}
