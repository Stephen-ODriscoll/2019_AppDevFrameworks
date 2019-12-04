package ie.stephen.services;

import ie.stephen.dao.BidDao;
import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidServiceImplementation implements BidService {

    @Autowired
    BidDao bidDAO;

    @Override
    public Bid save(Bid bid) {
        return bidDAO.save(bid);
    }

    @Override
    public boolean isValid(double amount, Job job, RegisteredUser bidder) {

        // Can't bid negative, on a closed job or on job you own
        if (amount < 0 || !job.getOpen() || bidder == job.getCreator()) {
            return false;
        }

        // Gotta be the lowest bid
        for (Bid bid : bidDAO.findAllByJob(job)) {
            if (amount >= bid.getAmount()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Bid> getBids(RegisteredUser registeredUser) {
        return bidDAO.findAllByBidder(registeredUser);
    }

    @Override
    public List<Bid> getBids(Job job) {
        return bidDAO.findAllByJob(job);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidDAO.findAll();
    }

    @Override
    public List<Bid> getWinningBids(List<Job> jobs) {
        List<Bid> winningBids = new ArrayList<>();

        for (Job job : jobs) {

            Bid winningBid = null;
            for (Bid bid : bidDAO.findAllByJob(job)) {
                if (winningBid == null || bid.getAmount() < winningBid.getAmount()) {
                    winningBid = bid;
                }
            }

            winningBids.add(winningBid);
        }

        return winningBids;
    }
}
