package ie.stephen.dao;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidDao extends JpaRepository<Bid, Integer> {

    List<Bid> findAllByJob(Job job);
    List<Bid> findAllByBidder(RegisteredUser registeredUser);
}
