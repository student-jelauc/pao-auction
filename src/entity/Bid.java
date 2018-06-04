package entity;

public class Bid extends AbstractEntity {
    private int userId;
    private int auctionId;
    private float price;

    public Bid() { }

    public Bid(int userId, int auctionId, float price) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.price = price;
    }

    public Bid(int id, int userId, int auctionId, float price) {
        this.id = id;
        this.userId = userId;
        this.auctionId = auctionId;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}