package ie.stephen.model;

public class Bid {

    private RegisteredUser bidder;
    private double amount;

    public Bid() {
    }

    public Bid(RegisteredUser bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public RegisteredUser getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }

    public void setBidder(RegisteredUser bidder) {
        this.bidder = bidder;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
