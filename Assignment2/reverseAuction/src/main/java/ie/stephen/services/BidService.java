package ie.stephen.services;

import ie.stephen.model.Bid;
import java.util.List;

public interface BidService {

    Bid save(Bid bid);
    List<Bid> getAllBids();
}
