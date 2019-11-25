package ie.stephen.services;

import ie.stephen.dao.BidDao;
import ie.stephen.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Bid> getAllBids() {
        return bidDAO.findAll();
    }
}
