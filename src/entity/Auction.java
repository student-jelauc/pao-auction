package entity;

public class Auction extends AbstractEntity {
    private int userId;
    private String title;
    private String products;
    private String publishAt;
    private String images;
    private float price;
    private int isClosed;

    public Auction() { }

    public Auction(int userId, String title, String products, String publishAt, String images, float price, int isClosed) {
        this.userId = userId;
        this.title = title;
        this.products = products;
        this.publishAt = publishAt;
        this.images = images;
        this.price = price;
        this.isClosed = isClosed;
    }

    public Auction(int id, int userId, String title, String products, String publishAt, String images, float price, int isClosed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.products = products;
        this.publishAt = publishAt;
        this.images = images;
        this.price = price;
        this.isClosed = isClosed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(int isClosed) {
        this.isClosed = isClosed;
    }
}