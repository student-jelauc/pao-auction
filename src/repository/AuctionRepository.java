package repository;

import entity.Auction;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AuctionRepository extends AbstractRepository<Auction> {

    public ArrayList<Auction> getAll() throws Exception {
        ArrayList<Auction> auctions = new ArrayList<Auction>();

        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where publish_at <= NOW() and (is_closed is null or is_closed = 0)"
        );

        while (rs.next()) {
            auctions.add(new Auction(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("products"),
                    rs.getString("publish_at"),
                    rs.getString("images"),
                    rs.getFloat("price"),
                    rs.getInt("is_closed")
            ));
        }

        return auctions;
    }

    public ArrayList<Auction> getUserAuctions(int userId) throws Exception {
        ArrayList<Auction> auctions = new ArrayList<Auction>();

        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where user_id = " + userId
        );

        while (rs.next()) {
            auctions.add(new Auction(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("products"),
                    rs.getString("publish_at"),
                    rs.getString("images"),
                    rs.getFloat("price"),
                    rs.getInt("is_closed")
            ));
        }

        return auctions;
    }

    @Override
    public Auction getById(int id) throws Exception {
        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() +" where id = '" + id + "'"
        );

        while (rs.next()) {
            return new Auction(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("products"),
                    rs.getString("publish_at"),
                    rs.getString("images"),
                    rs.getFloat("price"),
                    rs.getInt("is_closed")
            );
        }

        throw new Exception("Not found auction id: " + id);
    }

    public void store(Auction entity) throws Exception {
        String stmt = "INSERT INTO " + this.getTableName() + "(user_id, price, title, publish_at, products, images, is_closed) VALUES (" +
                "'" + entity.getUserId() + "', " +
                "'" + entity.getPrice() + "', " +
                "'" + entity.getTitle() + "', " +
                "'" + (!entity.getPublishAt().isEmpty() ? entity.getPublishAt() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) + "', " +
                "'" + entity.getProducts() + "', " +
                "'" + entity.getImages() + "', " +
                "'" + entity.getIsClosed() + "'" +
                ")";

        getConnection().createStatement().executeUpdate(stmt);
    }

    @Override
    public void update(Auction entity) throws Exception {
        String stmt = "update " + this.getTableName() + " set " +
                "user_id = '" + entity.getUserId() + "', " +
                "title ='" + entity.getTitle() + "', " +
                "publish_at ='" + entity.getPublishAt() + "', " +
                "products ='" + entity.getProducts() + "', " +
                "images ='" + entity.getImages() + "', " +
                "price ='" + entity.getPrice() + "', " +
                "is_closed ='" + entity.getIsClosed() + "' " +
                "where id = " + entity.getId();

        getConnection().createStatement().executeUpdate(stmt);
    }

    @Override
    protected String getTableName() {
        return "auctions";
    }
}
