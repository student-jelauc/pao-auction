package repository;

import entity.Bid;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BidRepository extends AbstractRepository<Bid> {

    public ArrayList<Bid> getBidsByAuction(int auctionId) throws Exception {
        ArrayList<Bid> bids = new ArrayList<Bid>();

        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where auction_id = " + auctionId + " order by id desc"
        );

        while (rs.next()) {
            bids.add(new Bid(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("auction_id"),
                    rs.getFloat("price")
            ));
        }

        return bids;
    }

    public Bid getAuctionCurrentBid(int auctionId) throws Exception {
        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where auction_id = " + auctionId + " order by id desc limit 1"
        );

        while (rs.next()) {
            return new Bid(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("auction_id"),
                    rs.getFloat("price")
            );
        }

        return null;
    }

    @Override
    public Bid getById(int id) {
        return null;
    }

    public void store(Bid entity) throws Exception {
        String stmt = "INSERT INTO " + this.getTableName() + "(user_id, auction_id, price) VALUES ("
                + "'" + entity.getUserId() + "', "
                + "'" + entity.getAuctionId() + "', "
                + "'" + entity.getPrice() + "'"
                + ")";

        getConnection().createStatement().executeUpdate(stmt);
    }

    @Override
    public void update(Bid entity) throws Exception {
        throw new Exception("Bid entity updating not implemented");
    }

    @Override
    protected String getTableName() {
        return "bids";
    }
}
