package ie.stephen.dao;

import ie.stephen.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidDao extends JpaRepository<Bid, Integer> {

}
