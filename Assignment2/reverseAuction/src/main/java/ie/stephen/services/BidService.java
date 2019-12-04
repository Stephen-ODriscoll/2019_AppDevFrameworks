package ie.stephen.services;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;

import java.util.List;

public interface BidService {

    Bid save(Bid bid);
    boolean isValid(double amount, Job job, RegisteredUser bidder);
    List<Bid> getBids(RegisteredUser registeredUser);
    List<Bid> getBids(Job job);
    List<Bid> getAllBids();
    List<Bid> getWinningBids(List<Job> job);
}
