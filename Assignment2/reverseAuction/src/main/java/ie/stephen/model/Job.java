package ie.stephen.model;

import java.util.List;

public class Job {

    private String name;
    private String description;
    private RegisteredUser creator;
    private List<Bid> bids;

    public Job() {
    }

    public Job(String name, String description, RegisteredUser creator, List<Bid> bids) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.bids = bids;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RegisteredUser getCreator() {
        return creator;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(RegisteredUser creator) {
        this.creator = creator;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
